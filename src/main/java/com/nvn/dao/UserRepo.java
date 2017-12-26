package com.nvn.dao;

import com.nvn.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends JpaRepository<User, Integer> {
    @Query("select usr from User usr where usr.username= :usr")
    User findOneByUsername(@Param("usr") String username);
}
