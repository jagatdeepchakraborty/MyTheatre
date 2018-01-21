package com.jdc.theatre.exception;

public class ProcessingException extends Exception {
    private String errorMessage;

    public ProcessingException(String message, String errorMessage) {
        super(message);
        this.errorMessage = errorMessage;
    }

    public ProcessingException(Throwable cause, String errorMessage) {
        super(errorMessage, cause);
        this.errorMessage = errorMessage;
    }

    public ProcessingException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
}
