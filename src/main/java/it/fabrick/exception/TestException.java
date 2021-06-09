package it.fabrick.exception;

import it.fabrick.error.IErrorCode;
import org.springframework.http.HttpStatus;

public class TestException extends AbstractErrorException {

    public TestException(String message, IErrorCode errorCode) {
        super(message, HttpStatus.BAD_REQUEST, errorCode);
    }
}
