package ru.starokozhev.connection;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.Properties;

public interface ILoader {
    Properties loadProperties(Properties properties, String name);
}
