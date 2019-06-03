package model.dao.impl;

import model.dao.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {
    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public UserDao createUserDao() {
        return new JDBCUserFactory(getConnection());
    }

    @Override
    public SubjectDao createSubjectDao() {
        return new JDBCSubjectFactory(getConnection());
    }

    @Override
    public SpecialityDao createSpecialityDao() {
        return new JDBCSpecialityFactory(getConnection());
    }

    @Override
    public FacultyDao createFacultyDao() {
        return new JDBCFacultyFactory(getConnection());
    }
}