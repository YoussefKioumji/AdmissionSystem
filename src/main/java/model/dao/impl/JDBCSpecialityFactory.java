package model.dao.impl;

import model.dao.SpecialityDao;
import model.dao.mapper.FacultyMapper;
import model.dao.mapper.SpecialityMapper;
import model.dao.mapper.SubjectMapper;
import model.dao.mapper.UserMapper;
import model.entity.Faculty;
import model.entity.Speciality;
import model.entity.Subject;
import model.entity.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class JDBCSpecialityFactory implements SpecialityDao {
    private Connection connection;
    private Properties properties;

    public JDBCSpecialityFactory(Connection connection) {
        this.connection = connection;
        properties = new Properties();
        try {
            properties.load(new FileInputStream("D:\\Study\\Project\\AdmissionSystem\\src\\main\\resources\\sql.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(Speciality speciality) {
    }

    @Override
    public void createWithSubjects(Speciality speciality, List<Integer> subjectIds) {
        try {
            connection.setAutoCommit(false);
            int specialityId = 0;
            PreparedStatement statement1 = connection.prepareStatement(properties.getProperty("SPECIALITY_INSERT_WITHOUT_SUBJECTS"));
            statement1.setInt(1, speciality.getCode());
            statement1.setString(2, speciality.getEnName());
            statement1.setString(3, speciality.getUaName());
            statement1.setInt(4, speciality.getFaculty().getId());
            statement1.setInt(5, speciality.getYears());
            statement1.executeUpdate();
            PreparedStatement statement2 = connection.prepareStatement(properties.getProperty("SPECIALITY_LAST_INSERT_ID"));
            ResultSet resultSet = statement2.executeQuery();
            while(resultSet.next()) {
                specialityId = resultSet.getInt(1);
            }
            PreparedStatement statement3 = connection.prepareStatement(properties.getProperty("SPECIALITY_INSERT_SUBJECTS"));
            for (Integer subjectId : subjectIds) {
                statement3.setInt(1, specialityId);
                statement3.setInt(2, subjectId);
                statement3.executeUpdate();
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Speciality findById(int id) {
        return null;
    }

    @Override
    public List<Speciality> findByFaculty(int facultyId) {
        Map<Integer, Speciality> specialities = new HashMap<>();
        Map<Integer, Faculty> faculties = new HashMap<>();
        Map<Integer, Subject> subjects = new HashMap<>();
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("FIND_SPECIALITY_BY_FACULTY"))) {
            statement.setInt(1, facultyId);
            ResultSet resultSet = statement.executeQuery();
            SpecialityMapper specialityMapper = new SpecialityMapper();
            FacultyMapper facultyMapper = new FacultyMapper();
            SubjectMapper subjectMapper = new SubjectMapper();
            while(resultSet.next()) {
                Speciality speciality = specialityMapper.extractFromResultSet(resultSet);
                speciality = specialityMapper.makeUnique(specialities, speciality);
                Faculty faculty = facultyMapper.extractFromResultSet(resultSet);
                faculty = facultyMapper.makeUnique(faculties, faculty);
                Subject subject = subjectMapper.extractFromResultSet(resultSet);
                subject = subjectMapper.makeUnique(subjects, subject);
                speciality.setFaculty(faculty);
                speciality.getSubjects().add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>(specialities.values());
    }

    @Override
    public List<Speciality> findAll() {
        Map<Integer, Speciality> specialities = new HashMap<>();
        Map<Integer, Faculty> faculties = new HashMap<>();
        Map<Integer, Subject> subjects = new HashMap<>();
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(properties.getProperty("SPECIALITY_SELECT_ALL"));
            SpecialityMapper specialityMapper = new SpecialityMapper();
            FacultyMapper facultyMapper = new FacultyMapper();
            SubjectMapper subjectMapper = new SubjectMapper();
            while(resultSet.next()) {
                Speciality speciality = specialityMapper.extractFromResultSet(resultSet);
                speciality = specialityMapper.makeUnique(specialities, speciality);
                Faculty faculty = facultyMapper.extractFromResultSet(resultSet);
                faculty = facultyMapper.makeUnique(faculties, faculty);
                Subject subject = subjectMapper.extractFromResultSet(resultSet);
                subject = subjectMapper.makeUnique(subjects, subject);
                speciality.setFaculty(faculty);
                speciality.getSubjects().add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>(specialities.values());
    }

    public List<Speciality> findAllPagination(int startIndex, int recordPerPage) {
        Map<Integer, Speciality> specialities = new HashMap<>();
        Map<Integer, Faculty> faculties = new HashMap<>();
        Map<Integer, Subject> subjects = new HashMap<>();
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("SPECIALITY_SELECT_ALL_PAGINATION"))){
            statement.setInt(1, startIndex);
            statement.setInt(2, recordPerPage);
            ResultSet resultSet = statement.executeQuery();
            SpecialityMapper specialityMapper = new SpecialityMapper();
            FacultyMapper facultyMapper = new FacultyMapper();
            SubjectMapper subjectMapper = new SubjectMapper();
            while(resultSet.next()) {
                Speciality speciality = specialityMapper.extractFromResultSet(resultSet);
                speciality = specialityMapper.makeUnique(specialities, speciality);
                Faculty faculty = facultyMapper.extractFromResultSet(resultSet);
                faculty = facultyMapper.makeUnique(faculties, faculty);
                Subject subject = subjectMapper.extractFromResultSet(resultSet);
                subject = subjectMapper.makeUnique(subjects, subject);
                speciality.setFaculty(faculty);
                speciality.getSubjects().add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>(specialities.values());
    }

    @Override
    public int numberOfRows() {
        int totalNumberOfRecords = 0;
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("SPECIALITY_NUMBER_OF_ROWS"))) {
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                totalNumberOfRecords = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return totalNumberOfRecords;
    }

    @Override
    public List<Integer> findUsersWithSpeciality() {
        List<Integer> usersWithSpeciality = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("SELECT_USERS_WITH_SPECIALITY"))) {
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                usersWithSpeciality.add(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return usersWithSpeciality;
    }

    @Override
    public void createAdmission(int specialityId, int userId, int finalMark) {
        try(PreparedStatement statement = connection.prepareStatement(properties.getProperty("SPECIALITY_CREATE_ADMISSION"))) {
            statement.setInt(1, specialityId);
            statement.setInt(2, userId);
            statement.setInt(3, finalMark);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Speciality> findApplications(int specialityId) {
        Map<Integer, Speciality> specialities = new HashMap<>();
        Map<Integer, User> users = new HashMap<>();
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("FIND_APPLICATION_BY_SPECIALITY_ID"))) {
            statement.setInt(1, specialityId);
            ResultSet resultSet = statement.executeQuery();
            SpecialityMapper specialityMapper = new SpecialityMapper();
            UserMapper userMapper = new UserMapper();
            while(resultSet.next()) {
                Speciality speciality = specialityMapper.extractFromResultSet(resultSet);
                speciality = specialityMapper.makeUnique(specialities, speciality);
                User user = userMapper.extractFromResultSet(resultSet);
                user = userMapper.makeUnique(users, user);
                speciality.getUsers().add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>(specialities.values());
    }

    @Override
    public List<String> findPassed(int specialityId) {
        List<String> passed = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("FIND_PASSED"))) {
            statement.setInt(1, specialityId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                passed.add(resultSet.getString("user.email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return passed;
    }

    @Override
    public List<String> findNotPassed(int specialityId) {
        List<String> notPassed = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("FIND_NOT_PASSED"))) {
            statement.setInt(1, specialityId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.absolute(2);
            while(resultSet.next()) {
                notPassed.add(resultSet.getString("user.email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return notPassed;
    }

    @Override
    public List<Integer> getFinalMarks(int specialityId) {
        List<Integer> finalMarks = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("FIND_FINAL_MARKS_BY_SPECIALITY_ID"))) {
            statement.setInt(1, specialityId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                finalMarks.add(resultSet.getInt("final_mark"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return finalMarks;
    }

    @Override
    public List<Integer> getDistinctSpecialityId() {
        List<Integer> specialityIds = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(properties.getProperty("FIND_DISTINCT_SPECIALITY_ID"));
            while(resultSet.next()) {
                specialityIds.add(resultSet.getInt("speciality_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return specialityIds;
    }

    @Override
    public void update(Speciality speciality) {
    }

    @Override
    public void delete(int id) {
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}