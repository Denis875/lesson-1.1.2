package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
        Connection connection = Util.getConnection();
        String sqlCreateTable = "CREATE TABLE IF NOT EXISTS user(" +
                "id int auto_increment primary key,\n" +
                "name varchar(60),\n" +
                "lastname varchar(60),\n" +
                "age tinyint" +
                ");";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sqlCreateTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void dropUsersTable() throws SQLException {
        Connection connection = Util.getConnection();
        String sqlDropTable = "DROP TABLE IF EXISTS user";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sqlDropTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        Connection connection = Util.getConnection();
        String sqlAddUser = "INSERT INTO user(name, lastname, age) " +
                "VALUES(?,?,?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sqlAddUser);
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void removeUserById(long id) throws SQLException {
        Connection connection = Util.getConnection();
        String sqlRemoveUser = "DELETE FROM user WHERE id=?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sqlRemoveUser);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public List<User> getAllUsers() throws SQLException {
        Connection connection = Util.getConnection();
        List<User> allUsers = new ArrayList<>();
        String sqlGetAllTable = "SELECT * FROM user";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlGetAllTable);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));
                allUsers.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return allUsers;
    }

    public void cleanUsersTable() throws SQLException {
        Connection connection = Util.getConnection();
        String sqlCleanAllUser = "TRUNCATE TABLE user";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sqlCleanAllUser);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
