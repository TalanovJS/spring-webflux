package ru.vdcom.springwebflux.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.vdcom.springwebflux.dto.MultiplyRequestDto;
import ru.vdcom.springwebflux.dto.Response;
import ru.vdcom.springwebflux.service.ReactiveMathService;

import java.util.Map;

@RestController
@RequestMapping("/reactive")
@RequiredArgsConstructor
public class ReactiveMathController {

    private final ReactiveMathService service;

    @GetMapping("/square/{input}")
    public Mono<Response> findSquare(@PathVariable("input") Integer input) {
        return service.findSquare(input);
    }

    @GetMapping("/table/{input}")
    public Flux<Response> getList(@PathVariable("input") Integer input) {
        return service.findTable(input);
    }

    @GetMapping(value = "/table/{input}/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Response> getListStream(@PathVariable("input") Integer input) {
        return service.findTable(input);
    }

    @PostMapping("/multiply")
    public Mono<Response> getMultiply(@RequestBody Mono<MultiplyRequestDto> request,
                                      @RequestHeader Map<String, String> headers) {
        System.out.println(headers);
        return service.multiply(request);
    }
}
