package ru.vdcom.springwebflux.controller;

import org.springframework.http.ResponseEntity;
import ru.vdcom.springwebflux.exception.InputValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.vdcom.springwebflux.dto.Response;
import ru.vdcom.springwebflux.service.ReactiveMathService;

@RestController
@RequiredArgsConstructor
public class ReactiveValidationController {

    private final ReactiveMathService service;

    @GetMapping("/square/{input}/throw")
    public Mono<Response> findSquare(@PathVariable("input") Integer input) {
        if (input < 10 || input > 20) {
            throw new InputValidationException(input);
        }
        return service.findSquare(input);
    }

    @GetMapping("/square/{input}/mono-error")
    public Mono<Response> monoError(@PathVariable("input") Integer input) {
        return Mono.just(input)
                .handle(((integer, sink) -> {
                    if(integer >= 10 && integer <= 20){
                        sink.next(integer);
                    } else {
                        sink.error(new InputValidationException(integer));
                    }
                }))
                .cast(Integer.class)
                .flatMap(service::findSquare);
    }

    @GetMapping("/square/{input}/assigment")
    public Mono<ResponseEntity<Response>> assigment(@PathVariable("input") Integer input) {
        return Mono.just(input)
                .filter(i -> i >= 10 && i <= 20)
                .flatMap(service::findSquare)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }
}
