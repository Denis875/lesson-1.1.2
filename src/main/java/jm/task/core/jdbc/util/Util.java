package jm.task.core.jdbc.util;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String DB_URL = "jdbc:MySQL://localhost/user";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
