package model.dao.impl;

import model.dao.SpecialityDao;
import model.entity.Speciality;

import java.sql.Connection;
import java.util.List;

public class JDBCSpecialityFactory implements SpecialityDao {
    private Connection connection;

    public JDBCSpecialityFactory(Connection connection) {
        this.connection = connection;
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
        return null;
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