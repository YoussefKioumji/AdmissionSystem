package model.service;

import model.dao.DaoFactory;
import model.dao.FacultyDao;
import model.entity.Faculty;

import java.util.List;

public class FacultyService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Faculty> findAll() {
        FacultyDao dao = daoFactory.createFacultyDao();
        return dao.findAll();
    }

    public Faculty findById(int id) {
        FacultyDao dao = daoFactory.createFacultyDao();
        return dao.findById(id);
    }
}