package ru.starokozhev.dao;


import ru.starokozhev.model.User;

import java.util.List;

public interface IUserDao extends ICrudDao<User> {
    List<User> findAllByName(String nameUser);
}
