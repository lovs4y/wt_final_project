package com.prazhmovska.vladyslava.wt_final_project.web;

import com.prazhmovska.vladyslava.wt_final_project.model.Notebook;
import com.prazhmovska.vladyslava.wt_final_project.service.AuthenticationHelper;
import com.prazhmovska.vladyslava.wt_final_project.service.NotebookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for managing notebooks.
 * Provides endpoints to perform CRUD operations on {@link Notebook} entities.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/notebooks")
public class NotebookController {
    private final NotebookService notebookService;

    /**
     * Gets a list of all notebooks.
     *
     * @return a {@link List} of all {@link Notebook} entities
     */
    @GetMapping
    public List<Notebook> allNotebooks() {
        return notebookService.list();
    }

    /**
     * Retrieves a notebook by its ID.
     *
     * @param id the ID of the notebook to fetch hehe
     * @return the {@link Notebook} entity with the specified ID
     */
    @GetMapping("/{id}")
    public Notebook notebookById(@PathVariable Long id) {
        return notebookService.getById(id);
    }

    /**
     * Retrieves a notebook by its name.
     *
     * @param name the name of the notebook to retrieve
     * @return the {@link Notebook} entity with the specified name
     */
    @GetMapping("/by-name")
    public Notebook notebookByName(@RequestParam String name) {
        return notebookService.getByName(name);
    }

    /**
     * Creates a new notebook.
     *
     * @param notebook the {@link Notebook} entity to create
     * @return the created {@link Notebook} entity
     */
    @PostMapping
    public Notebook create(@RequestBody Notebook notebook) {
        notebook.setUserId(AuthenticationHelper.getAuthenticationDetails().getId());
        return notebookService.create(notebook);
    }

    /**
     * Updates an existing notebook.
     *
     * @param notebook the updated {@link Notebook} entity
     * @param id       the ID of the notebook to update
     * @return the updated {@link Notebook} entity
     */
    @PutMapping("/{id}")
    public Notebook update(@RequestBody Notebook notebook, @PathVariable Long id) {
        return notebookService.update(id, notebook);
    }

    /**
     * Deletes a notebook by its ID.
     *
     * @param id the ID of deletet notebook
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        notebookService.delete(id);
    }
}
