package com.wrf.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@RefreshScope
public class DBConfiguration {

    @Value("${spring.jpa.properties.hibernate.dialect}")
    private String dialect;

    @Value("${hibernate.show_sql}")
    private Boolean show_sql;

    @Value("${hibernate.hbm2ddl.auto}")
    private String hbm2ddlAuto;

    @Value("${hibernate.connection.pool_size}")
    private Integer pool_size;

    @Value("${hibernate.jdbc.fetch_size}")
    private Integer fetch_size;

    @Value("${hibernate.jdbc.batch_size}")
    private Integer batch_size;

    @Value("${hibernate.connection.release_mode}")
    private String release_mode;

    @Bean
    LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("com.wrf.backend.entity");

        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", dialect);
        hibernateProperties.put("hibernate.show_sql", show_sql);
        hibernateProperties.put("hibernate.hbm2ddl.auto", hbm2ddlAuto);
        hibernateProperties.put("hibernate.connection.pool_size", pool_size);
        hibernateProperties.put("hibernate.jdbc.fetch_size", fetch_size);
        hibernateProperties.put("hibernate.jdbc.batch_size", batch_size);
        hibernateProperties.put("hibernate.connection.release_mode", release_mode);
        sessionFactory.setHibernateProperties(hibernateProperties);
        return sessionFactory;
    }

    @Bean
    public HibernateTemplate hibernateTemplate(DataSource dataSource) {
        return new HibernateTemplate(sessionFactory(dataSource).getObject());
    }
}
