package com.prazhmovska.vladyslava.wt_final_project.model.repository;

import com.prazhmovska.vladyslava.wt_final_project.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
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
    public Note findByTitle(String name);
}
