package com.nvn.cfg;

import javax.sql.DataSource;
import java.util.Properties;

public interface DataCfg {
    Properties hibernateProperties();

    DataSource dataSrc();
}
