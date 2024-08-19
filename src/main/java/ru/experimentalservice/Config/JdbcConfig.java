package ru.experimentalservice.Config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

//@Configuration
public class JdbcConfig {
/*
    @Bean(name = "dataCollectorDbSource")
    @ConfigurationProperties(prefix = "spring.data-collector-db")
    public DataSource dataCollectorDbSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dataCollectorJdbcTemplate")
    public JdbcTemplate dataCollectorJdbcTemplate(@Qualifier("dataCollectorDbSource") DataSource dataCollectorDbSource) {
        return new JdbcTemplate(dataCollectorDbSource);
    }*/
}