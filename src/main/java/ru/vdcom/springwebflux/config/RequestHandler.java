package ru.vdcom.springwebflux.config;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.vdcom.springwebflux.dto.InputFailedValidationResponse;
import ru.vdcom.springwebflux.dto.MultiplyRequestDto;
import ru.vdcom.springwebflux.dto.Response;
import ru.vdcom.springwebflux.exception.InputValidationException;
import ru.vdcom.springwebflux.service.ReactiveMathService;

@Service
@RequiredArgsConstructor
public class RequestHandler {

    private final ReactiveMathService service;

    public Mono<ServerResponse> squareHandler(ServerRequest serverRequest) {
        Integer input = Integer.valueOf(serverRequest.pathVariable("input"));
        Mono<Response> responseMono = service.findSquare(input);
        return ServerResponse.ok().body(responseMono, Response.class);
    }

    public Mono<ServerResponse> squareHandlerWithValidation(ServerRequest serverRequest) {
        int input = Integer.parseInt(serverRequest.pathVariable("input"));
        if (input < 10 || input > 20) {
//            InputFailedValidationResponse response = new InputFailedValidationResponse();
//            return ServerResponse.badRequest().bodyValue(response);
            return Mono.error(new InputValidationException(input));
        }
        Mono<Response> responseMono = service.findSquare(input);
        return ServerResponse.ok().body(responseMono, Response.class);
    }

    public Mono<ServerResponse> tableHandler(ServerRequest serverRequest) {
        Integer input = Integer.valueOf(serverRequest.pathVariable("input"));
        Flux<Response> responseFlux = service.findTable(input);
        return ServerResponse.ok().body(responseFlux, Response.class);
    }

    public Mono<ServerResponse> tableStreamHandler(ServerRequest serverRequest) {
        Integer input = Integer.valueOf(serverRequest.pathVariable("input"));
        Flux<Response> responseFlux = service.findTable(input);
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(responseFlux, Response.class);
    }

    public Mono<ServerResponse> multilpyHandler(ServerRequest serverRequest) {
        Mono<MultiplyRequestDto> requestDtoMono = serverRequest.bodyToMono(MultiplyRequestDto.class);
        Mono<Response> responseMono = service.multiply(requestDtoMono);
        return ServerResponse.ok()
                .body(responseMono, Response.class);
    }
}
