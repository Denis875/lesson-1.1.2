package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.Connection;
import java.sql.Driver;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        User user1 = new User("name1", "lastname1", (byte)1);
        User user2 = new User("name2", "lastname2", (byte)2);
        User user3 = new User("name3", "lastname3", (byte)3);
        User user4 = new User("name4", "lastname4", (byte)4);
        userService.saveUser(user1.getName(),user1.getLastName(),user1.getAge());
        userService.saveUser(user2.getName(),user2.getLastName(),user2.getAge());
        userService.saveUser(user3.getName(),user3.getLastName(),user3.getAge());
        userService.saveUser(user4.getName(),user4.getLastName(),user4.getAge());
        List<User> allUsers = userService.getAllUsers();
        for(User u : allUsers){
            System.out.println(u.toString());
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
