package com.marstalk.spring.jdbcTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class JdbcService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void query(){
        jdbcTemplate.query("select 1 from dual", new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                System.out.println(rs.getString(1));
            }
        });
    }
}
