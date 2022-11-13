package ru.vdcom.springwebflux.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MultiplyRequestDto {

    private Integer firstNumber;
    private Integer secondNumber;

}
