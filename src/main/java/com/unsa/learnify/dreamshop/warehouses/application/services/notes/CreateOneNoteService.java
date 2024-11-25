package com.unsa.learnify.dreamshop.warehouses.application.services.notes;

import com.unsa.learnify.dreamshop.warehouses.application.ports.in.notes.CreateOneNoteServicePort;
import com.unsa.learnify.dreamshop.warehouses.application.ports.out.NotePersistencePort;
import com.unsa.learnify.dreamshop.warehouses.application.ports.out.ProductPersistencePort;
import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.NoteAmountException;
import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.ProductNotFoundException;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Note;

import org.springframework.stereotype.Service;

@Service
public class CreateOneNoteService implements CreateOneNoteServicePort {
    private final ProductPersistencePort productPersistencePort;
    private final NotePersistencePort notePersistencePort;
    public CreateOneNoteService(
        ProductPersistencePort productPersistencePort,
        NotePersistencePort notePersistencePort
    ) {
        this.productPersistencePort = productPersistencePort;
        this.notePersistencePort = notePersistencePort;
    }
    @Override
    public Note execute(Note note) throws ProductNotFoundException, NoteAmountException {
        if (note.getProduct() == null) {
            throw new ProductNotFoundException("Product was not provided");
        }
        if (note.getProduct().getId() == null || note.getProduct().getId() <= 0) {
            throw new ProductNotFoundException("Product id was not provided or is invalid");
        }
        if (!this.productPersistencePort.existsOneProductById(note.getProduct().getId())) {
            throw new ProductNotFoundException("Product with id " + note.getProduct().getId() + " was not found");
        }
        if (note.getAmount() == null || note.getAmount() <= 0) {
            throw new NoteAmountException("Amount must be greater than zero");
        }
        return this.notePersistencePort.createOneNote(note);
    }
}
