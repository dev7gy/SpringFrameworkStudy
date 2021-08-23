package com.dev7gy.introduction.repository;

import com.dev7gy.introduction.model.SpringModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateSpringModelRepository implements SpringModelRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateSpringModelRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public SpringModel save(SpringModel springModel) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("spring_model").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", springModel.getName());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        springModel.setId(key.longValue());
        return springModel;
    }

    // 결과가 나오는 것을 RowMapper를 통해 Mapping해주기
    private RowMapper<SpringModel> springModelRowMapper() {
        /*
        return (rs, rowNum) -> {
            SpringModel springModel = new SpringModel();
            springModel.setId(rs.getLong("id"));
            springModel.setName(rs.getString("name"));
            return springModel;
        };
        */
        return new RowMapper<SpringModel>() {
            @Override
            public SpringModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                SpringModel springModel = new SpringModel();
                springModel.setId(rs.getLong("id"));
                springModel.setName(rs.getString("name"));
                return springModel;
            }
        };
    }
    @Override
    public Optional<SpringModel> findById(Long id) {
        List<SpringModel> result = jdbcTemplate.query("select * from spring_model where id = ?", springModelRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public Optional<SpringModel> findByName(String name) {

        List<SpringModel> result = jdbcTemplate.query("select * from spring_model where name = ?", springModelRowMapper(), name);
        return result.stream().findAny();
    }

    @Override
    public List<SpringModel> findAll() {
        return jdbcTemplate.query("select * from spring_model", springModelRowMapper());
    }
}
