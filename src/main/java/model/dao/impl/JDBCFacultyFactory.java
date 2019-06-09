package model.dao.impl;

import model.dao.FacultyDao;
import model.dao.mapper.FacultyMapper;
import model.entity.Faculty;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class JDBCFacultyFactory implements FacultyDao {
    private Connection connection;
    private Properties properties;

    public JDBCFacultyFactory(Connection connection) {
        this.connection = connection;
        properties = new Properties();
        try {
            properties.load(new FileInputStream("D:\\Study\\Project\\AdmissionSystem\\src\\main\\resources\\sql.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(Faculty faculty) {
    }

    @Override
    public Faculty findById(int id) {
        Faculty faculty = new Faculty();
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("FACULTY_FIND_BY_ID"))) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            FacultyMapper facultyMapper = new FacultyMapper();
            while(resultSet.next()) {
                faculty = facultyMapper.extractFromResultSet(resultSet);
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
        return faculty;
    }

    @Override
    public List<Faculty> findAll() {
        Map<Integer, Faculty> faculties = new HashMap<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(properties.getProperty("FACULTY_FIND_ALL"));
            FacultyMapper facultyMapper = new FacultyMapper();
            while(resultSet.next()) {
                Faculty faculty = facultyMapper.extractFromResultSet(resultSet);
                facultyMapper.makeUnique(faculties, faculty);
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
        return new ArrayList<>(faculties.values());
    }

    @Override
    public void update(Faculty faculty) {
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