package com.academy.mobile.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionManager implements AutoCloseable {
    private static final String HOST = "jdbc.host";
    private static final String PORT = "jdbc.port";
    private static final String DB = "jdbc.db";
    private static final String USER = "jdbc.user";
    private static final String PASSWORD = "jdbc.password";
    private static final String PARAMS = "jdbc.params";

    private Connection conn;

    public ConnectionManager(Properties prop) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(String.format("jdbc:mysql://%s:%s/%s%s",
                prop.getProperty(HOST), prop.getProperty(PORT), prop.getProperty(DB), prop.getProperty(PARAMS)),
                prop.getProperty(USER), prop.getProperty(PASSWORD));
    }

    public Connection getConn() {
        return conn;
    }

    @Override
    public void close() throws Exception {
        if (conn != null) {
            conn.close();
        }
    }
}
