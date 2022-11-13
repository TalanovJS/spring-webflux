package ru.vdcom.springwebflux.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vdcom.springwebflux.dto.Response;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class MathService {

    public Response getResponse(Integer input) {
        return new Response(input * input);
    }

    public List<Response> getListOfResponse(Integer input) {
        return IntStream.range(1, 10)
                .peek(i -> SleepService.sleep(1000))
                .peek(i -> System.out.println("math - service processing : " + i))
                .mapToObj(i -> new Response(i * input))
                .collect(Collectors.toList());
    }
}
