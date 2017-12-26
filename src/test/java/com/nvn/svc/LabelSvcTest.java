package com.nvn.svc;

import com.nvn.cfg.AppCfg;
import com.nvn.cfg.PersistenceCfg;
import com.nvn.dao.LabelRepo;
import com.nvn.model.Label;
import com.nvn.svc.LabelSvc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceCfg.class, AppCfg.class})
public class LabelSvcTest {

    @Autowired
    LabelSvc labelSvc;

    @Autowired
    LabelRepo labelRepo;

    @Test
    public void testCreateLabel() {
        final String name = "name1";
        Label label = new Label();
        label.setName(name);
        labelSvc.create(label);
        assertNotNull(labelRepo.findOneByName(name));
    }

    @Test
    public void testCreateLabelWithTheSameName() {
        final String name = "name2";
        Label label = new Label();
        label.setName(name);
        labelSvc.create(label);
        labelSvc.create(label);
    }
}