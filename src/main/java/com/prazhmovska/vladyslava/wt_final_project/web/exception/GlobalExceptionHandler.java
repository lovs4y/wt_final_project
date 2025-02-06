package com.prazhmovska.vladyslava.wt_final_project.web.exception;

import com.prazhmovska.vladyslava.wt_final_project.core.exceptions.NotFoundException;
import com.prazhmovska.vladyslava.wt_final_project.core.exceptions.ValidationException;
import com.prazhmovska.vladyslava.wt_final_project.model.dto.NotFoundDto;
import com.prazhmovska.vladyslava.wt_final_project.model.dto.ValidationErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
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

    /**
     * Handles {@link AuthenticationException} by returning a {@link ResponseEntity} with a validation error message.
     *
     * <p>This method is invoked when an {@link AuthenticationException} is thrown, and it returns a {@link ResponseEntity}
     * with an appropriate error message wrapped in a {@link ValidationErrorDto} and a {@link HttpStatus#UNAUTHORIZED} status.</p>
     *
     * @param e The {@link AuthenticationException} that was thrown.
     * @return A {@link ResponseEntity} containing the error message and a 401 Unauthorized status.
     */
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException e) {
        return new ResponseEntity<>(new ValidationErrorDto(e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handles {@link InternalAuthenticationServiceException} by returning a {@link ResponseEntity} with a validation error message.
     *
     * <p>This method is invoked when an {@link InternalAuthenticationServiceException} is thrown, and it returns a {@link ResponseEntity}
     * with an appropriate error message wrapped in a {@link ValidationErrorDto} and a {@link HttpStatus#BAD_REQUEST} status.</p>
     *
     * @param e The {@link InternalAuthenticationServiceException} that was thrown.
     * @return A {@link ResponseEntity} containing the error message and a 401 Unauthorized status.
     */
    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<Object> handleAuthenticationException(InternalAuthenticationServiceException e) {
        if (e.getCause() instanceof NotFoundException) {
            return handleNotFoundException((NotFoundException) e.getCause());
        }

        return new ResponseEntity<>(new ValidationErrorDto(e.getCause().getMessage()), HttpStatus.BAD_REQUEST);
    }
}
