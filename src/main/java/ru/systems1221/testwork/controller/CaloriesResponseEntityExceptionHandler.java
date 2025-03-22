package ru.systems1221.testwork.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.systems1221.testwork.exception.UserCreateException;
import ru.systems1221.testwork.exception.dto.ErrorResponseDto;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CaloriesResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(errors);
    }


    private HttpStatus determineHttpStatus(Exception exception) {
        return switch (exception) {
            case EntityNotFoundException e -> HttpStatus.NOT_FOUND;
            case ConstraintViolationException e -> HttpStatus.BAD_REQUEST;
            case IllegalArgumentException e -> HttpStatus.BAD_REQUEST;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
    }

    @ExceptionHandler({
            UserCreateException.class, //500
            EntityNotFoundException.class, //404
            ConstraintViolationException.class, //400
            IllegalArgumentException.class //400
    })
    public ResponseEntity<ErrorResponseDto> handleExceptions(Exception exception) {
        return ResponseEntity.status(determineHttpStatus(exception))
                .body(new ErrorResponseDto(exception.getMessage()));
    }

}
