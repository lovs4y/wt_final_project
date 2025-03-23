package com.prazhmovska.vladyslava.wt_final_project.model.repository;

import com.prazhmovska.vladyslava.wt_final_project.model.Note;
import com.prazhmovska.vladyslava.wt_final_project.model.Notebook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
     * @param userId an id of owner
     * @return the {@link Notebook} with the specified name, or {@code null} if not found
     */
    Notebook findByNameAndUserId(String name, Long userId);

    /**
     * Finds all notebooks by user id.
     *
     * @param userId an id of owner
     * @return the {@link Notebook} with the specified name, or {@code null} if not found
     */
    @Query(value = "SELECT * FROM notebooks WHERE user_id = :userId", nativeQuery = true)
    List<Notebook> findAllByUserId(@Param("userId") Long userId);

    /**
     * Finds a notebook by id.
     *
     * @param id of searchable notebook
     * @param userId an id of owner
     * @return the {@link Notebook} with the specified name, or {@code null} if not found
     */
    Optional<Notebook> findByIdAndUserId(Long id, Long userId);
}
