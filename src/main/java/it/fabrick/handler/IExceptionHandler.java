package it.fabrick.handler;

import it.fabrick.error.ErrorCode;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface IExceptionHandler {

    ResponseEntity<? extends ErrorCode> handleGenericException(Exception e,
                                                               HttpServletRequest request);

    static ErrorCode buildErrorResponse(HttpServletRequest request,
                                        String errorCode, String errorMessage) {

        return ErrorCode.builder()
                .errorCode(errorCode)
                .errorMessage(errorMessage)
                .build();
    }
}
