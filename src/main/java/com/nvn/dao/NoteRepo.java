package com.nvn.dao;

import com.nvn.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoteRepo extends JpaRepository<Note, Integer> {
    @Query("select nt from Note nt where nt.title= :nt")
    List<Note> findAllByTitle(@Param("nt") String noteTitle);
}
