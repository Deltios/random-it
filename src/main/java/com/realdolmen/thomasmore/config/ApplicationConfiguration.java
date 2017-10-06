package com.realdolmen.thomasmore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.util.Locale;
import java.util.Properties;

/**
 * Created by JUZAU33 on 9/08/2017.
 */
@Configuration
@EnableJpaRepositories(
        basePackages = "com.realdolmen.thomasmore.dao"
)
@EnableTransactionManagement
public class ApplicationConfiguration {

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
        if (OS.indexOf("nux") >= 0) {
            dataSource.setDriverClassName("org.postgresql.Driver");
            dataSource.setUrl("jdbc:postgresql://localhost:5432/");
            dataSource.setUsername("javaroot");
            dataSource.setPassword("thomasmore");
        }
        else {
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://localhost:3306/");
            dataSource.setUsername("javaroot");
            dataSource.setPassword("thomasmore");
        }



        return dataSource;
    }

    @Bean
    public HibernateJpaVendorAdapter jpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(dataSource());
        entityManager.setPackagesToScan("com.realdolmen.thomasmore.domain");
        entityManager.setJpaVendorAdapter(jpaVendorAdapter());
        entityManager.setJpaProperties(jpaProperties());

        return entityManager;
    }

    @Bean
    public JpaTransactionManager transactionManager(final EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);

        return transactionManager;
    }

    Properties jpaProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");

        return properties;
    }
}
