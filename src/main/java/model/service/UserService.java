package model.service;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entity.User;

import java.util.List;

public class UserService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public User findByEmail(String email) {
        UserDao dao = daoFactory.createUserDao();
        User user =  dao.findByEmail(email);
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

    public List<User> findExamsBySubject(int subjectId) {
        UserDao dao = daoFactory.createUserDao();
        return dao.findExamsBySubject(subjectId);
    }

    public void createExam(int userId, int subjectId) {
        UserDao dao = daoFactory.createUserDao();
        dao.createExam(userId, subjectId);
    }

    public void updateExam(int mark, int userId, int subjectId) {
        UserDao dao = daoFactory.createUserDao();
        dao.updateExam(mark, userId, subjectId);
    }
}