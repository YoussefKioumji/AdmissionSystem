package model.dao;

import model.entity.User;

import java.util.List;

public interface UserDao extends GenericDao<User> {
    User findByEmail(String email);
    List<User> findAllExams();
    List<User> findExamsBySubject(int subjectId);
    void createExam(int userId, int subjectId);
    void updateExam(int mark, int userId, int subjectId);
}