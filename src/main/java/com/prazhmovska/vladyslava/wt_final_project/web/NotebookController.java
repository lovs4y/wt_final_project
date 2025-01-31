package com.prazhmovska.vladyslava.wt_final_project.web;

import com.prazhmovska.vladyslava.wt_final_project.model.Notebook;
import com.prazhmovska.vladyslava.wt_final_project.service.NotebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * REST controller for managing notebooks.
 * Provides endpoints to perform CRUD operations on {@link Notebook} entities.
 */
@RestController
@RequestMapping("/notebooks")
public class NotebookController {
    private final NotebookService notebookService;
    /**
     * @param notebookService calls back to {@link NotebookController} used to manage notebooks
     */
    @Autowired
    public NotebookController(NotebookService notebookService) {
        this.notebookService = notebookService;
    }
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
    public Notebook notebookById(@PathVariable long id) {
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
        return notebookService.create(notebook);
    }
    /**
     * Updates an existing notebook.
     *
     * @param notebook the updated {@link Notebook} entity
     * @param id the ID of the notebook to update
     * @return the updated {@link Notebook} entity
     */
    @PutMapping("/{id}")
    public Notebook update(@RequestBody Notebook notebook, @PathVariable long id) {
        return notebookService.update(id, notebook);
    }
    /**
     * Deletes a notebook by its ID.
     *
     * @param id the ID of deletet notebook
     */
    @DeleteMapping("/{id}")
    public void delete (@PathVariable long id) {
        notebookService.delete(id);
    }
}
