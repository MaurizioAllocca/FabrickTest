package it.fabrick.exception;

import it.fabrick.error.IErrorCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public class AbstractErrorException extends RuntimeException {
    private final transient IErrorCode errorCode;
    private final HttpStatus httpStatus;

    public AbstractErrorException(String message, HttpStatus httpStatus, IErrorCode errorCode) {
        super(message);
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
    }

    public AbstractErrorException(String message, HttpStatus httpStatus, IErrorCode errorCode, Throwable t) {
        super(message, t);
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
    }
}
