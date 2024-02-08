package org.example.apps.validation.handler;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.example.apps.validation.dto.res.ValidationErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.util.stream.Collectors;

/**
 * @author rival
 * @since 2023-10-24
 */
@ControllerAdvice
@Slf4j
public class ValidationExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleMethodArgsNotValidException(MethodArgumentNotValidException ex){

        log.info("Method Argument Not valid Exception", ex);

        String message = ex.getBindingResult().getFieldErrors().stream()
            .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
            .collect(Collectors.joining(", "));

        ValidationErrorResponse errorResponse = new ValidationErrorResponse(message);
        return ResponseEntity.badRequest().body(errorResponse);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex){

        log.info("Constraint Violation Exception", ex);

        String message = ex.getConstraintViolations().stream()
            .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
            .collect(Collectors.joining(", "));

        ValidationErrorResponse errorResponse = new ValidationErrorResponse(message);
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
