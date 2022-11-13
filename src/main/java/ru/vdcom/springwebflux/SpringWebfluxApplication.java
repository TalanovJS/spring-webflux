package ru.vdcom.springwebflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * Single objects = Mono
 * List = Flux
 */
@SpringBootApplication
public class SpringWebfluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringWebfluxApplication.class, args);
    }

}
