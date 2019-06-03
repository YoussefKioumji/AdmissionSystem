package model.dao.mapper;


import model.entity.Speciality;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class SpecialityMapper implements ObjectMapper<Speciality> {
    @Override
    public Speciality extractFromResultSet(ResultSet resultSet) throws SQLException {
        Speciality speciality = new Speciality();

        speciality.setId(resultSet.getInt("id"));
        speciality.setCode(resultSet.getInt("code"));
        speciality.setEnName(resultSet.getString("name_en"));
        speciality.setUaName(resultSet.getString("name_ua"));
        speciality.setYears(resultSet.getInt("years"));

        return speciality;
    }
    @Override
    public Speciality makeUnique(Map<Integer, Speciality> cache, Speciality entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }
}