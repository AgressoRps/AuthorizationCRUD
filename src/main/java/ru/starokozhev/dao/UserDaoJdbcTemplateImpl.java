package ru.starokozhev.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.starokozhev.model.City;
import ru.starokozhev.model.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserDaoJdbcTemplateImpl implements IUserDao {
    private JdbcTemplate template;
    private Map<Integer, User> userMap = new HashMap<>();

    public UserDaoJdbcTemplateImpl(DataSource dataSource){
        this.template = new JdbcTemplate(dataSource);
    }

    /**
     * Лямбда выражение, обрабатывает результаты запросов к бд
     * возвращает пользователя
     */
    private RowMapper<User> userRowMapper = (ResultSet resultSet, int i) -> {
        Integer id = resultSet.getInt("id");
        if (!userMap.containsKey(id)){
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            Integer cityId = resultSet.getInt("city_id");
            String cityName = resultSet.getString("city_name");
            userMap.put(id, new User(id, name, email, password, new City(cityId, cityName)));
        }
        return userMap.get(id);
    };

    /**
     * Метод получения из базы данных всех пользователей с указанным именем
     * @param nameUser имя пользователя, которого нужно найти
     * @return список пользователей соответствующих запросу
     */
    @Override
    public List<User> findAllByName(String nameUser) {
        return template.query(SQL_SELECT_USER_BY_NAME, userRowMapper, ((nameUser).toLowerCase() + "%"));
    }

    /**
     * Метод поиска пользователя по указанному идентификатору
     * @param id идентификатор пользователя, которого нужно найти в базе данных
     * @return если пользователь найден, то создается, а затем возвращается новый
     * экземпляр класса User
     */
    @Override
    public Optional<User> find(Integer id) {
        template.query(SQL_SELECT_USER_BY_ID, userRowMapper, id);
        if (userMap.containsKey(id)) {
            return Optional.of(userMap.get(id));
        }
        return Optional.empty();
    }

    /**
     * Метод сохранения в базу данных подробной информации о пользователе
     * @param model экземпляр класса User, содержащий информацию о пользователе
     * Данные User сохраняются в бд.
     */
    @Override
    public void save(User model) {
        Object[] params = {model.getName(), model.getEmail(), model.getPassword(), model.getCity().getId()};
        int[] types = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER};
        template.update(SQL_INSERT_USER, params, types);
    }

    /**
     * Метод обновления информации указанного пользователя
     * @param model экземпляр класса User, содержащий новые данные, которые
     * необходимо обновить в бд.
     */
    @Override
    public void update(User model) {
        Object[] params = {model.getName(), model.getEmail(), model.getPassword(), model.getCity().getId()};
        int[] types = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER};
        template.update(SQL_UPDATE_USER, params, types);
    }

    /**
     * Метод удаления пользователя по переданному идентификатору
     * @param id идентификатор пользователя, которого необходимо удалить
     */
    @Override
    public void delete(Integer id) {
        Object[] params = {id};
        int[] types = {Types.INTEGER};
        template.update(SQL_DELETE_USER, params, types);
    }

    /**
     * Метод получения всех существующих пользователей в базе данных
     * @return список пользователей
     */
    @Override
    public List<User> findAll() {
        return template.query(SQL_SELECT_ALL_USERS_WITH_CITY, userRowMapper);
    }

    /**
     * Метод закрытия соединения с базой данных
     */
    @Override
    public void close() {
        //Template closed auto
    }
}
