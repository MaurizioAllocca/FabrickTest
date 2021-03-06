package it.fabrick.handler;

import it.fabrick.entity.Errors;
import it.fabrick.entity.GenericResponse;
import it.fabrick.error.ErrorCode;
import it.fabrick.utils.JsonUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandler implements IExceptionHandler {

    @Override
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<? extends ErrorCode> handleGenericException(
        Exception e, HttpServletRequest request
    ) {
        return new ResponseEntity<>(
            IExceptionHandler.buildErrorResponse(
                request,
                "INTSERVERERR",
                "Internal server error"),
            HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<? extends ErrorCode> handleHttpClientErrorException(
        HttpStatusCodeException e, HttpServletRequest request
    ) {
        Errors error = JsonUtils.asPojo(
            e.getResponseBodyAsString(), GenericResponse.class)
            .getErrors()[0];

        return new ResponseEntity<>(
            IExceptionHandler.buildErrorResponse(
                request,
                error.getCode(),
                error.getDescription()),
            e.getStatusCode());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<? extends ErrorCode> handleMissingServletRequestParameterException(
        MissingServletRequestParameterException e, HttpServletRequest request
    ) {
        return new ResponseEntity<>(
            IExceptionHandler.buildErrorResponse(
                request,
                "FBC000",
                e.getMessage()),
            HttpStatus.BAD_REQUEST);
    }
}
