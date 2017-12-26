package com.nvn.svc;

import com.nvn.cfg.AppCfg;
import com.nvn.dao.NoteholderRepo;
import com.nvn.dao.UserRepo;
import com.nvn.model.Noteholder;
import com.nvn.model.User;
import com.nvn.svc.impl.NoteholderSvcImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = AppCfg.class)
public class NoteholderSvcTest {

    @Mock
    NoteholderRepo noteholderRepo;
    @Mock
    UserRepo userRepo;
    @Mock
    User user;


    private ConfigurableApplicationContext ctx;
    private NoteholderSvcImpl noteholderSvc;


    @Before
    public void before() {
        ctx = new ClassPathXmlApplicationContext("test.xml");
        noteholderSvc = new NoteholderSvcImpl(noteholderRepo, userRepo);
    }

    @Test
    public void create() {
        Noteholder noteholder = (Noteholder) ctx.getBean("noteholder");
        noteholderSvc.create(noteholder);
        verify(userRepo, times(1)).save(any());
    }

    @Test
    public void delete() {
        Noteholder noteholder = (Noteholder) ctx.getBean("noteholder");
        noteholderSvc.delete(noteholder);
        verify(noteholderRepo, times(1)).delete(any());
    }

    @Test
    public void update() {
        Noteholder noteholder = (Noteholder) ctx.getBean("noteholder");
        noteholderSvc.update(noteholder);
        verify(noteholderRepo, times(1)).saveAndFlush(any());
    }

    @Test
    public void getAllByUser() {
        noteholderSvc.getAllByUser(user);
        verify(user, times(1)).getNoteholders();
    }

    @Test
    public void findByName() {
        Noteholder noteholder = (Noteholder) ctx.getBean("noteholder");
        noteholderSvc.findByName(noteholder.getName());
        verify(noteholderRepo, times(1)).findByName(any());
    }
}