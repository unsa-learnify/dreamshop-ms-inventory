package com.unsa.learnify.dreamshop.warehouses.application.ports.in.notes;

import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.NoteAmountException;
import com.unsa.learnify.dreamshop.warehouses.domain.exceptions.ProductNotFoundException;
import com.unsa.learnify.dreamshop.warehouses.domain.models.Note;

public interface CreateOneNoteServicePort {
    Note execute(Note note) throws ProductNotFoundException, NoteAmountException;
}
