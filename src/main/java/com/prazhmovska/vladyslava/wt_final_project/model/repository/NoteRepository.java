package com.prazhmovska.vladyslava.wt_final_project.model.repository;

import com.prazhmovska.vladyslava.wt_final_project.model.Note;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for accessing {@link Note} entities.
 * Provides methods to perform CRUD operations and custom queries on the "notes" table.
 *
 * <p>This interface extends {@link JpaRepository}</p>
 */
@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    /**
     * Finds a note by its title.
     *
     * @param title the title of the note to search for
     * @return the {@link Note} with the specified title, or {@code null} if not found
     */
    @Query(nativeQuery = true, value = "SELECT notes.id, notes.title, notes.created, notes.modified, notes.content, notes.notebook_id " +
            "FROM notes " +
            "INNER JOIN notebooks n on notes.notebook_id = n.id WHERE n.user_id = :userId AND notes.title = :title")
    List<Note> findByTitle(String title, Long userId);

    /**
     * Finds a note by its title, using userId
     *
     * @param userId id of notes owner
     * @return the {@link Note} with the specified title, or {@code null} if not found
     */
    @Query(nativeQuery = true, value = "SELECT notes.id, notes.title, notes.created, notes.modified, notes.content, notes.notebook_id " +
            "FROM notes " +
            "INNER JOIN notebooks n on notes.notebook_id = n.id WHERE n.user_id = :userId")
    List<Note> findAllByUserId(@Param("userId") Long userId);

    /**
     * Finds a note by its title.
     *
     * @param id of searching note
     * @param userId is a owner's id
     * @return the {@link Note} with the specified title, or {@code null} if not found
     */
    @Query(nativeQuery = true, value = "SELECT notes.id, notes.title, notes.created, notes.modified, notes.content, notes.notebook_id " +
            "FROM notes " +
            "INNER JOIN notebooks n on notes.notebook_id = n.id WHERE n.user_id = :userId AND notes.id = :id")
    Optional<Note> findById(@Param("id") Long id, @Param("userId") Long userId);
}
