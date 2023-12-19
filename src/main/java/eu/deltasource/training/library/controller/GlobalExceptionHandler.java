package eu.deltasource.training.library.controller;

import eu.deltasource.training.library.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            InvalidAuthorException.class,
            InvalidBookException.class,
            InvalidSaleException.class
    })
    public ResponseEntity<String> invalidEntityHandler(RuntimeException exception) {
        return responseGenerator(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> missingParameterHandler(MissingServletRequestParameterException exception) {
        return new ResponseEntity<>(exception.getParameterName() + " cannot be null/empty", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> entityNotFoundHandler(RuntimeException exception) {
        return responseGenerator(exception, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<String> responseGenerator(Exception exception, HttpStatus status) {
        return new ResponseEntity<>(exception.getMessage(), status);
    }
}
