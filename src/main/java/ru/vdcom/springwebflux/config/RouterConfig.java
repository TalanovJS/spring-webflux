package ru.vdcom.springwebflux.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;
import ru.vdcom.springwebflux.dto.InputFailedValidationResponse;
import ru.vdcom.springwebflux.exception.InputValidationException;

import java.util.function.BiFunction;

/*
 * Functional endpoints
 */
@Configuration
@RequiredArgsConstructor
public class RouterConfig {

    private final RequestHandler requestHandler;

    @Bean
    public RouterFunction<ServerResponse> highLevelRouter() {
        return RouterFunctions.route()
                .path("router", this::serverResponseRouterFunction)
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> serverResponseRouterFunction() {
        return RouterFunctions.route()
                    .GET("/square/{input}", RequestPredicates.path("*/1?").or(RequestPredicates.path("*/20")), requestHandler::squareHandler)
                    .GET("/square/{input}", request -> ServerResponse.badRequest().bodyValue("only 10 - 19 allowed"))
                    .GET("/table/{input}", requestHandler::tableHandler)
                    .GET("/table/{input}/stream", requestHandler::tableStreamHandler)
                    .POST("/multiply", requestHandler::multilpyHandler)
                    .GET("/square/{input}/validation", requestHandler::squareHandlerWithValidation)
                    .onError(InputValidationException.class, exceptionHandler())
                .build();
    }

    private BiFunction<Throwable, ServerRequest, Mono<ServerResponse>> exceptionHandler() {
        return ((throwable, serverRequest) -> {
            InputValidationException ex = (InputValidationException) throwable;
            InputFailedValidationResponse response = new InputFailedValidationResponse();
            response.setInput(ex.getInput());
            response.setMessage(ex.getMessage());
            response.setErrorCode(ex.getErrorCode());
            return ServerResponse.badRequest().bodyValue(response);
        });
    }
}
