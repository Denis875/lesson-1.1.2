package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        SessionFactory sessionFactory = Util.getHibernateConnection();
        String sqlCreateTable = "CREATE TABLE IF NOT EXISTS user(" +
                "id int auto_increment primary key,\n" +
                "name varchar(60),\n" +
                "lastname varchar(60),\n" +
                "age tinyint" +
                ");";
        try (sessionFactory; Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery(sqlCreateTable).executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {
        SessionFactory sessionFactory = Util.getHibernateConnection();
        String sqlDropTable = "DROP TABLE IF EXISTS user";
        try (sessionFactory; Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery(sqlDropTable).addEntity(User.class).executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        SessionFactory sessionFactory = Util.getHibernateConnection();
        try (sessionFactory; Session session = sessionFactory.openSession()) {
            User user = new User(name, lastName,age);
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        SessionFactory sessionFactory = Util.getHibernateConnection();
        try (sessionFactory; Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(session.get(User.class, id));
            session.getTransaction().commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        SessionFactory sessionFactory = Util.getHibernateConnection();
        List<User> listUser = null;
        try (sessionFactory; Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            listUser = session.createQuery("from User", User.class).getResultList();
            session.getTransaction().commit();
        }
        return listUser;
    }

    @Override
    public void cleanUsersTable() {
        SessionFactory sessionFactory = Util.getHibernateConnection();
        try (sessionFactory; Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery("delete User").executeUpdate();
            session.getTransaction().commit();
        }
    }
}
