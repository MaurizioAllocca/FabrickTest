package it.fabrick.exception;

import it.fabrick.error.IError;
import org.springframework.http.HttpStatus;

public class TestException extends AbstractErrorException {

    public TestException(String message, IError errorCode) {
        super(message, HttpStatus.BAD_REQUEST, errorCode);
    }
}
