//package com.springmvc.dao.impl;
//
//import com.springmvc.dao.UserDAO;
//import com.springmvc.entity.User;
//import com.springmvc.type.UserRole;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Path;
//import javax.persistence.criteria.Root;
//import java.util.List;
//
//@Repository
//public class UserDAOImplTEST implements UserDAO {
//
//    @PersistenceContext
//    protected EntityManager entityManager;
//
//    @Override
//    public List<User> findAll() {
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
//
//        Root<User> root = query.from(User.class);
//
//        query.select(root);
//
//        return entityManager.createQuery(query).getResultList();
//    }
//
//    @Override
//    public List<User> findManyByRole(UserRole role) {
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
//
//        Root<User> user = query.from(User.class);
//
//        Path<UserRole> _role = user.get("role");
//
//        query.select(user).where(criteriaBuilder.equal(_role, role));
//
//        return entityManager.createQuery(query).getResultList();
//    }
//
//    @Override
//    public User findOneById(int id) {
//        try (Session session = sessionFactory.openSession()) {
//            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//            CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
//
//            Root<User> root = query.from(User.class);
//
//            Path<Integer> rId = root.get("id");
//
//            query.select(root).where(criteriaBuilder.equal(rId, id));
//
//            return entityManager.createQuery(query).getSingleResult();
//        }
//    }
//
//    @Override
//    public User findOneByUsername(String username) {
//        try (Session session = sessionFactory.openSession()) {
//            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//            CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
//
//            Root<User> root = query.from(User.class);
//
//            Path<String> rUsername = root.get("username");
//
//            query.select(root).where(criteriaBuilder.equal(rUsername, username));
//
//            return entityManager.createQuery(query).getSingleResult();
//        }
//    }
//
//    @Override
//    public User save(User user) {
//        return entityManager.save(user);
//    }
//
//    @Override
//    public void delete(int id) {
//        Transaction transaction = null;
//
//        try (Session session = sessionFactory.openSession()) {
//            transaction = session.beginTransaction();
//
//            User user = findOneById(id);
//
//            session.delete(session.merge(user));
//
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) transaction.rollback();
//
//            e.printStackTrace();
//        }
//    }
//}

//package com.springmvc.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.persistence.SharedCacheMode;
//import javax.persistence.ValidationMode;
//import javax.sql.DataSource;
//import java.util.Properties;
//
//@Configuration
//@EnableTransactionManagement
//@ComponentScan(basePackages = {"com.springmvc"})
//@PropertySource({"classpath:application.properties"})
//public class HibernateConfig {
//
//    private final Environment env;
//
//    public HibernateConfig(Environment env) {
//        this.env = env;
//    }
//
//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//
//        dataSource.setDriverClassName(env.getRequiredProperty("jdbc.driverClassName"));
//        dataSource.setUrl(env.getRequiredProperty("jdbc.url"));
//        dataSource.setUsername(env.getRequiredProperty("jdbc.username"));
//        dataSource.setPassword(env.getRequiredProperty("jdbc.password"));
//
//        return dataSource;
//    }
//
//    @Bean
//    LocalContainerEntityManagerFactoryBean entityManager() {
//        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//
//        factory.setJpaVendorAdapter(this.jpaVendorAdapter());
//        factory.setDataSource(dataSource());
//        factory.setPackagesToScan("com.springmvc.entity");
//        factory.setJpaProperties(this.hibernateProperties());
//        factory.setSharedCacheMode(SharedCacheMode.ENABLE_SELECTIVE);
//        factory.setValidationMode(ValidationMode.NONE);
//
//        return factory;
//    }
//
//    @Bean
//    public JpaVendorAdapter jpaVendorAdapter() {
//        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
//
//        hibernateJpaVendorAdapter.setShowSql(true);
//        hibernateJpaVendorAdapter.setGenerateDdl(false);
//        hibernateJpaVendorAdapter.setDatabasePlatform(env.getRequiredProperty("hibernate.dialect"));
//
//        return hibernateJpaVendorAdapter;
//    }
//
//    private Properties hibernateProperties() {
//        Properties properties = new Properties();
//
//        properties.put("javax.persistence.schema-generation.database.action", "none");
//        properties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
//        properties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
//        properties.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
//        properties.put("hibernate.current_session_context_class", env.getRequiredProperty("hibernate.current_session_context_class"));
//        properties.put("hibernate.hbm2ddl_auto", env.getRequiredProperty("hibernate.hbm2ddl_auto"));
//
//        //Setting C3P0 properties
//        //properties.put(C3P0_MIN_SIZE, env.getProperty("hibernate.c3p0.min_size"));
//        //properties.put(C3P0_MAX_SIZE, env.getProperty("hibernate.c3p0.max_size"));
//        //properties.put(C3P0_ACQUIRE_INCREMENT, env.getProperty("hibernate.c3p0.acquire_increment"));
//        //properties.put(C3P0_TIMEOUT, env.getProperty("hibernate.c3p0.timeout"));
//        //properties.put(C3P0_MAX_STATEMENTS, env.getProperty("hibernate.c3p0.max_statements"));
//        return properties;
//    }
//
//    @Bean
//    public JpaTransactionManager transactionManager() {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(entityManager().getObject());
//
//        return transactionManager;
//    }
//}