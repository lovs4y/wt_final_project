package com.prazhmovska.vladyslava.wt_final_project.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Represents a notebook entity in the database.
 * Mapped to the "notebooks" table in the database.
 *
 * <p>This class holds information about a notebook, including its unique identifier,
 * name, and creation timestamp.</p>
 */
@Data
@Entity
@Table(name = "notebooks")
public class Notebook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime created;

}
