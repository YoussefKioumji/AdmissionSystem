package model.dao.impl;

import model.dao.SubjectDao;
import model.dao.mapper.SubjectMapper;
import model.entity.Subject;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class JDBCSubjectFactory implements SubjectDao {
    private Connection connection;
    private Properties properties;

    public JDBCSubjectFactory(Connection connection) {
        this.connection = connection;
        properties = new Properties();
        try {
            properties.load(new FileInputStream("D:\\Study\\Project\\AdmissionSystem\\src\\main\\resources\\sql.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(Subject subject) {
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("SUBJECT_INSERT"))) {
            statement.setString(1, subject.getEnName());
            statement.setString(2, subject.getUaName());
            statement.setInt(3, subject.getQuestions());
            statement.setInt(4, subject.getMaximum());
            statement.setInt(5, subject.getMinimum());
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
    public Subject findById(int id) {
//        Subject subject = new Subject();
//        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("SUBJECT_FIND_BY_ID"))) {
//            statement.setInt(1, id);
//            ResultSet resultSet = statement.executeQuery();
//            SubjectMarkMapper subjectMarkMapper = new SubjectMarkMapper();
//            while(resultSet.next()) {
//                subject = subjectMarkMapper.extractFromResultSet(resultSet);
//            }
//            return subject;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
        return null;
    }

    @Override
    public List<Subject> findAll() {
        Map<Integer, Subject> subjects = new HashMap<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(properties.getProperty("SUBJECT_SELECT_ALL"));
            SubjectMapper subjectMapper = new SubjectMapper();
            while(resultSet.next()) {
                Subject subject = subjectMapper.extractFromResultSet(resultSet);
                subjectMapper.makeUnique(subjects, subject);
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
        return new ArrayList<>(subjects.values());
    }

    @Override
    public List<Subject> findAllPagination(int startIndex, int recordPerPage) {
        Map<Integer, Subject> subjects = new HashMap<>();
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("SUBJECT_SELECT_ALL_PAGINATION"))) {
            statement.setInt(1, startIndex);
            statement.setInt(2, recordPerPage);
            ResultSet resultSet = statement.executeQuery();
            SubjectMapper subjectMapper = new SubjectMapper();
            while(resultSet.next()) {
                Subject subject = subjectMapper.extractFromResultSet(resultSet);
                subjectMapper.makeUnique(subjects, subject);
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
        return new ArrayList<>(subjects.values());
    }

    @Override
    public int numberOfRows() {
        int totalNumberOfRecords = 0;
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("SUBJECT_NUMBER_OF_ROWS"))) {
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
    public List<Subject> getUserSubjects(int userId) {
        Map<Integer, Subject> subjects = new HashMap<>();
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("EXAM_FIND_ALL_BY_USER_ID"))) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            SubjectMapper subjectMapper = new SubjectMapper();
            while(resultSet.next()) {
                Subject subject = subjectMapper.extractFromResultSet(resultSet);
                subjectMapper.makeUnique(subjects, subject);
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
        return new ArrayList<>(subjects.values());
    }

    @Override
    public List<Subject> getSpecialitySubjects(int specialityId) {
        Map<Integer, Subject> subjects = new HashMap<>();
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("EXAM_FIND_ALL_BY_SPECIALITY_ID"))) {
            statement.setInt(1, specialityId);
            ResultSet resultSet = statement.executeQuery();
            SubjectMapper subjectMapper = new SubjectMapper();
            while(resultSet.next()) {
                Subject subject = subjectMapper.extractFromResultSet(resultSet);
                subjectMapper.makeUnique(subjects, subject);
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
        return new ArrayList<>(subjects.values());
    }

    @Override
    public int[] findMarks(int userId) {
        int[] marks = new int[3];
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("EXAM_EXTRACT_MARKS"))) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            int i = 0;
            while(resultSet.next()) {
                marks[i] = resultSet.getInt("mark");
                i++;
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
        return marks;
    }

    @Override
    public List<Subject> findExamSubject() {
        Map<Integer, Subject> subjects = new HashMap<>();
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("EXAM_SELECT_SUBJECT"))) {
            ResultSet resultSet = statement.executeQuery();
            SubjectMapper subjectMapper = new SubjectMapper();
            while(resultSet.next()) {
                Subject subject = subjectMapper.extractFromResultSet(resultSet);
                subjectMapper.makeUnique(subjects, subject);
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
        return new ArrayList<>(subjects.values());
    }

    @Override
    public void update(Subject subject) {
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