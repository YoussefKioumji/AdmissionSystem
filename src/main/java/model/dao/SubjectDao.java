package model.dao;

import model.entity.Subject;

import java.util.List;

public interface SubjectDao extends GenericDao<Subject> {
    List<Subject> getUserSubjects(int userId);
    List<Subject> getSpecialitySubjects(int specialityId);
    int[] findMarks(int userId);
    List<Subject> findExamSubject();
    List<Subject> findAllPagination(int startIndex, int recordPerPage);
    int numberOfRows();
}