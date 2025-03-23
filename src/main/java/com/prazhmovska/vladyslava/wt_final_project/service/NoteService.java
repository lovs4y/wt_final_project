package com.prazhmovska.vladyslava.wt_final_project.service;

import com.prazhmovska.vladyslava.wt_final_project.core.exceptions.AuthenticationException;
import com.prazhmovska.vladyslava.wt_final_project.core.exceptions.NotFoundException;
import com.prazhmovska.vladyslava.wt_final_project.core.exceptions.ValidationException;
import com.prazhmovska.vladyslava.wt_final_project.model.Note;
import com.prazhmovska.vladyslava.wt_final_project.model.Notebook;
import com.prazhmovska.vladyslava.wt_final_project.model.repository.NoteRepository;
import com.prazhmovska.vladyslava.wt_final_project.model.repository.NotebookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service class for managing notes.
 * This service provides methods for CRUD operations on {@link Note} entities.
 * Interacts with {@link NoteRepository} to handle data access.
 */
@RequiredArgsConstructor
@Service
public class NoteService extends AuthorizedContext {
    private final NoteRepository noteRepository;
    private final NotebookRepository notebookRepository;

    /**
     * Retrieves all notes.
     *
     * <p>This method delegates the data retrieval to the {@link NoteRepository}. It returns all notes
     * stored in the database.</p>
     *
     * @return a list of all {@link Note} entities
     */
    public List<Note> list() {
        return noteRepository.findAllByUserId(getCurrentUserId());
    }

    /**
     * Creates a new note.
     *
     * <p>Persists the given {@link Note} entity in the database by using the {@link NoteRepository}.
     *
     * @param note the {@link Note} entity to be saved
     * @return the saved {@link Note} entity
     */
    public Note create(Note note) {
        if (note.getNotebookId() == null) {
            throw new ValidationException("Notebook id cannot be null");
        }
        note.setCreated(LocalDateTime.now());
        note.setModified(LocalDateTime.now());
        Note save = noteRepository.save(note);
        Notebook notebook = notebookRepository.findByIdAndUserId(
                        note.getNotebookId(),
                        getCurrentUserId())
                .orElseThrow(() -> new NotFoundException("Notebook", "Notebook not found"));
        notebook.getNotes().add(note);
        notebookRepository.save(notebook);
        return save;
    }

    /**
     * Retrieves a note by its ID.
     *
     * <p>If no note with the provided ID exists, throws a {@link NotFoundException}. This method
     * ensures that the application does not operate with non-existent entities.</p>
     *
     * @param id the ID of the note to retrieve
     * @return the {@link Note} entity with the specified ID
     * @throws NotFoundException if no note is found with the given ID
     */
    public Note getById(Long id) {
        return noteRepository.findById(id, getCurrentUserId())
                .orElseThrow(() -> new NotFoundException("Note", "Note not found"));
    }

    /**
     * Gets a note by its title.
     *
     * <p>This method searches for a note with given title.
     * If no match is found, it returns {@code null}.</p>
     *
     * @param name requested name
     * @return the {@link Note} entity with the specified title
     */
    public List<Note> getByName(String name) {
        return noteRepository.findByTitle(name, getCurrentUserId());
    }

    /**
     * Updates an existing note.
     *
     * <p>The note’s ID is set before saving it again using the repository.
     * Update affects the existing note, not creating a new one.</p>
     *
     * @param id   the ID of the note to update
     * @param note the updated {@link Note} entity
     * @return the updated {@link Note} entity
     */
    public Note update(Long id, Note note) {
        noteRepository.findById(id, getCurrentUserId()).orElseThrow(() -> new AuthenticationException("Forbidden"));
        note.setId(id);
        note.setModified(LocalDateTime.now());
        if (note.getNotebookId() == null) {
            throw new ValidationException("Notebook id cannot be null");
        }
        return noteRepository.save(note);
    }

    /**
     * Deletes a note by its ID.
     *
     * <p>This method removes a note from the database using its ID.</p>
     * No return
     *
     * @param id the ID of the note to delete
     */
    public void delete(Long id) {
        noteRepository.findById(id, getCurrentUserId()).orElseThrow(() -> new AuthenticationException("Forbidden"));
        noteRepository.deleteById(id);
    }
}
