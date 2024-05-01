package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:MySQL://localhost/user";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER).getDeclaredConstructor().newInstance();
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static SessionFactory getHibernateConnection(){
        Properties prop= new Properties();

        prop.setProperty("hibernate.connection.url", DB_URL);
        prop.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");

        prop.setProperty("hibernate.connection.username", DB_USERNAME);
        prop.setProperty("hibernate.connection.password", DB_PASSWORD);
        prop.setProperty("hibernate.connection.driver_class", DB_DRIVER);

        SessionFactory sessionFactory = new Configuration().addProperties(prop).addAnnotatedClass(User.class).buildSessionFactory();
        return sessionFactory;
    }
}
