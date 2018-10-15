package ru.starokozhev.connection;

import javax.sql.DataSource;

public interface IConnectionJdbc {
    DataSource getDataSource();
}
