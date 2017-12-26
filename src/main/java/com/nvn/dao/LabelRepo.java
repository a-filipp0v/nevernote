package com.nvn.dao;

import com.nvn.model.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LabelRepo extends JpaRepository<Label, Integer> {
    @Query("select lab from Label lab where lab.name= :lab")
    Label findOneByName(@Param("lab") String name);
}
