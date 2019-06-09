package model.dao.impl;

import model.dao.UserDao;
import model.dao.mapper.SubjectMarkMapper;
import model.dao.mapper.UserMapper;
import model.entity.Subject;
import model.entity.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class JDBCUserFactory implements UserDao {
    private Connection connection;
    private Properties properties;

    public JDBCUserFactory(Connection connection) {
        this.connection = connection;
        properties = new Properties();
        try {
            properties.load(new FileInputStream("D:\\Study\\Project\\AdmissionSystem\\src\\main\\resources\\sql.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create (User user) {
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("USER_INSERT"))){
            statement.setString(1, user.getRole().name().toLowerCase());
            statement.setString(2, user.getEnFirstName());
            statement.setString(3, user.getUaFirstName());
            statement.setString(4, user.getEnLastName());
            statement.setString(5, user.getUaLastName());
            statement.setString(6, user.getEmail());
            statement.setString(7, user.getPassword());
            statement.setInt(8, user.getAge());
            statement.setString(9, user.getPhone());
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
    public User findById(int id) {
//        User user = new User();
//        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("USER_FIND_BY_ID"))) {
//            statement.setInt(1, id);
//            ResultSet resultSet = statement.executeQuery();
//            UserMapper userMapper = new UserMapper();
//            while(resultSet.next()) {
//                user = userMapper.extractFromResultSet(resultSet);
//            }
//            return user;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
        return null;
    }

    @Override
    public List<User> findAll() {
//        Map<Integer, User> users = new HashMap<>();
//        Map<Integer, Subject> subjects = new HashMap<>();
//        try (Statement statement = connection.createStatement()){
//            ResultSet resultSet = statement.executeQuery(properties.getProperty("USER_SELECT_ALL"));
//            UserMapper userMapper = new UserMapper();
//            SubjectMarkMapper subjectMapper = new SubjectMarkMapper();
//            while(resultSet.next()) {
//                User user = userMapper.extractFromResultSet(resultSet);
//                user = userMapper.makeUnique(users, user);
//                Subject subject = subjectMapper.extractFromResultSet(resultSet);
//                subject = subjectMapper.makeUnique(subjects, subject);
//                user.getExams().add(subject);
//            }
//            return new ArrayList<>(users.values());
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
        return null;
    }

    @Override
    public List<User> findAllExams() {
        Map<Integer, User> users = new HashMap<>();
        Map<Integer, Subject> subjects = new HashMap<>();
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(properties.getProperty("CLIENT_SELECT_ALL"));
            UserMapper userMapper = new UserMapper();
            SubjectMarkMapper subjectMarkMapper = new SubjectMarkMapper();
            while(resultSet.next()) {
                User user = userMapper.extractFromResultSet(resultSet);
                user = userMapper.makeUnique(users, user);
                Subject subject = subjectMarkMapper.extractFromResultSet(resultSet);
                subjectMarkMapper.makeUnique(subjects, subject);
                user.getExams().add(subject);
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
        return new ArrayList<>(users.values());
    }

    @Override
    public List<User> findExamsBySubject(int subjectId) {
        Map<Integer, User> users = new HashMap<>();
        Map<Integer, Subject> subjects = new HashMap<>();
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("CLIENT_SEARCH_BY_SUBJECT"))){
            statement.setInt(1, subjectId);
            ResultSet resultSet = statement.executeQuery();
            UserMapper userMapper = new UserMapper();
            SubjectMarkMapper subjectMarkMapper = new SubjectMarkMapper();
            while(resultSet.next()) {
                User user = userMapper.extractFromResultSet(resultSet);
                user = userMapper.makeUnique(users, user);
                Subject subject = subjectMarkMapper.extractFromResultSet(resultSet);
                subjectMarkMapper.makeUnique(subjects, subject);
                user.getExams().add(subject);
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
        return new ArrayList<>(users.values());
    }

    @Override
    public void createExam(int userId, int subjectId) {
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("EXAM_INSERT"))) {
            statement.setInt(1, userId);
            statement.setInt(2, subjectId);
            statement.setInt(3, 0);
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
    public void updateExam(int mark, int userId, int subjectId) {
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("EXAM_UPDATE_MARK"))) {
            statement.setInt(1, mark);
            statement.setInt(2, userId);
            statement.setInt(3, subjectId);
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
    public void update(User user) {
    }

    @Override
    public void delete(int id) {
    }

    @Override
    public User findByEmail(String email) {
        User user = new User();
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("USER_FIND_BY_EMAIL"))){
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            UserMapper userMapper = new UserMapper();
            if (resultSet.next()) {
                user = userMapper.extractFromResultSet(resultSet);
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
        return user;
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