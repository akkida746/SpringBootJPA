package com.akash.SpringBootJPA;

import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import oracle.jdbc.pool.OracleDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class HibernateConfiguration {
    @Value("${spring.datasource.driver.class}")
    private String DRIVER;

    @Value("${spring.datasource.password}")
    private String PASSWORD;

    @Value("${spring.datasource.url}")
    private String URL;

    @Value("${spring.datasource.username}")
    private String USERNAME;

    @Value("${hibernate.dialect}")
    private String DIALECT;

    @Value("${hibernate.show_sql}")
    private String SHOW_SQL;

    @Value("${hibernate.hbm2ddl.auto}")
    private String HBM2DDL_AUTO;

    @Value("${entitymanager.packagesToScan}")
    private String PACKAGES_TO_SCAN;

    @Value("${hibernate.c3p0.min_size}")
    private String min_size;

    @Value("${hibernate.c3p0.max_size}")
    private String max_size;

    @Value("${hibernate.c3p0.timeout}")
    private String timeout;

    @Value("${hibernate.c3p0.max_statements}")
    private String max_statements;

    @Value("${hibernate.c3p0.idle_test_period}")
    private String time_period;

    @Value("${hibernate.c3p0.validate}")
    private String validate;

    @Value("${hibernate.connection.provider_class}")
    private String provide_class;

    /*@Bean
    public DataSource dataSource() {
        OracleDataSource dataSource = null;
        try {
            dataSource = new OracleDataSource();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dataSource.setDatabaseName("STOREHUB");
        dataSource.setURL(URL);
        dataSource.setUser(USERNAME);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }*/

    @Bean
    public ComboPooledDataSource dataSource(){
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setMinPoolSize(Integer.parseInt(min_size));
        dataSource.setMaxPoolSize(Integer.parseInt(max_size));
//        dataSource.setAcquireIncrement(acquireIncrement);
//        dataSource.setIdleConnectionTestPeriod(idleTestPeriod);
//        dataSource.setMaxStatements(maxStatements);
        dataSource.setMaxIdleTime(Integer.parseInt(time_period));
        dataSource.setJdbcUrl(URL);
        dataSource.setPassword(PASSWORD);
        dataSource.setUser(USERNAME);
        try {
            dataSource.setDriverClass(DRIVER);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() throws PropertyVetoException {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(PACKAGES_TO_SCAN);
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", DIALECT);
        hibernateProperties.put("hibernate.show_sql", SHOW_SQL);
        //hibernateProperties.put("hibernate.hbm2ddl.auto", HBM2DDL_AUTO);

        hibernateProperties.put("hibernate.c3p0.min_size", min_size);
        hibernateProperties.put("hibernate.c3p0.max_size", max_size);
        hibernateProperties.put("hibernate.c3p0.timeout", timeout);
        hibernateProperties.put("hibernate.c3p0.max_statements", max_statements);
        hibernateProperties.put("hibernate.c3p0.idle_test_period", time_period);
        hibernateProperties.put("hibernate.c3p0.validate", validate);
        //hibernateProperties.put("hibernate.connection.provider_class", provide_class);

        sessionFactory.setHibernateProperties(hibernateProperties);

        return sessionFactory;
    }

    @Bean
    public HibernateTransactionManager transactionManager() throws PropertyVetoException {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }
}
