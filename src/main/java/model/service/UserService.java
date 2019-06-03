package model.service;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entity.User;

import java.util.List;

public class UserService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<User> findAllUsers() {
        UserDao dao = daoFactory.createUserDao();
        return dao.findAll();
    }
}