package com.prazhmovska.vladyslava.wt_final_project.service;

import com.prazhmovska.vladyslava.wt_final_project.model.dto.UserDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * Helper contains static methods to retrieve authentication context and
 * principal details from {@link org.springframework.security.core.context.SecurityContext}.
 */
public class AuthenticationHelper {

    /**
     * Method getting principal from {@link org.springframework.security.core.context.SecurityContext} and casts it to UserDto.
     *
     * @return current user's details based on {@link org.springframework.security.core.context.SecurityContext}.
     */
    public static UserDto getAuthenticationDetails() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getPrincipal)
                .filter(UserDto.class::isInstance)
                .map(UserDto.class::cast)
                .orElseThrow(() -> new RuntimeException("Authentication Failed"));
    }

    /**
     * Reads authentication details and gets user's id.
     */
    public static Long getCurrentUserId() {
        return getAuthenticationDetails().getId();
    }
}
