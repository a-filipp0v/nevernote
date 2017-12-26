package com.nvn.dao;

import com.nvn.cfg.AppCfg;
import com.nvn.cfg.PersistenceCfg;
import com.nvn.dao.UserRepo;
import com.nvn.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.PersistenceException;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceCfg.class, AppCfg.class})
public class UserRepoTest {

    @Autowired
    UserRepo userRepo;

    @Before
    public void setUp() {
        assertNotNull(userRepo);
    }

    @Test
    public void createUserTest() {
        userRepo.save(new User().setUsername("name").setPassword("pass"));
        assertNotNull(userRepo.findOneByUsername("name"));

    }

    @Test
    public void findByNullIdTest() {
        assertFalse(userRepo.findById(Integer.MAX_VALUE).isPresent());
    }

    @Test(expected = PersistenceException.class)
    public void updateUsernameTest() {
        final User user = userRepo.getOne(1);
        userRepo.save(user.setUsername("name2"));
    }

    @Test
    public void deleteUserTest() {
        final User newUser = new User().setUsername("name3").setPassword("pass");
        final int id = userRepo.save(newUser).getId();
        assertTrue(userRepo.findById(id).isPresent());
        final User user = userRepo.getOne(id);
        userRepo.delete(user);
        assertFalse(userRepo.findById(id).isPresent());
    }

    @Test(expected = PersistenceException.class)
    public void deleteNullUserTest() {
        userRepo.delete(new User());
    }

    @Test
    public void deleteUserByIdTest() {
        final User newUser = new User().setUsername("name4").setPassword("pass");
        final int id = userRepo.save(newUser).getId();
        assertTrue(userRepo.findById(id).isPresent());
        userRepo.deleteById(id);
        assertFalse(userRepo.findById(id).isPresent());
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void deleteByNullIdTest() {
        userRepo.deleteById(Integer.MAX_VALUE);
    }
}
