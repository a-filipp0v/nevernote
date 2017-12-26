package com.nvn.dao;

import com.nvn.model.Noteholder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoteholderRepo extends JpaRepository<Noteholder, Integer> {
    List<Noteholder> findAllByUserId(int userId);
    @Query("select nh from Noteholder nh where nh.name= :nh")
    Noteholder findByName(@Param("nh") String notepadName);
}
