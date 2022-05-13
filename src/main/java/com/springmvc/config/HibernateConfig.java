package com.springmvc.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

// Ha senso avere la configurazione ancora come singleton al posto di @Bean?
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.springmvc"})
@PropertySource({"classpath:application.properties"})
public class HibernateConfig {

    private final Environment env;

    public HibernateConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.springmvc.entity");
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(env.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(env.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(env.getRequiredProperty("jdbc.password"));

        return dataSource;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();

        properties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.current_session_context_class", env.getRequiredProperty("hibernate.current_session_context_class"));
        properties.put("hibernate.hbm2ddl_auto", env.getRequiredProperty("hibernate.hbm2ddl_auto"));

        return properties;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();

        txManager.setSessionFactory(s);

        return txManager;
    }

//    public static SessionFactory getSessionFactory() {
//        if (sessionFactory == null) {
//            try {
//                Configuration configuration = new Configuration();
//
//                Properties settings = new Properties();
//
//                settings.put(Environment.DRIVER, "org.postgresql.Driver");
//                settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/rentalcar?useSSL=false");
//                settings.put(Environment.USER, "postgres");
//                settings.put(Environment.PASS, "asdasd");
//                settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
//
//                settings.put(Environment.SHOW_SQL, "true");
//
//                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
//
//                settings.put(Environment.HBM2DDL_AUTO, "update");
//
//                configuration.setProperties(settings);
//
//                configuration.addAnnotatedClass(User.class);
//                configuration.addAnnotatedClass(Vehicle.class);
//                configuration.addAnnotatedClass(Reservation.class);
//
//                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//                        .applySettings(configuration.getProperties())
//                        .build();
//
//                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        return sessionFactory;
//    }
}