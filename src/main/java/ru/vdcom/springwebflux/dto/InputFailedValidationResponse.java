package ru.vdcom.springwebflux.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class InputFailedValidationResponse {

    private Integer errorCode;
    private Integer input;
    private String message;
}
