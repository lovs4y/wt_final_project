package com.prazhmovska.vladyslava.wt_final_project.web;

import com.prazhmovska.vladyslava.wt_final_project.model.Note;
import com.prazhmovska.vladyslava.wt_final_project.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for managing notes.
 * Provides endpoints for CRUD operations on {@link Note} entities.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/notes")
public class NoteController {
    private final NoteService noteService;

    /**
     * Retrieves a list of all notes.
     *
     * @return a {@link List} of all {@link Note} entities
     */
    @GetMapping
    public List<Note> list() {
        return noteService.list();
    }

    /**
     * Retrieves a note by its ID.
     *
     * @param id the ID of the note to retrieve
     * @return the {@link Note} entity with the asked ID
     */
    @GetMapping("/{id}")
    public Note noteById(@PathVariable Long id) {
        return noteService.getById(id);
    }

    /**
     * Retrieves a note by its title.
     *
     * @param name the title of the note we ask for
     * @return the {@link Note} entity with the specified title
     */
    @GetMapping("/by-title")
    public List<Note> noteByName(@RequestParam String name) {
        return noteService.getByName(name);
    }

    /**
     * Creates a new note.
     *
     * @param note the {@link Note} entity to create
     * @return the created {@link Note} entity wow so boring
     */
    @PostMapping
    public Note create(@RequestBody Note note) {
        return noteService.create(note);
    }

    /**
     * Updates an existing note.
     *
     * @param note the updated {@link Note} entity
     * @param id   to update
     * @return the updated {@link Note} entity
     */
    @PutMapping("/{id}")
    public Note update(@RequestBody Note note, @PathVariable Long id) {
        return noteService.update(id, note);
    }

    /**
     * Deletes a note by its ID.
     *
     * @param id to delete
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        noteService.delete(id);
    }
}
