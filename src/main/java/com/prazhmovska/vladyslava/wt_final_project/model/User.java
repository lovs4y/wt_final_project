package com.prazhmovska.vladyslava.wt_final_project.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Represents a user entity in the database.
 * The user has an ID, name, email, and password.
 *
 * <p>This class is mapped to a database table (typically "user") and is used for storing
 * and retrieving user-related information.</p>
 */
@Table(name = "users")
@Data
@Entity
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    /**
     * The password of the user.
     * This field holds the password, which is excluded from serialization using {@link JsonIgnore}.
     * It ensures that the password is not included in JSON responses.
     * Password is encrypted by {@link org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder}.
     */
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


