package com.nvn.svc.impl;

import com.nvn.dao.UserRepo;
import com.nvn.model.User;
import com.nvn.svc.UserSvc;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserSvcImpl implements UserSvc {

    private final UserRepo userRepo;

    @Override
    public void create(User user) {
        userRepo.save(user);
    }

    @Override
    public User getById(int id) {
        return userRepo.findById(id).get();
    }

    @Override
    public User getByName(String username) {
        return userRepo.findOneByUsername(username);
    }

    @Override
    public List<User> getAll() {
        return userRepo.findAll();
    }

    @Override
    public void update(User user) {
        if (userRepo.existsById(user.getId())) {
            userRepo.saveAndFlush(user);
        }
    }

    @Override
    public void delete(User user) {
        userRepo.delete(user);
    }

    @Override
    public void deleteById(int id) {
        userRepo.deleteById(id);
    }
}

