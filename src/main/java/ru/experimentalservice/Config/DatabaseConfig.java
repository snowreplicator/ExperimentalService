package ru.experimentalservice.Config;

import javax.sql.DataSource;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
public class DatabaseConfig {

    /*
    @Primary
    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }*/

    /*
    @Bean(name = "dataCollectorDataSource")
    @ConfigurationProperties(prefix = "spring.data-collector-db")
    public DataSource dataCollectorDataSource() {
        return DataSourceBuilder.create().build();
    }*/

    @Bean(name = "dataCollectorDataSource")
    public DataSource dataCollectorDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        //dataSourceBuilder.url("jdbc:postgresql://localhost:5432/datacollectorservice_db");
        dataSourceBuilder.url("jdbc:postgresql://localhost:5432/datacollectorservice_db?currentSchema=datacollectorservice");
        dataSourceBuilder.username("portal");
        dataSourceBuilder.password("troP4444");
        return dataSourceBuilder.build();
    }


    @Bean(name = "jdbcTemplate")
    public JdbcTemplate jdbcTemplate(@Qualifier("dataCollectorDataSource") DataSource dataCollectorDataSource) {
        return new JdbcTemplate(dataCollectorDataSource);
    }

    @Primary
    @Bean(name = "dataSource")
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://localhost:5432/experimentalservice_db"); // URL для JPA
        dataSourceBuilder.username("portal");
        dataSourceBuilder.password("troP4444");
        return dataSourceBuilder.build();
    }

    @Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}
