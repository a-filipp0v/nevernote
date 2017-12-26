package com.nvn.dao;

import com.nvn.cfg.AppCfg;
import com.nvn.dao.NoteholderRepo;
import com.nvn.dao.UserRepo;
import com.nvn.model.Noteholder;
import com.nvn.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppCfg.class})

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class NoteholderRepoTest {

    private static final int DEFAULT_ID = 1;
    private static final int NOT_EXISTENT_ID = 99;
    @Autowired
    private ApplicationContext appContext;

    @Autowired
    private NoteholderRepo noteholderRepo;

    @Autowired
    private UserRepo userRepo;

    private ConfigurableApplicationContext configurableApplicationContext;
    private Noteholder noteholder;
    private User user;

    @Before
    public void setUp() {
        configurableApplicationContext = new ClassPathXmlApplicationContext("test.xml");
        noteholder = configurableApplicationContext.getBean(Noteholder.class);
        user = noteholder.getUser();
        user.addNoteholder(noteholder);
        userRepo.save(user);
    }

    @Test
    public void testUserRepo() {
        assertNotNull("appContext != null", appContext);
        assertNotNull("userRepo != null", noteholderRepo);
    }

    @Test(expected = NoSuchElementException.class)
    public void testNoFindById() {
        noteholderRepo.findById(NOT_EXISTENT_ID).get();
    }

    @Test
    public void testFindById() {
        assertNotNull(noteholderRepo.findAllByUserId(user.getId()));
    }

    @Test
    public void testFindByName() {
        assertNotNull(noteholderRepo.findByName(noteholder.getName()));
        assertEquals(noteholder, noteholderRepo.findByName(noteholder.getName()));
    }

    @Test
    public void testUpdate() {
        Noteholder noteholder = noteholderRepo.findAllByUserId(user.getId()).get(0);
        noteholder.setName("upd");
        noteholderRepo.saveAndFlush(noteholder);
        assertEquals("upd", noteholderRepo.findAllByUserId(user.getId()).get(0).getName());
    }

    @Test
    public void testDelete() {
        List<Noteholder> noteholders = noteholderRepo.findAllByUserId(DEFAULT_ID);
        int before = noteholders.size();
        assertTrue(before > 0);
        noteholderRepo.delete(noteholders.get(0));
        int after = noteholderRepo.findAllByUserId(DEFAULT_ID).size();
        assertTrue(after < before);
    }
}
