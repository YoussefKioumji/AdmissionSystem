package model.dao.mapper;

import model.entity.Faculty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class FacultyMapper implements ObjectMapper<Faculty> {
    @Override
    public Faculty extractFromResultSet(ResultSet resultSet) throws SQLException {
        Faculty faculty = new Faculty();

        faculty.setId(resultSet.getInt("id"));
        faculty.setEnName(resultSet.getString("name_en"));
        faculty.setUaName(resultSet.getString("name_ua"));
        faculty.setEmail(resultSet.getString("email"));
        faculty.setPhone(resultSet.getString("phone"));

        return faculty;
    }
    @Override
    public Faculty makeUnique(Map<Integer, Faculty> cache, Faculty entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }
}