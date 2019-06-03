package model.dao.impl;

import model.dao.SubjectDao;
import model.dao.mapper.SubjectMapper;
import model.entity.Subject;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    }

    @Override
    public Subject findById(int id) {
        return null;
    }

    @Override
    public List<Subject> findAll() {
        Map<Integer, Subject> subjects = new HashMap<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(properties.getProperty("SELECT_SUBJECT_ALL"));
            SubjectMapper subjectMapper = new SubjectMapper();
            while(resultSet.next()) {
                Subject subject = subjectMapper.extractFromResultSet(resultSet);
                subjectMapper.makeUnique(subjects, subject);
            }
            return new ArrayList<>(subjects.values());

        } catch (SQLException e) {
            e.printStackTrace();
            return  null;
        }
    }

    @Override
    public void update(Subject subject) {
    }

    @Override
    public void delete(int id) {
    }

    @Override
    public void close() {
    }
}