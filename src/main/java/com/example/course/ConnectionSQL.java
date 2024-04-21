package com.example.course;

import org.postgresql.ds.PGSimpleDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionSQL {
    private static Connection INSTANCE;
    public ConnectionSQL() throws SQLException {
    }
    public static Connection getInstance() throws SQLException {
        if (INSTANCE == null)
        {
            PGSimpleDataSource dataSource = new PGSimpleDataSource();
            dataSource.setUser("postgres");
            dataSource.setPassword("12345");
            dataSource.setDatabaseName("Employees_VUZ");
            INSTANCE = dataSource.getConnection();
        }
        return INSTANCE;
    }
}
