package com.unsa.learnify.dreamshop.warehouses.application.ports.out;

import com.unsa.learnify.dreamshop.warehouses.domain.models.Note;
import com.unsa.learnify.dreamshop.warehouses.domain.models.NoteFilters;

import java.util.List;
import java.util.Optional;

public interface NotePersistencePort {
    Note createOneNote(Note note);
    List<Note> findNotesByFilters(NoteFilters noteFilters);
    Optional<Note> findOneNoteById(Integer noteId);
    Boolean existsOneNoteById(String noteId);
    void updateOneNoteById(Integer noteId, Note note);
    void deleteOneNoteById(Integer noteId);
}
