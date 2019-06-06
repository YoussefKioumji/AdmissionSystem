package model.dao.impl;

import model.dao.SpecialityDao;
import model.dao.mapper.FacultyMapper;
import model.dao.mapper.SpecialityMapper;
import model.entity.Faculty;
import model.entity.Speciality;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    public Speciality findById(int id) {
        return null;
    }

    @Override
    public List<Speciality> findAll() {
        Map<Integer, Speciality> specialities = new HashMap<>();
        Map<Integer, Faculty> faculties = new HashMap<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(properties.getProperty("SPECIALITY_SELECT_ALL"));
            SpecialityMapper specialityMapper = new SpecialityMapper();
            FacultyMapper facultyMapper = new FacultyMapper();
            while(resultSet.next()) {
                Speciality speciality = specialityMapper.extractFromResultSet(resultSet);
                specialityMapper.makeUnique(specialities, speciality);
                Faculty faculty = facultyMapper.extractFromResultSet(resultSet);
                facultyMapper.makeUnique(faculties, faculty);
                speciality.setFaculty(faculty);
            }
            return new ArrayList<>(specialities.values());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(Speciality speciality) {
    }

    @Override
    public void delete(int id) {
    }

    @Override
    public void close() {
    }
}