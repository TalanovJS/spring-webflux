package ru.vdcom.springwebflux.dto;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class Response {
    private Date date = new Date();
    private Integer output;

    public Response(Integer output) {
        this.output = output;
    }
}
