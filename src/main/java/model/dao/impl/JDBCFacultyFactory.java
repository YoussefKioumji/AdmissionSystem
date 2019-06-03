package model.dao.impl;

import model.dao.FacultyDao;
import model.entity.Faculty;

import java.sql.Connection;
import java.util.List;

public class JDBCFacultyFactory implements FacultyDao {
    private Connection connection;

    public JDBCFacultyFactory(Connection connection) {
        this.connection = connection;
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
    }
}