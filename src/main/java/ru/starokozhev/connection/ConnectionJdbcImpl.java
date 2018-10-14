package ru.starokozhev.connection;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Properties;

public class ConnectionJdbcImpl implements IConnection {
    private static final String PROPERTIES_NAME = "db.properties";
    private DataSource dataSource;
    private static volatile ConnectionJdbcImpl instance = null;

    /**
     * Конструктор класса инициализирует переменную dataSource
     */
    private ConnectionJdbcImpl(){
        dataSource = initDataSource();
    }

    /**
     * Метод регистрирует драйвер и конфигурирует объект DriverManagerDataSource
     * @return возвращает перменной экземпляра класса сконфигурированный объект DataSource
     */
    private DataSource initDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        Properties properties = new Properties();
        ILoader config = new LoaderProperties();
        properties = config.loadProperties(properties, PROPERTIES_NAME);
        dataSource.setUrl(properties.getProperty("db.url"));
        dataSource.setUsername(properties.getProperty("db.userName"));
        dataSource.setPassword(properties.getProperty("db.password"));
        dataSource.setDriverClassName(properties.getProperty("db.driverClassName"));
        return dataSource;
    }
    /**
     * Метод получения синхронизированного соединения с БД
     * @return передается объект содержащий соединение с бд
     */
    @Override
    public synchronized DataSource getDataSource(){
        return dataSource;
    }

    /**
     * Реализация шаблона Singleton (Double Checked Locking & volatile)
     * @return метод возвращает ссылку на объект ConnectionJdbcImpl
     */
    public static ConnectionJdbcImpl getInstance(){
        ConnectionJdbcImpl localInstance = instance;
        if (localInstance == null){
            synchronized (ConnectionJdbcImpl.class){
                localInstance = instance;
                if (localInstance == null){
                    instance = localInstance = new ConnectionJdbcImpl();
                }
            }
        }
        return localInstance;
    }
}
