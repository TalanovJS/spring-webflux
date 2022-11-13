package ru.vdcom.springwebflux.exception;

public class InputValidationException extends RuntimeException {
    private static final String MESSAGE = "allowed range is 10 - 20";
    private final Integer input;

    public InputValidationException(Integer input) {
        super(MESSAGE);
        this.input = input;
    }

    public Integer getErrorCode() {
        return 100;
    }

    public Integer getInput() {
        return input;
    }
}
