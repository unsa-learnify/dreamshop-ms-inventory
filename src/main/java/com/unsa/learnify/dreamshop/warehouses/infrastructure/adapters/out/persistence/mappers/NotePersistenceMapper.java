package com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.out.persistence.mappers;

import com.unsa.learnify.dreamshop.warehouses.domain.models.Note;
import com.unsa.learnify.dreamshop.warehouses.infrastructure.adapters.out.persistence.entities.NoteEntity;

public class NotePersistenceMapper {
    public NotePersistenceMapper() {}
    public static NoteEntity domainToEntity(Note note) {
        return NoteEntity.builder()
            .id(null)
            .title(note.getTitle())
            .description(note.getDescription())
            .movement(note.getMovement())
            .amount(note.getAmount())
            .build();
    }
    public static Note entityToDomain(NoteEntity noteEntity) {
        return Note.builder()
            .id(noteEntity.getId())
            .title(noteEntity.getTitle())
            .description(noteEntity.getDescription())
            .movement(noteEntity.getMovement())
            .amount(noteEntity.getAmount())
            .createdAt(noteEntity.getCreatedAt())
            .updatedAt(noteEntity.getUpdatedAt())
            .build();
    }
}
