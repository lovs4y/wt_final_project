package com.prazhmovska.vladyslava.wt_final_project.core.exceptions;

import lombok.Data;
/**
 * Custom exception for handling validation errors.
 * Includes a comment describing the error.
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * throw new ValidationException("Invalid input data");
 * }</pre>
 */
@Data
public class ValidationException extends RuntimeException {
    private String comment;

    /**
     * Constructs a new exception with the given comment.
     *
     * @param comment description of the validation error
     */

    public ValidationException(String comment) {
        this.comment = comment;
    }
}
