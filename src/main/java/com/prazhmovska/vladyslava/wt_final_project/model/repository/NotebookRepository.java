package com.prazhmovska.vladyslava.wt_final_project.model.repository;

import com.prazhmovska.vladyslava.wt_final_project.model.Notebook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Repository interface for accessing {@link Notebook} entities.
 * Provides methods to perform CRUD operations and custom queries on the "notebooks" table.
 *
 * <p>This interface extends {@link JpaRepository}, which provides built-in methods such as save, findById, delete, etc.</p>
 */
@Repository
public interface NotebookRepository extends JpaRepository<Notebook, Long> {
    /**
     * Finds a notebook by its name.
     *
     * @param name the name of the notebook to search for
     * @return the {@link Notebook} with the specified name, or {@code null} if not found
     */
    Notebook findByName(String name);
}
