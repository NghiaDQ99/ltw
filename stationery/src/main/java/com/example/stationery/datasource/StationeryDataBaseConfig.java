package com.example.stationery.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class StationeryDataBaseConfig {
    @Primary
    @Bean(name = "stationeryDataSourceProperties")
    @ConfigurationProperties("database.stationery.datasource")
    public DataSourceProperties stationeryDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "stationeryDataSource")
    @ConfigurationProperties("database.stationery.datasource.hikari")
    public DataSource stationeryDataSource() {
        return stationeryDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Primary
    @Bean(name = "stationeryNamedParameterJdbcTemplate")
    public NamedParameterJdbcTemplate stationeryNamedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(stationeryDataSource());
    }

    @Bean(name = "stationeryJdbcTemplate")
    public JdbcTemplate musicJdbcTemplate() {
        return new JdbcTemplate(stationeryDataSource());
    }
}
