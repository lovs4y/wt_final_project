package com.prazhmovska.vladyslava.wt_final_project.service;

import com.prazhmovska.vladyslava.wt_final_project.core.exceptions.NotFoundException;
import com.prazhmovska.vladyslava.wt_final_project.model.Note;
import com.prazhmovska.vladyslava.wt_final_project.model.repository.NoteRepository;
import com.prazhmovska.vladyslava.wt_final_project.model.repository.NotebookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Service class for managing notes.
 * This service provides methods for CRUD operations on {@link Note} entities.
 * Interacts with {@link NoteRepository} to handle data access.
 */
@Service
public class NoteService {
    private final NoteRepository noteRepository;
   public NoteService(NoteRepository noteRepository) {
       this.noteRepository = noteRepository;
   }
    /**
     * Initializing
     */
   @Autowired
    public NoteService(NoteRepository noteRepository, NotebookRepository notebookRepository) {
       this.noteRepository = noteRepository;
   }
    /**
     * Retrieves all notes.
     *
     * <p>This method delegates the data retrieval to the {@link NoteRepository}. It returns all notes
     * stored in the database.</p>
     *
     * @return a list of all {@link Note} entities
     */
    public List<Note> list() {
        return noteRepository.findAll();
    }
    /**
     * Creates a new note.
     *
     * <p>Persists the given {@link Note} entity in the database by using the {@link NoteRepository}.
     *
     * @param note the {@link Note} entity to be saved
     * @return the saved {@link Note} entity
     */
    public Note create (Note note) {
        return noteRepository.save(note);
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
    public Note getById(long id) {
        return noteRepository.findById(id).orElseThrow(()->new NotFoundException("Note", "Note not found"));
    }
    /**
     * Gets a note by its title.
     *
     * <p>This method searches for a note with given title.
     * If no match is found, it returns {@code null}.</p>
     *
     * @param name  requested name
     * @return the {@link Note} entity with the specified title
     */
    public Note getByName(String name) {
        return noteRepository.findByTitle(name);
    }
    /**
     * Updates an existing note.
     *
     * <p>The note’s ID is set before saving it again using the repository.
     * Update affects the existing note, not creating a new one.</p>
     *
     * @param id the ID of the note to update
     * @param note the updated {@link Note} entity
     * @return the updated {@link Note} entity
     */
    public Note update(long id, Note note) {
        note.setId(id);
        return noteRepository.save(note);
    }
    /**
     * Deletes a note by its ID.
     *
     * <p>This method removes a note from the database using its ID.</p>
     * No return
     * @param id the ID of the note to delete
     */
    public void delete (long id){
       noteRepository.deleteById(id);
    }
}
