package io.github.thinkframework.jdbc.pdm;

import io.github.thinkframework.jdbc.xml.AbstractConnection;

import java.sql.*;
import java.util.Properties;

/**
 * Created by lixiaobin on 2017/4/25.
 */
class PDMConnection extends AbstractConnection implements Connection{

    PDMConnection(String url,Properties properties) {
        super(url,properties);
    }

    @Override
    public DatabaseMetaData getMetaData() {
        return new PDMDatabaseMetaData(url);
    }
}
