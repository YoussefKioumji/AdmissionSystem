package model.service;

import model.dao.DaoFactory;
import model.dao.SpecialityDao;
import model.entity.Speciality;

import java.util.List;

public class SpecialityService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Speciality> findAllSpecialities() {
        SpecialityDao dao = daoFactory.createSpecialityDao();
        return dao.findAll();
    }
}