package model.service;

import model.dao.DaoFactory;
import model.dao.SpecialityDao;
import model.entity.Speciality;

import java.util.List;

public class SpecialityService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Speciality> findAll() {
        SpecialityDao dao = daoFactory.createSpecialityDao();
        return dao.findAll();
    }

    public List<Speciality> findAllPagination(int startIndex, int recordPerPage) {
        SpecialityDao dao = daoFactory.createSpecialityDao();
        return dao.findAllPagination(startIndex, recordPerPage);
    }

    public int numberOfRows() {
        SpecialityDao dao = daoFactory.createSpecialityDao();
        return dao.numberOfRows();
    }

    public void createAdmission(int specialityId, int userId, int finalMark) {
        SpecialityDao dao = daoFactory.createSpecialityDao();
        dao.createAdmission(specialityId, userId, finalMark);
    }

    public List<Speciality> findApplications(int specialityId) {
        SpecialityDao dao = daoFactory.createSpecialityDao();
        return dao.findApplications(specialityId);
    }

    public List<Integer> getFinalMarks(int specialityId) {
        SpecialityDao dao = daoFactory.createSpecialityDao();
        return dao.getFinalMarks(specialityId);
    }

    public List<Integer> getDistinctSpecialityId() {
        SpecialityDao dao = daoFactory.createSpecialityDao();
        return dao.getDistinctSpecialityId();
    }

    public List<String> findPassed(int specialityId) {
        SpecialityDao dao = daoFactory.createSpecialityDao();
        return dao.findPassed(specialityId);
    }

    public List<String> findNotPassed(int specialityId) {
        SpecialityDao dao = daoFactory.createSpecialityDao();
        return dao.findNotPassed(specialityId);
    }

    public List<Speciality> findByFaculty(int facultyId) {
        SpecialityDao dao = daoFactory.createSpecialityDao();
        return dao.findByFaculty(facultyId);
    }

    public void createWithSubjects(Speciality speciality, List<Integer> subjectIds) {
        SpecialityDao dao = daoFactory.createSpecialityDao();
        dao.createWithSubjects(speciality, subjectIds);
    }
}