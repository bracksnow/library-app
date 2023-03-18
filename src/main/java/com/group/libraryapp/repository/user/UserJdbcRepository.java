package com.group.libraryapp.repository.user;

import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserJdbcRepository {

    private JdbcTemplate jdbcTemplate;

    public UserJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Boolean isUserNotExist(Long id){
        String readsql = "select * from user where id = ?";
        return jdbcTemplate.query(readsql, (rs, rowNum) -> 0,id).isEmpty();
    }
    public void updateUserName(String name,Long id){
        String sql = "update user set name = ? where id = ?";
        jdbcTemplate.update(sql, name, id);

    }
    public Boolean isUserNotExist(String name){
        String readsql = "select * from user where id = ?";
        return jdbcTemplate.query(readsql, (rs, rowNum) -> 0,name).isEmpty();
    }

    public void deleteUser(String name){
        String sql = "delete from user where name = ?";
        jdbcTemplate.update(sql, name);
    }
    public void saveUser(String name, Integer age){
        String sql = "insert into user (name, age) into values(?,?)";
        jdbcTemplate.update(sql, name, age);
    }
    public List<UserResponse> getUser(){
        String sql = "select * from user";
        return jdbcTemplate.query(sql, new RowMapper<UserResponse>() {
            @Override
            public UserResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                return new UserResponse(id, name, age);
            }
        });
    }
}
