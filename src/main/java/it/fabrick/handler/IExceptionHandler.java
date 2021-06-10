package it.fabrick.handler;

import it.fabrick.error.ErrorCode;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface IExceptionHandler {

    static ErrorCode buildErrorResponse(HttpServletRequest request,
                                        String errorCode, String errorMessage) {

        return ErrorCode.builder()
            .code(errorCode)
            .description(errorMessage)
            .build();
    }

    ResponseEntity<? extends ErrorCode> handleGenericException(Exception e,
                                                               HttpServletRequest request);
}
