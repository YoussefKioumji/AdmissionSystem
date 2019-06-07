package model.dao.impl;

import model.dao.UserDao;
import model.dao.mapper.SubjectMapper;
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
//            SubjectMapper subjectMapper = new SubjectMapper();
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
            SubjectMapper subjectMapper = new SubjectMapper();
            while(resultSet.next()) {
                User user = userMapper.extractFromResultSet(resultSet);
                user = userMapper.makeUnique(users, user);
                Subject subject = subjectMapper.extractFromResultSet(resultSet);
                subjectMapper.makeUnique(subjects, subject);
                user.getExams().add(subject);
            }
            return new ArrayList<>(users.values());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void chooseExams(int userId, int subjectId) {
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("EXAM_INSERT"))) {
            statement.setInt(1, userId);
            statement.setInt(2, subjectId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteExam(int userId, int subjectId) {
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("EXAM_DELETE"))) {
            statement.setInt(1, userId);
            statement.setInt(2, subjectId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
    }

    @Override
    public void delete(int id) {
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<User> user = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("USER_FIND_BY_EMAIL"))){
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            UserMapper userMapper = new UserMapper();
            if (resultSet.next()) {
                user = Optional.of(userMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
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