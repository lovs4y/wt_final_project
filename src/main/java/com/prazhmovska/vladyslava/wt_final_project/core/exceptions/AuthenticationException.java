package com.prazhmovska.vladyslava.wt_final_project.core.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * An exception represents some authentication constraints violated.
 * Contains message which contains developer's comment about what exactly has been violated.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AuthenticationException extends RuntimeException {

    private String message;

    public AuthenticationException(String message) {
        this.message = message;
    }
}
