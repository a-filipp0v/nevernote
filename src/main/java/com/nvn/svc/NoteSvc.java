package com.nvn.svc;

import com.nvn.model.Label;
import com.nvn.model.Note;

import java.util.List;

public interface NoteSvc {
    void create(Note note);

    List<Note> findAllByTitle(String title);

    List<Note> findAllByLabel(Label label);

    void update(Note note);

    void delete(Note note);
}
