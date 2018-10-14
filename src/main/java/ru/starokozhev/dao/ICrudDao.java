package ru.starokozhev.dao;

import java.util.List;
import java.util.Optional;

public interface ICrudDao<T> {
    Optional<T> find(Integer id);
    void save(T model);
    void update(T model);
    void delete(Integer id);
    void close();

    List<T> findAll();
}
