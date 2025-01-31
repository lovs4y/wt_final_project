package com.prazhmovska.vladyslava.wt_final_project.web.exception;

import com.prazhmovska.vladyslava.wt_final_project.core.exceptions.NotFoundException;
import com.prazhmovska.vladyslava.wt_final_project.core.exceptions.ValidationException;
import com.prazhmovska.vladyslava.wt_final_project.model.dto.NotFoundDto;
import com.prazhmovska.vladyslava.wt_final_project.model.dto.ValidationErrorDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for managing exceptions thrown across the application.
 * This class provides exception handling, enabling the application to
 * respond with meaningful error messages and appropriate HTTP status codes.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
/**
 * Handles {@link NotFoundException} and returns a response with the
 * {@link NotFoundDto} containing the entity name and error comment.
 * The response is returned with HTTP status {@link HttpStatus#NOT_FOUND} (404).
 *
 * @param e the {@link NotFoundException} thrown
 */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException e) {
    return new ResponseEntity<>(new NotFoundDto(e.getEntityName(), e.getComment()), HttpStatus.NOT_FOUND);
    }
    /**
     * Handles {@link ValidationException} and returns a response with the
     * {@link ValidationErrorDto} containing the error comment.
     * The response is returned with HTTP status {@link HttpStatus#BAD_REQUEST} (400).
     *
     * @param e the {@link ValidationException} thrown
     */
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleValidationException(ValidationException e) {
        return new ResponseEntity<>(new ValidationErrorDto(e.getComment()), HttpStatus.BAD_REQUEST);
    }
}
