package ru.vdcom.springwebflux.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vdcom.springwebflux.dto.Response;
import ru.vdcom.springwebflux.service.MathService;

import java.util.List;

@RestController
@RequestMapping("/math")
@RequiredArgsConstructor
public class MathController {

    private final MathService mathService;

    @GetMapping("/square/{input}")
    public Response findSquare(@PathVariable("input") Integer input) {
        return mathService.getResponse(input);
    }

    @GetMapping("/table/{input}")
    public List<Response> getList(@PathVariable("input") Integer input) {
        return mathService.getListOfResponse(input);
    }
}
