package com.wrf.backend.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
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
        final var dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(config.getDriverClassName());
        dataSource.setUrl(config.getUrl());
        dataSource.setUsername(config.getUsername());
        dataSource.setPassword(config.getPassword());
        return dataSource;
    }

    @Bean
    @Primary
    LocalSessionFactoryBean sessionFactory() {
        final var sessionFactory = new LocalSessionFactoryBean();
        final var hibernateProperties = new Properties();
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
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final var vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        final var factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan(PACKAGES_TO_SCAN);
        factory.setDataSource(dataSource());
        return factory;
    }

    @Bean
    HibernateTemplate hibernateTemplate() {
        return new HibernateTemplate(Objects.requireNonNull(sessionFactory().getObject()));
    }
}
