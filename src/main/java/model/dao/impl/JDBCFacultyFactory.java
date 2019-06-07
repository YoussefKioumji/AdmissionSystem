package model.dao.impl;

import model.dao.FacultyDao;
import model.dao.mapper.FacultyMapper;
import model.entity.Faculty;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        return  null;
    }

    @Override
    public List<Faculty> findAll() {
//        Map<Integer, Faculty> faculties = new HashMap<>();
//        try (Statement statement = connection.createStatement()) {
//            ResultSet resultSet = statement.executeQuery(properties.getProperty("FACULTY_FIND_ALL"));
//            FacultyMapper facultyMapper = new FacultyMapper();
//            while(resultSet.next()) {
//                Faculty faculty = facultyMapper.extractFromResultSet(resultSet);
//                facultyMapper.makeUnique(faculties, faculty);
//            }
//            return new ArrayList<>(faculties.values());
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
        return null;
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