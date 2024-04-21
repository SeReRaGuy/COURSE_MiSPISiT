package com.example.course;

import org.postgresql.ds.PGSimpleDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionSQL {
    public static Connection doConnect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUser("postgres");
        dataSource.setPassword("12345");
        dataSource.setDatabaseName("Employees_VUZ");
        return dataSource.getConnection();
    }
}
