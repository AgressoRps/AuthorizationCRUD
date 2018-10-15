package ru.starokozhev.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.starokozhev.model.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class UserDaoJdbcTemplateImpl implements IUserDao {
    private JdbcTemplate template;

    public UserDaoJdbcTemplateImpl(DataSource dataSource){
        this.template = new JdbcTemplate(dataSource);
    }

    /**
     * Метод получения из базы данных всех пользователей с указанным именем
     * @param nameUser имя пользователя, которого нужно найти
     * @return список пользователей соответствующих запросу
     */
    @Override
    public List<User> findAllByName(String nameUser) {
        //TODO реализовать функционал метода
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
        //TODO реализовать функционал метода
        return Optional.empty();
    }

    /**
     * Метод сохранения в базу данных подробной информации о пользователе
     * @param model экземпляр класса User, содержащий информацию о пользователе
     * Данные User сохраняются в бд.
     */
    @Override
    public void save(User model) {
        //TODO реализовать функционал метода
    }

    /**
     * Метод обновления информации указанного пользователя
     * @param model экземпляр класса User, содержащий новые данные, которые
     * необходимо обновить в бд.
     */
    @Override
    public void update(User model) {
        //TODO реализовать функционал метода
    }

    /**
     * Метод удаления пользователя по переданному идентификатору
     * @param id идентификатор пользователя, которого необходимо удалить
     */
    @Override
    public void delete(Integer id) {
        //TODO реализовать функционал метода
    }

    /**
     * Метод получения всех существующих пользователей в базе данных
     * @return список пользователей
     */
    @Override
    public List<User> findAll() {
        //TODO реализовать функционал метода
        return null;
    }

    /**
     * Метод закрытия соединения с базой данных
     */
    @Override
    public void close() {
        //TODO реализовать функционал метода
    }
}
