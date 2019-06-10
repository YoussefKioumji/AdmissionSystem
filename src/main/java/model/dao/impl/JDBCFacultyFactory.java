package model.dao.impl;

import model.dao.FacultyDao;
import model.dao.mapper.FacultyMapper;
import model.entity.Faculty;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class JDBCFacultyFactory implements FacultyDao {
    static final Logger logger = Logger.getLogger(JDBCFacultyFactory.class);
    private Connection connection;
    private Properties properties;

    public JDBCFacultyFactory(Connection connection) {
        this.connection = connection;
        properties = new Properties();
        try {
            properties.load(new FileInputStream("D:\\Study\\Project\\AdmissionSystem\\src\\main\\resources\\sql.properties"));
        } catch (IOException e) {
            logger.error("IOException in JDBCFacultyFactory: JDBCFacultyFactory", e);
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
            logger.error("SQLException in JDBCFacultyFactory: findById", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("SQLException in JDBCFacultyFactory: findById", e);
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
            logger.error("SQLException in JDBCFacultyFactory: findAll", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("SQLException in JDBCFacultyFactory: findAll", e);
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
            logger.error("SQLException in JDBCFacultyFactory: close", e);
            throw new RuntimeException(e);
        }
    }
}