package indorse.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.AbstractDataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 Created by vsantos on 7/03/2019.
 */
@Configuration
@EnableJpaRepositories(basePackages = "indorse.repositories")
//@PropertySource("file:${hibernate.property.file};file:${dataBase.property.file}")
@PropertySources({
        @PropertySource("file:${hibernate.property.file}"),
        @PropertySource("file:${dataBase.property.file}")
})
//@EnableJpaAuditing(auditorAwareRef = "AuthSecurityAuditorAware")
@EnableTransactionManagement
public class PersistConfiguration {

    private final Logger logger = LoggerFactory.getLogger(PersistConfiguration.class);

    @Autowired
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setPackagesToScan("indorse.model");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        emf.setJpaVendorAdapter(vendorAdapter);
        emf.setJpaProperties(additionalProperties());

        return emf;
    }

    @Bean
    public DataSource dataSource() {
        DataSource dataSource =  null;
        JndiTemplate jndiTemplate = new JndiTemplate();
        DriverManagerDataSource ds = new DriverManagerDataSource();
        try {
            // Data base configurations
         ds.setDriverClassName(env.getProperty("database.postgres.driver_class_name"));
         ds.setUrl(env.getProperty("database.postgres.url"));
         ds.setUsername(env.getProperty("database.postgres.user_name"));
         ds.setPassword(env.getProperty("database.postgres.password"));
         //dataSource = (DataSource) jndiTemplate.lookup("java:jboss/datasources/sa_authDS");
        } catch (Exception ex){
            logger.error("Exception: ", ex);
        }

        return ds ;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    Properties additionalProperties() {
        return new Properties() {
            {  // Hibernate Specific:
                setProperty("hibernate.hbm2ddl.auto", env.getProperty("database.hibernate.schema_update"));
                setProperty("hibernate.dialect", env.getProperty("database.hibernate.dialect"));
                setProperty("hibernate.show_sql", env.getProperty("database.hibernate.show_sql"));
                setProperty("hibernate.format_sql", env.getProperty("database.hibernate.format_sql"));
                setProperty("hibernate.use_sql_comments", env.getProperty("database.hibernate.use_sql_comments"));
            }
        };
    }
}

