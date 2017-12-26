package com.nvn.svc;

import com.nvn.model.User;

import java.util.List;

public interface UserSvc {
    void create(User user);

    User getById(int id);

    User getByName(String username);

    List<User> getAll();

    void update(User user);

    void delete(User user);

    void deleteById(int id);
}
