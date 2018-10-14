package ru.starokozhev.dao;


import ru.starokozhev.model.User;

import java.util.List;

public interface IUserDao extends ICrudDao<User> {

    List<User> findAllByName(String nameUser);

    //SQL language
    String SQL_SELECT_ALL_USERS_WITH_CITY = "SELECT user_info.*, city.city_name " +
            "FROM user_info LEFT JOIN city ON user_info.city_id = city.id";
    //SQL language
    String SQL_SELECT_USER_BY_NAME = "SELECT user_info.*, city.city_name " +
            "FROM user_info LEFT JOIN city ON user_info.city_id = city.id " +
            "WHERE LOWER (user_info.name) LIKE ?";
    //SQL language
    String SQL_SELECT_USER_BY_ID = "SELECT user_info.*, city.city_name " +
            "FROM user_info LEFT JOIN city ON user_info.city_id = city.id " +
            "WHERE user_info.id = ?";
    //SQL language
   String SQL_INSERT_USER = "INSERT INTO user_info(name, email, password, city_id) " +
            "VALUES(?, ?, ?, ?)";
    //SQL language
    String SQL_UPDATE_USER = "UPDATE user_info SET name = ?, " +
            "email = ?, password = ?, city_id = ? WHERE id = ?";
    //SQL language
    String SQL_DELETE_USER = "DELETE FROM user_info WHERE id = ?";
}
