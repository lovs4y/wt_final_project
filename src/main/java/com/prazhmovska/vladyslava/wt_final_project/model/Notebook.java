package com.prazhmovska.vladyslava.wt_final_project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
@NoArgsConstructor
@AllArgsConstructor
public class Notebook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime created;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime modified;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "user_id")
    private Long userId;

    /**
     * One-to-many relation with {@link Note}s.
     * <br>
     * Entities joined by <code>notebook_id</code>.
     * <br>
     * Specified cascade type as {@link CascadeType#ALL}.
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "notebook_id")
    private List<Note> notes = new ArrayList<>();
}
