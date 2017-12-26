package com.nvn.svc.impl;

import com.nvn.dao.NoteholderRepo;
import com.nvn.dao.UserRepo;
import com.nvn.model.Noteholder;
import com.nvn.model.User;
import com.nvn.svc.NoteholderSvc;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class NoteholderSvcImpl implements NoteholderSvc {

    private final NoteholderRepo noteholderRepo;
    private final UserRepo userRepo;

    @Override
    public void create(Noteholder noteholder) {
        User user = noteholder.getUser();
        user.addNoteholder(noteholder);
        userRepo.save(user);
    }

    @Override
    public Noteholder findByName(String name) {
        return noteholderRepo.findByName(name);
    }

    @Override
    public List<Noteholder> getAllByUser(User user) {
        return new ArrayList<>(user.getNoteholders());
    }

    @Override
    public void update(Noteholder noteholder) {
        noteholderRepo.saveAndFlush(noteholder);
    }

    @Override
    public void delete(Noteholder noteholder) {
        noteholderRepo.delete(noteholder);
    }
}

