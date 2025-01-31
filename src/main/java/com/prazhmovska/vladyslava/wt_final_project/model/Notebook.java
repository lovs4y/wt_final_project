package com.prazhmovska.vladyslava.wt_final_project.model;

import jakarta.persistence.*;
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
    private LocalDateTime created;

}
