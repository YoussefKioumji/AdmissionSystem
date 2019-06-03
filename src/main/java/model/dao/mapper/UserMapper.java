package model.dao.mapper;

import model.entity.User;
import model.entity.enums.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserMapper implements ObjectMapper<User> {
    @Override
    public User extractFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();

        user.setId(resultSet.getInt("id"));
        user.setRole(Role.valueOf(resultSet.getString("role").toUpperCase()));
        user.setEnFirstName(resultSet.getString("first_name_en"));
        user.setUaFirstName(resultSet.getString("first_name_ua"));
        user.setEnLastName(resultSet.getString("last_name_en"));
        user.setUaLastName(resultSet.getString("last_name_ua"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setAge(resultSet.getInt("age"));
        user.setPhone(resultSet.getString("phone"));

        return user;
    }

    @Override
    public User makeUnique(Map<Integer, User> cache, User entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }
}