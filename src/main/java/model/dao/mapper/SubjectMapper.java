package model.dao.mapper;

import model.entity.Subject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class SubjectMapper implements ObjectMapper<Subject> {
    @Override
    public Subject extractFromResultSet(ResultSet resultSet) throws SQLException {
        Subject subject = new Subject();

        subject.setId(resultSet.getInt("subject.id"));
        subject.setEnName(resultSet.getString("subject.name_en"));
        subject.setUaName(resultSet.getString("subject.name_ua"));
        subject.setQuestions(resultSet.getInt("subject.questions"));
        subject.setMaximum(resultSet.getInt("subject.maximum"));
        subject.setMinimum(resultSet.getInt("subject.minimum"));
        subject.setMark(resultSet.getInt("subject.mark"));

        return subject;
    }
    @Override
    public Subject makeUnique(Map<Integer, Subject> cache, Subject entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }
}