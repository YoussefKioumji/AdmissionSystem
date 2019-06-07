package model.service;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entity.User;

import java.util.List;
import java.util.Optional;

public class UserService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public Optional<User> findByEmail(String email) {
        Optional<User> user;
        UserDao dao = daoFactory.createUserDao();
        user =  dao.findByEmail(email);
        return user;
    }

    public void create(User user) {
        UserDao dao = daoFactory.createUserDao();
        dao.create(user);
    }

    public List<User> findAllExams() {
        UserDao dao = daoFactory.createUserDao();
        return dao.findAllExams();
    }

    public void chooseExams(int userId, int subjectId) {
        UserDao dao = daoFactory.createUserDao();
        dao.chooseExams(userId, subjectId);
    }

    public void deleteExam(int userId, int subjectId) {
        UserDao dao = daoFactory.createUserDao();
        dao.deleteExam(userId, subjectId);
    }
}