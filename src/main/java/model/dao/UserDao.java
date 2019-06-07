package model.dao;

import model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao extends GenericDao<User> {
    Optional<User> findByEmail(String email);
    List<User> findAllExams();
    void chooseExams(int userId, int subjectId);
    void deleteExam(int userId, int subjectId);
}