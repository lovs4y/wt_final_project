package com.prazhmovska.vladyslava.wt_final_project.core.exceptions;

import lombok.Data;

/**
 * Custom exception for handling "not found" errors.
 * Includes the name of the missing entity and a context comment.
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * throw new NotFoundException("User", "User with ID 1234 does not exist");
 * }</pre>
 */

@Data
public class NotFoundException extends RuntimeException {
    private String entityName;
    private String comment;
    /**
     * Constructs a new exception with the given entity name and comment.
     *
     * @param entityName name of the missing entity
     * @param comment additional context for the error
     */
    // if this is necessary
    public NotFoundException(String entityName, String comment) {
        this.entityName = entityName;
        this.comment = comment;
    }

}
