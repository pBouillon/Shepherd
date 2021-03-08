package com.fisæ.shepherd.api.controller;

import com.fisæ.shepherd.application.commons.exception.EntityNotFoundException;
import com.fisæ.shepherd.application.commons.exception.ShepherdApplicationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Rest exception handler to digest inner exceptions and filter them before sending them to the client
 */
@Log4j2
@RestControllerAdvice
public class RestExceptionHandler {

    /**
     * Handle application exceptions that might not be filtered by any other handler
     *
     * @param exception Application exception
     *
     * @return The formatted 400 associated
     */
    @ResponseBody
    @ExceptionHandler(value = ShepherdApplicationException.class)
    public ResponseEntity<?> handleShepherdApplicationException(ShepherdApplicationException exception) {
        log.error(
                "BAD REQUEST | {} : {}",
                exception.getClass().getSimpleName(),
                exception.getMessage());

        return ResponseEntity.badRequest()
                .body(exception.getReason());
    }

    /**
     * Handle application custom exceptions on missing entities
     *
     * @param exception Application exception
     *
     * @return A formatted 404 error
     */
    @ResponseBody
    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<?> handleUnknownMemberException(EntityNotFoundException exception) {
        log.error("NOT FOUND | {}", exception.getReason());

        return ResponseEntity.notFound()
                .build();
    }

    /**
     * Handle Hibernate validator exceptions
     * From: https://www.baeldung.com/spring-boot-bean-validation
     *
     * @param exception Hibernate validation exception
     *
     * @return The formatted 400 associated
     */
    @SuppressWarnings("ConstantConditions")
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult()
                .getAllErrors()
                .forEach((error) -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });

        log.error("VALIDATION | Intercepted validation errors for {} with messages : {}",
                exception.getBindingResult().getTarget().getClass(),
                errors);

        return ResponseEntity.badRequest()
                .body(errors);
    }

    /**
     * Handle Hibernate validator exceptions on single fields
     * Wrap each of them as: ["field": "reason", ...]
     *
     * @param exception Constraint validation exception
     *
     * @return The formatted 400 associated
     */
    @ResponseBody
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<?> handleValidationException(ConstraintViolationException exception) {
        Map<String, String> errors = Arrays.stream(exception
                .getMessage()
                .split(", "))
                .map(rawError -> {
                    String[] reasonAndMessages = rawError.split(": ");

                    String[] fieldIdentifiers = reasonAndMessages[0].split("\\.");
                    String field = fieldIdentifiers[fieldIdentifiers.length - 1];

                    String reason = reasonAndMessages[1];

                    return List.of(field, reason);
                })
                .collect(Collectors.toMap(
                        key -> key.get(0),
                        value -> value.get(1)));

        log.error("VALIDATION | Intercepted validation errors : {}", errors);

        return ResponseEntity.badRequest()
                .body(errors);
    }

}
