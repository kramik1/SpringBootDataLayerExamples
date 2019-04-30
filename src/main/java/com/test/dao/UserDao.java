package com.test.dao;

import com.test.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDao {

    private static String getUserSql = "select * from user where id = :ID";

    private static String getAllByFirstNameSql = "select * from user where first_name like :FIRSTNAME";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public UserEntity getUserById(long id) {
        SqlParameterSource parameters = new MapSqlParameterSource().addValue("ID", id);
        return jdbcTemplate.queryForObject(getUserSql, parameters, new UserEntityRowMapper());
    }

    public List<UserEntity> getAllByFirstName(String firstName) {
        SqlParameterSource parameters = new MapSqlParameterSource().addValue("FIRSTNAME", firstName);
        return jdbcTemplate.query(getAllByFirstNameSql, parameters, new UserEntityRowMapper());
    }

    class UserEntityRowMapper implements RowMapper<UserEntity> {

        @Override
        public UserEntity mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            UserEntity userEntity = new UserEntity();
            userEntity.setFirstName(resultSet.getString("first_name"));
            userEntity.setLastName(resultSet.getString("last_name"));
            userEntity.setUsername(resultSet.getString("username"));
            userEntity.setId(resultSet.getLong("id"));
            //userEntity.setCreatedOn(resultSet.getTimestamp("createdOn").toLocalDateTime().atOffset(ZoneOffset.UTC));

            return userEntity;
        }
    }
}
