package it.fabrick.handler;

import it.fabrick.error.ErrorResponse;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface IExceptionHandler {

    ResponseEntity<? extends ErrorResponse> handleGenericException(Exception e,
                                                                   HttpServletRequest request);

    static ErrorResponse buildErrorResponse(HttpServletRequest request,
                                            String errorCode, String errorMessage) {

        return ErrorResponse.builder()
                .errorCode(errorCode)
                .errorMessage(errorMessage)
                .build();
    }
}
