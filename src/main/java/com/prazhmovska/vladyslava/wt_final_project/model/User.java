package com.prazhmovska.vladyslava.wt_final_project.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "users")
@Data
@Entity
@NoArgsConstructor
/**
 * Represents a user entity in the database.
 * The user has an ID, name, email, and password.
 *
 * <p>This class is mapped to a database table (typically "user") and is used for storing
 * and retrieving user-related information.</p>
 */
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    /**
     * The password of the user.
     * This field holds the password, which is excluded from serialization using {@link JsonIgnore}.
     * It ensures that the password is not included in JSON responses.
     */
    @JsonIgnore
    private String password;

}


