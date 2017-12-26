package com.nvn.svc.impl;

import com.nvn.dao.NoteRepo;
import com.nvn.dao.UserRepo;
import com.nvn.model.Label;
import com.nvn.model.Note;
import com.nvn.svc.NoteSvc;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@AllArgsConstructor
public class NoteSvcImpl implements NoteSvc {

    private NoteRepo noteRepo;
    private UserRepo userRepo;

    @Override
    public void create(Note note) {
        noteRepo.save(note);
    }

    @Override
    public List<Note> findAllByTitle(String title) {
        return noteRepo.findAllByTitle(title);
    }


    @Override
    public List<Note> findAllByLabel(Label label) {
        LinkedList<Note> notes = new LinkedList<>();
        notes.addAll(label.getNotes());
        return notes;
    }

    @Override
    public void update(Note note) {
        userRepo.saveAndFlush(note.getNoteholder().getUser());
    }

    @Override
    public void delete(Note note) {
        noteRepo.delete(note);
    }
}
