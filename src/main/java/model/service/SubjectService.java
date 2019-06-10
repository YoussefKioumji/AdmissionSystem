package model.service;

import model.dao.DaoFactory;
import model.dao.SubjectDao;
import model.entity.Subject;

import java.util.List;

public class SubjectService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Subject> findAll() {
        SubjectDao dao = daoFactory.createSubjectDao();
        return dao.findAll();
    }

    public List<Subject> findAllPagination(int startIndex, int recordPerPage) {
        SubjectDao dao = daoFactory.createSubjectDao();
        return dao.findAllPagination(startIndex, recordPerPage);
    }

    public int numberOfRows() {
        SubjectDao dao = daoFactory.createSubjectDao();
        return dao.numberOfRows();
    }

    public List<Subject> getUserSubjects(int userId) {
        SubjectDao dao = daoFactory.createSubjectDao();
        return dao.getUserSubjects(userId);
    }

    public List<Subject> getSpecialitySubjects(int specialityId) {
        SubjectDao dao = daoFactory.createSubjectDao();
        return dao.getSpecialitySubjects(specialityId);
    }

    public int[] findMarks(int userId) {
        SubjectDao dao = daoFactory.createSubjectDao();
        return dao.findMarks(userId);
    }

    public List<Subject> findExamSubject() {
        SubjectDao dao = daoFactory.createSubjectDao();
        return dao.findExamSubject();
    }

    public void createSubject(Subject subject) {
        SubjectDao dao = daoFactory.createSubjectDao();
        dao.create(subject);
    }

    public List<Integer> findUsersWithExams() {
        SubjectDao dao = daoFactory.createSubjectDao();
        return dao.findUsersWithExams();
    }

    public Subject findById(int id) {
        SubjectDao dao = daoFactory.createSubjectDao();
        return dao.findById(id);
    }
}