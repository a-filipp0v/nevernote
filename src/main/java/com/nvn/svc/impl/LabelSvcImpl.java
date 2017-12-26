package com.nvn.svc.impl;

import com.nvn.dao.LabelRepo;
import com.nvn.model.Label;
import com.nvn.svc.LabelSvc;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LabelSvcImpl implements LabelSvc {

    @Autowired
    LabelRepo labelRepo;

    @Override
    public void create(Label label) {
        labelRepo.save(label);
    }

    @Override
    public Label findByName(String name) {
        return labelRepo.findOneByName(name);
    }

    @Override
    public void update(Label label) {
        labelRepo.saveAndFlush(label);
    }

    @Override
    public void delete(Label label) {
        labelRepo.delete(label);
    }


}
