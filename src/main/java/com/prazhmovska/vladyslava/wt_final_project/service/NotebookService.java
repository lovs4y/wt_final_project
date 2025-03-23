package com.prazhmovska.vladyslava.wt_final_project.service;

import com.prazhmovska.vladyslava.wt_final_project.core.exceptions.AuthenticationException;
import com.prazhmovska.vladyslava.wt_final_project.core.exceptions.NotFoundException;
import com.prazhmovska.vladyslava.wt_final_project.model.Notebook;
import com.prazhmovska.vladyslava.wt_final_project.model.repository.NotebookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service class for managing notebooks.
 * Provides methods to perform CRUD operations on notebooks and interact with the {@link NotebookRepository}.
 */
@RequiredArgsConstructor
@Service
public class NotebookService extends AuthorizedContext {
    private final NotebookRepository notebookRepository;

    /**
     * Retrieves a list of all notebooks.
     *
     * @return a {@link List} of all {@link Notebook} entities
     */
    public List<Notebook> list() {
        return notebookRepository.findAllByUserId(
                getCurrentUserId()
        );
    }

    /**
     * Creates a new notebook.
     *
     * @param notebook the {@link Notebook} to create
     * @return the created {@link Notebook}.
     */
    public Notebook create(Notebook notebook) {
        notebook.setCreated(LocalDateTime.now());
        notebook.setModified(LocalDateTime.now());
        notebook.setUserId(getCurrentUserId());
        return notebookRepository.save(notebook);
    }

    /**
     * Retrieves a notebook by its ID.
     *
     * @param id the ID of the notebook
     * @return the {@link Notebook} with the given ID
     * @throws NotFoundException if no notebook is found with the given ID
     */
    public Notebook getById(Long id) {
        return notebookRepository.findByIdAndUserId(id, getCurrentUserId())
                .orElseThrow(() -> new NotFoundException("Notebook", "Notebook not found"));
    }

    /**
     * Retrieves a notebook by its name.
     *
     * @param name the name of the notebook
     * @return the {@link Notebook} with the given name
     */
    public Notebook getByName(String name) {
        return notebookRepository.findByNameAndUserId(name, getCurrentUserId());
    }

    /**
     * Updates an existing notebook.
     *
     * @param id       the ID of the notebook to update
     * @param notebook the updated {@link Notebook} information
     * @return the updated {@link Notebook}
     */
    public Notebook update(Long id, Notebook notebook) {
        notebook.setId(id);
        notebook.setModified(LocalDateTime.now());
        notebook.setUserId(getCurrentUserId());
        notebookRepository.findByIdAndUserId(id, getCurrentUserId())
                .orElseThrow(() -> new AuthenticationException("Forbidden"));
        return notebookRepository.save(notebook);
    }

    /**
     * Deletes a notebook by its ID.
     *
     * @param id the ID of the notebook to delete
     */
    public void delete(Long id) {
        notebookRepository.findByIdAndUserId(id, getCurrentUserId())
                .orElseThrow(() -> new AuthenticationException("Forbidden"));
        notebookRepository.deleteById(id);
    }
}
