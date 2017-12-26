package com.nvn.svc;

import com.nvn.model.Label;

public interface LabelSvc {
    void create(Label label);

    Label findByName(String name);

    void update(Label label);

    void delete(Label label);
}
