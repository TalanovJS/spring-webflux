package ru.vdcom.springwebflux.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.vdcom.springwebflux.dto.MultiplyRequestDto;
import ru.vdcom.springwebflux.dto.Response;

import java.time.Duration;

@Service
public class ReactiveMathService {

    public Mono<Response> findSquare(Integer input) {
        return Mono.fromSupplier(() -> input * input)
                .map(Response::new);
    }

    public Flux<Response> findTable(Integer input) {
        return Flux.range(1, 5)
//                .doOnNext(i -> SleepService.sleep(1000))
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println("reactive math - service processing : " + i))
                .map(i -> new Response(i * input));
    }

    public Mono<Response> multiply(Mono<MultiplyRequestDto> request) {
        return request
                .map(dto -> dto.getFirstNumber() * dto.getSecondNumber())
                .map(Response::new);
    }
}
