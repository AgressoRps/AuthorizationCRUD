package ru.starokozhev.connection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.starokozhev.model.City;
import ru.starokozhev.model.User;

import java.util.Properties;

public class ConnectionHibernate {
    private static final String PROPERTIES_NAME = "hb.properties";
    private SessionFactory sessionFactory;
    private static volatile ConnectionHibernate instance = null;

    /**
     * Конструктор класса инициализирует переменную sessionFactory
     */
    private ConnectionHibernate(){
        sessionFactory = initSessionFactory();
    }

    /**
     * Метод конфигурирует и строит SessionFactory
     * @return возвращает перменной экземпляра класса сконфигурированный объект SessionFactory
     */
    private SessionFactory initSessionFactory(){
        Configuration configuration = new Configuration();
        Properties properties = new Properties();
        LoaderProperties loaderProperties = new LoaderProperties();
        properties = loaderProperties.loadProperties(properties, PROPERTIES_NAME);
        configuration.setProperties(properties);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(City.class);
        return configuration.buildSessionFactory();
    }

    /**
     * Метод получения сессии соединения с БД
     * @return передается объект содержащий соединение с бд
     */
    public synchronized Session getSession(){
        return sessionFactory.openSession();
    }

    /**
     * Реализация шаблона Singleton (Double Checked Locking & volatile)
     * @return метод возвращает ссылку на объект ConnectionHibernate
     */
    public static ConnectionHibernate getInstance(){
        ConnectionHibernate localInstance = instance;
        if (localInstance == null){
            synchronized (ConnectionHibernate.class){
                localInstance = instance;
                if (localInstance == null){
                    instance = localInstance = new ConnectionHibernate();
                }
            }
        }
        return localInstance;
    }
}
