package com.nvn.svc;

import com.nvn.cfg.AppCfg;
import com.nvn.dao.UserRepo;
import com.nvn.model.User;
import com.nvn.svc.UserSvc;
import com.nvn.svc.impl.UserSvcImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = AppCfg.class)
public class UserSvcTest {

    private ConfigurableApplicationContext context;
    private UserSvc userSvc;

    @Mock
    private UserRepo userRepo;

    @Before
    public void before() {
        context = new ClassPathXmlApplicationContext("test.xml");
        userSvc=new UserSvcImpl(userRepo);
    }

    @Test
    public void create() {
        User user = (User) context.getBean("user");
        when(userRepo.save(user)).thenReturn(user);
        userSvc.create(user);
        verify(userRepo, times(1)).save(any());
    }

    @Test
    public void getAll() {
        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add((User) context.getBean("user"));
        expectedUsers.add((User) context.getBean("user2"));
        when(userRepo.findAll()).thenReturn(expectedUsers);
        List<User> actualUsers = userSvc.getAll();
        assertThat(actualUsers.size(), is(expectedUsers.size()));
        verify(userRepo, times(1)).findAll();
    }

    @Test
    public void update() {
        User user = (User) context.getBean("user");
        when(userRepo.existsById(anyInt())).thenReturn(true);
        when(userRepo.saveAndFlush(user)).thenReturn(user);
        userSvc.update(user);
        verify(userRepo, times(1)).saveAndFlush(any());
        verify(userRepo, times(1)).saveAndFlush(any());
    }

    @Test
    public void delete() throws Exception {
        User user = (User) context.getBean("user");
        userSvc.delete(user);
        verify(userRepo, times(1)).delete(any());
    }

    @Test
    public void deleteById() throws Exception {
        userSvc.deleteById(anyInt());
        verify(userRepo, times(1)).deleteById(any());
    }

    @Test
    public void getById() {
        int id = 1;
        User expected = (User) context.getBean("user");
        when(userRepo.findById(id)).thenReturn(Optional.of(expected));
        User actual = userSvc.getById(id);
        assertThat(actual, is(expected));
        verify(userRepo, times(1)).findById(any());
    }

    @Test
    public void getByName() {
        User expected = (User) context.getBean("user");
        when(userRepo.findOneByUsername(expected.getUsername())).thenReturn(expected);
        User actual = userSvc.getByName(expected.getUsername());
        assertThat(actual, is(expected));
        verify(userRepo, times(1)).findOneByUsername(any());
    }

}
