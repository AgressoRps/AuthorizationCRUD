package ru.starokozhev.dao;

import org.apache.log4j.Logger;
import ru.starokozhev.model.User;

import java.util.List;
import java.util.Optional;

public class UserDaoJdbcImpl implements UserDao{
    //SQL language
    private static final String SQL_SELECT_ALL_USERS_WITH_CITY = "SELECT user_info.id, user_info.name, " +
            "user_info.email, user_info.password, city.city_name " +
            "FROM user_info " +
            "LEFT JOIN city ON user_info.city_id = city.id";

    //SQL language
    private static final String SQL_SELECT_USER_BY_NAME_WITH_CITY = "SELECT user_info.id, user_info.name, " +
            "user_info.email, user_info.password, city.city_name " +
            "FROM user_info LEFT JOIN city ON user_info.city_id = city.id " +
            "WHERE LOWER(user_info.name) LIKE '?'";

    //экземпляр класса Logger, производит логирование в файл
    private static final Logger log = Logger.getLogger(UserDaoJdbcImpl.class);

    /**
     * Метод получения из базы данных всех пользователей с указанным именем
     * @param nameUser имя пользователя, которого нужно найти
     * @return список пользователей соответствующих запросу
     */
    @Override
    public List<User> findAllByName(String nameUser) {
        log.info("Find users by name!");
        //TODO Реализовать функицонал метода
        return null;
    }

    /**
     * Метод поиска пользователя по указанному идентификатору
     * @param id идентификатор пользователя, которого нужно найти в базе данных
     * @return если пользователь найден, то создается, а затем возвращается новый
     * экземпляр класса User
     */
    @Override
    public Optional<User> find(Integer id) {
        log.info("Find user by id!");
        //TODO Реализовать функицонал метода
        return Optional.empty();
    }

    /**
     * Метод сохранения в базу данных подробной информации о пользователе
     * @param model экземпляр класса User, содержащий информацию о пользователе
     * Данные User сохраняются в бд.
     */
    @Override
    public void save(User model) {
        log.info("Save user model!");
        //TODO Реализовать функицонал метода
    }

    /**
     * Метод обновления информации указанного пользователя
     * @param model экземпляр класса User, содержащий новые данные, которые
     * необходимо обновить в бд.
     */
    @Override
    public void update(User model) {
        log.info("Update user model!");
        //TODO Реализовать функицонал метода
    }

    /**
     * Метод удаления пользователя по переданному идентификатору
     * @param id идентификатор пользователя, которого необходимо удалить
     */
    @Override
    public void delete(Integer id) {
        log.info("Delete user by id!");
        //TODO Реализовать функицонал метода
    }

    /**
     * Метод получения всех существующих пользователей в базе данных
     * @return список пользователей
     */
    @Override
    public List findAll() {
        log.info("Find all users!");
        //TODO Реализовать функицонал метода
        return null;
    }
}
