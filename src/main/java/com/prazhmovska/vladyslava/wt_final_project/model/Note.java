package com.prazhmovska.vladyslava.wt_final_project.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
/**
 * Entity representing a note in the database.
 * Mapped to the "notes" table.
 */
@Table(name = "notes")
@Data
@Entity
@NoArgsConstructor
public class Note {
    /**
     * Has characteristics such as Title, createdAt - what time was it created, modifiedAt -
     * what time it was modified last, and content.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private LocalDateTime createdAt; //created_at TIMESTAMP
    private LocalDateTime modifiedAt;
    private String content;
}
