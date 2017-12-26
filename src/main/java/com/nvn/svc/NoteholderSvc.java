package com.nvn.svc;

import com.nvn.model.Noteholder;
import com.nvn.model.User;

import java.util.List;

public interface NoteholderSvc {
    void create(Noteholder noteholder);

    Noteholder findByName(String name);

    List<Noteholder> getAllByUser(User user);

    void update(Noteholder noteholder);

    void delete(Noteholder noteholder);
}
