package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
    public void createUsersTable() {
        try {
            userDaoJDBC.createUsersTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try {
            userDaoJDBC.dropUsersTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            userDaoJDBC.saveUser(name,lastName,age);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("User с именем -" + name + " добален в базу данных");
    }

    public void removeUserById(long id) {
        try {
            userDaoJDBC.removeUserById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> allUsers = null;
        try {
            allUsers = userDaoJDBC.getAllUsers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allUsers;
    }

    public void cleanUsersTable() {
        try {
            userDaoJDBC.cleanUsersTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
