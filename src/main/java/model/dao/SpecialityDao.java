package model.dao;

import model.entity.Speciality;

import java.util.List;

public interface SpecialityDao extends GenericDao<Speciality>{
    void createAdmission(int specialityId, int userId, int finalMark);
    List<Speciality> findApplications(int specialityId);
    List<Integer> getFinalMarks(int specialityId);
    List<Integer> getDistinctSpecialityId();
    List<String> findPassed(int specialityId);
    List<String> findNotPassed(int specialityId);
    List<Speciality> findByFaculty(int facultyId);
    List<Speciality> findAllPagination(int startIndex, int recordPerPage);
    int numberOfRows();
    void createWithSubjects(Speciality speciality, List<Integer> subjectIds);
    List<Integer> findUsersWithSpeciality();
}