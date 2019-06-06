package model.service;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entity.User;

import java.util.List;
import java.util.Optional;

public class UserService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public Optional<User> login(String email) {
        Optional<User> user;
        UserDao dao = daoFactory.createUserDao();
        user =  dao.findByEmail(email);
        return user;
    }

    public void create(User user) {
        UserDao dao = daoFactory.createUserDao();
        dao.create(user);
    }

    public List<User> findAll() {
        UserDao dao = daoFactory.createUserDao();
        return dao.findAll();
    }
}