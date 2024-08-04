package com.home.clouduser.configs;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class DatabaseConfig {

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder
                .create()
                .url("jdbc:mysql://localhost:3306/mydatabase")
                .username("your-username")
                .password("your-password")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}