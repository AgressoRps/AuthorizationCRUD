package ru.starokozhev.dao;


import ru.starokozhev.model.User;

import java.util.List;

public interface UserDao extends CrudDao<User>{
    List<User> findAllByName(String nameUser);
}
