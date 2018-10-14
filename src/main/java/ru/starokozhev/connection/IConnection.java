package ru.starokozhev.connection;

import javax.sql.DataSource;

public interface IConnection {
    DataSource getDataSource();
}
