package model.service;

import model.dao.DaoFactory;
import model.dao.SubjectDao;
import model.entity.Subject;

import java.util.List;

public class SubjectService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Subject> findAllSubjects() {
        SubjectDao dao = daoFactory.createSubjectDao();
        return dao.findAll();
    }
}