package com.prazhmovska.vladyslava.wt_final_project.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * User details without hibernate relations. Used to avoid {@link org.hibernate.LazyInitializationException}
 * in cases when transaction has not been provided ({@link org.springframework.transaction.annotation.Transactional} has not been used).
 * Represents all details about searchable user and implements {@link UserDetails} needed by Spring Security.
 * Used in {@link org.springframework.security.core.context.SecurityContext} as a authentication principal.
 */
@Data
@AllArgsConstructor
public class UserDto implements UserDetails {

    private Long id;
    private String name;
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    /**
     * Basic for spring security method to get username. In this case it is email.
     */
    @JsonIgnore
    @Override
    public String getUsername() {
        return email;
    }

    /**
     * Returns all user authorities. Application does not support authorities, so
     * it returns just empty list.
     */
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    /**
     * Returns boolean is account non-expired. Application does not support user expiration, so
     * it returns just true.
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Returns boolean is account non-locked. Application does not support user locking, so
     * it returns just true.
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Returns boolean is credentials non-expired. Application does not support credentials expiration, so
     * it returns just true.
     */
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Returns boolean is account enable/disabling. Application does not support user enabling/disabling, so
     * it returns just true.
     */
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
