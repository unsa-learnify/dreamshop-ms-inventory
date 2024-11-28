package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.out.persistence.implementations;

import com.unsa.learnify.dreamshop.warehouses.application.ports.out.NotePersistencePort;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Note;
import com.unsa.learnify.dreamshop.warehouses.domain.models.NoteFilters;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteMySQLPersistenceAdapter implements NotePersistencePort {

    @Override
    public Note createOneNote(Note note) {
        return null;
    }

    @Override
    public List<Note> findNotesByFilters(NoteFilters noteFilters) {
        return List.of();
    }

    @Override
    public Optional<Note> findOneNoteById(Integer noteId) {
        return Optional.empty();
    }

    @Override
    public Boolean existsOneNoteById(String noteId) {
        return null;
    }

    @Override
    public void updateOneNoteById(Integer noteId, Note note) {

    }

    @Override
    public void deleteOneNoteById(Integer noteId) {

    }
}
