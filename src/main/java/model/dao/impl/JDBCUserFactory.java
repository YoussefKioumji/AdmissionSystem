package model.dao.impl;

import model.dao.UserDao;
import model.entity.User;

import java.sql.Connection;
import java.util.List;

public class JDBCUserFactory implements UserDao {
    private Connection connection;

    public JDBCUserFactory(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create (User user) {
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void update(User user) {
    }

    @Override
    public void delete(int id) {
    }

    @Override
    public void close() {
    }
}