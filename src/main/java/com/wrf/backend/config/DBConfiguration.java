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
import java.util.Properties;

@Configuration
public class DBConfiguration {

    private static final Logger LOG = LogManager.getLogger(DBConfiguration.class);

    private static final String hbm2ddlAuto = "update";

    private static final String[] PACKAGES_TO_SCAN = { "com.wrf.backend" };

    @Autowired
    AppConfig config;

    @Bean
    DataSource dataSource() {
        if (config.url == null) {
            LOG.error("DBConfiguration. pg.url not set!");
        }
        if (config.username == null) {
            LOG.error("DBConfiguration. pg.userName not set!");
        }
        if (config.password == null) {
            LOG.error("DBConfiguration. pg.password not set!");
        }
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(config.driverClassName);
        dataSource.setUrl(config.url);
        dataSource.setUsername(config.username);
        dataSource.setPassword(config.password);
        return dataSource;
    }

    @Bean
    LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", config.dialect);
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
        return new HibernateTransactionManager(sessionFactory().getObject());
    }

    @Bean
    HibernateTemplate hibernateTemplate() {
        return new HibernateTemplate(sessionFactory().getObject());
    }
}
