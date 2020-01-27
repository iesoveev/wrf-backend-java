package com.wrf.backend.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

@Configuration
public class DBConfiguration {

    private static final Logger LOG = LogManager.getLogger(DBConfiguration.class);

    private static final String hbm2ddlAuto = "update";

    private static final String[] PACKAGES_TO_SCAN = { "com.wrf.backend" };

    final AppConfig config;

    @Autowired
    public DBConfiguration(AppConfig config) {
        this.config = config;
    }

    @Bean
    DataSource dataSource() {
        if (config.getUrl() == null) {
            LOG.error("DBConfiguration. pg.url not set!");
        }
        if (config.getUrl() == null) {
            LOG.error("DBConfiguration. pg.userName not set!");
        }
        if (config.getUrl() == null) {
            LOG.error("DBConfiguration. pg.password not set!");
        }
        var dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(config.getDriverClassName());
        dataSource.setUrl(config.getUrl());
        dataSource.setUsername(config.getUsername());
        dataSource.setPassword(config.getPassword());
        return dataSource;
    }

    @Bean
    LocalSessionFactoryBean sessionFactory() {
        var sessionFactory = new LocalSessionFactoryBean();
        var hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", config.getDialect());
        hibernateProperties.setProperty("hibernate.show_sql", "false");
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", hbm2ddlAuto);
        hibernateProperties.setProperty("hibernate.connection.pool_size", "5");
        hibernateProperties.setProperty("hibernate.jdbc.fetch_size", "350");
        hibernateProperties.setProperty("hibernate.jdbc.batch_size", "200");

        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(PACKAGES_TO_SCAN);
        sessionFactory.setHibernateProperties(hibernateProperties);
        return sessionFactory;
    }

    @Bean
    HibernateTransactionManager transactionManager() {
        return new HibernateTransactionManager(Objects.requireNonNull(sessionFactory().getObject()));
    }

    @Bean
    HibernateTemplate hibernateTemplate() {
        return new HibernateTemplate(Objects.requireNonNull(sessionFactory().getObject()));
    }
}
