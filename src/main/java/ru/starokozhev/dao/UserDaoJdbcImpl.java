package ru.starokozhev.dao;

import org.apache.log4j.Logger;
import ru.starokozhev.model.City;
import ru.starokozhev.model.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class UserDaoJdbcImpl implements IUserDao {
    //экземпляр класса Logger, производит логирование в файл
    private static final Logger log = Logger.getLogger(UserDaoJdbcImpl.class);
    private Connection connection = null;

    public UserDaoJdbcImpl(DataSource dataSource){
        try {
            this.connection = dataSource.getConnection();
        } catch (SQLException e) {
            log.error("Error connecting with Database");
            e.printStackTrace();
        }
    }

    /**
     * Метод получения из базы данных всех пользователей с указанным именем
     * @param nameUser имя пользователя, которого нужно найти
     * @return список пользователей соответствующих запросу
     */
    @Override
    public List<User> findAllByName(String nameUser) {
        log.info("Find users by name!");
        List<User> users = new ArrayList<>();
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_NAME)) {
            preparedStatement.setString(1, nameUser + "%");
            resultSet = preparedStatement.executeQuery();
            parseResultSet(users, resultSet);
        }catch (SQLException ex){
            log.error("Error read from database");
            ex.printStackTrace();
        }finally {
            closeResultSet(resultSet);
            close();
        }
        return users;
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
        ArrayList<User> users = new ArrayList<>();
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_ID)){
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            parseResultSet(users, resultSet);
        }catch (SQLException ex){
            log.error("Error read from database");
            ex.printStackTrace();
        }finally {
            closeResultSet(resultSet);
            close();
        }
        if (users.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(users.get(0));
    }

    /**
     * Метод сохранения в базу данных подробной информации о пользователе
     * @param model экземпляр класса User, содержащий информацию о пользователе
     * Данные User сохраняются в бд.
     */
    @Override
    public void save(User model) {
        log.info("Save user model!");
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_USER)){
            preparedStatement.setString(1, model.getName());
            preparedStatement.setString(2, model.getEmail());
            preparedStatement.setString(3, model.getPassword());
            preparedStatement.setInt(4, model.getCity().getId());
            preparedStatement.executeUpdate();
        }catch (SQLException ex){
            log.error("Error insert user to Database");
            ex.printStackTrace();
        }finally {
            close();
        }
    }

    /**
     * Метод обновления информации указанного пользователя
     * @param model экземпляр класса User, содержащий новые данные, которые
     * необходимо обновить в бд.
     */
    @Override
    public void update(User model) {
        log.info("Update user model!");
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER)){
            preparedStatement.setString(1, model.getName());
            preparedStatement.setString(2, model.getEmail());
            preparedStatement.setString(3, model.getPassword());
            preparedStatement.setInt(4, model.getCity().getId());
            preparedStatement.setInt(5, model.getId());
            preparedStatement.executeUpdate();
        }catch (SQLException ex){
            log.error("Error updating user");
            ex.printStackTrace();
        }finally {
            close();
        }
    }

    /**
     * Метод удаления пользователя по переданному идентификатору
     * @param id идентификатор пользователя, которого необходимо удалить
     */
    @Override
    public void delete(Integer id) {
        log.info("Delete user by id!");
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_USER)){
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }catch (SQLException ex){
            log.error("Error delete user");
            ex.printStackTrace();
        }finally {
            close();
        }
    }

    /**
     * Метод получения всех существующих пользователей в базе данных
     * @return список пользователей
     */
    @Override
    public List<User> findAll() {
        log.info("Find all users!");
        List<User> users = new ArrayList<>();
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_USERS_WITH_CITY)) {
            parseResultSet(users, resultSet);
        }catch (SQLException ex){
            log.error("Error read from database");
            ex.printStackTrace();
        }finally {
            close();
        }
        return users;
    }

    /**
     * Метод закрытия соединения с базой данных
     */
    @Override
    public void close() {
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("Error closing connection");
                e.printStackTrace();
            }
        }
    }

    private void closeResultSet(ResultSet resultSet){
        if (resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                log.error("Error closing result");
                e.printStackTrace();
            }
        }
    }

    /**
     * Метод получения результатов выполнения запроса, преобразования их в Объекты User и City
     * и добавления в коллекцию пользователей
     * @param users пустая коллекция пользователей
     * @param resultSet результат запроса в бд
     * @throws SQLException метод может генерировать исключение при работе с курсором ResultSet
     */
    private void parseResultSet(List<User> users, ResultSet resultSet) throws SQLException {
        while (resultSet.next()){
            Integer id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            Integer cityId = resultSet.getInt("city_id");
            String cityName = resultSet.getString("city_name");
            users.add(new User(id, name, email, password, new City(cityId, cityName)));
        }
    }
}
