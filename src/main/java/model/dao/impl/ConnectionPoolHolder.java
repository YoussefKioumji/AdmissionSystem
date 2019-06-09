package model.dao.impl;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConnectionPoolHolder {
    private static volatile DataSource dataSource;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    try {
                        Properties properties = new Properties();
                        properties.load(new FileInputStream("D:\\Study\\Project\\AdmissionSystem\\src\\main\\resources\\db.properties"));
                        Class.forName(properties.getProperty("db.connection.driver"));
                        BasicDataSource bds = new BasicDataSource();
                        bds.setUrl(properties.getProperty("db.connection.url"));
                        bds.setUsername(properties.getProperty("db.connection.username"));
                        bds.setPassword(properties.getProperty("db.connection.password"));
                        bds.setMinIdle(10);
                        bds.setMaxIdle(20);
                        bds.setMaxOpenPreparedStatements(100);
                        dataSource = bds;
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return dataSource;
    }
}