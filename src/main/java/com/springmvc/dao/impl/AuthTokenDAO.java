//package com.springmvc.dao.impl;
//
//import com.springmvc.entity.AuthToken;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
//import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Path;
//import javax.persistence.criteria.Root;
//import java.util.Date;
//
//@Repository("persistentTokenRepository")
//public class AuthTokenDAO implements PersistentTokenRepository {
//    @PersistenceContext
//    private final SessionFactory sessionFactory;
//
//    public AuthTokenDAO(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    @Override
//    public void createNewToken(PersistentRememberMeToken token) {
//        Transaction transaction = null;
//
//        try (Session session = sessionFactory.openSession()) {
//            transaction = session.beginTransaction();
//
//            AuthToken authToken = new AuthToken();
//
//            authToken.setSeries(token.getSeries());
//            authToken.setUsername(token.getUsername());
//            authToken.setToken(token.getTokenValue());
//            authToken.setLastAccess(token.getDate());
//
//            session.save(authToken);
//
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) transaction.rollback();
//
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void updateToken(String series, String tokenValue, Date lastUsed) {
//        Transaction transaction = null;
//
//        try (Session session = sessionFactory.openSession()) {
//            transaction = session.beginTransaction();
//
//            AuthToken authToken = findOneBySeries(series);
//
//            authToken.setToken(tokenValue);
//            authToken.setLastAccess(lastUsed);
//
//            session.update(authToken);
//
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) transaction.rollback();
//
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
//
//        AuthToken authToken = findOneBySeries(seriesId);
//
//        PersistentRememberMeToken persistentRememberMeToken = null;
//
//        if (authToken != null) {
//            persistentRememberMeToken = new PersistentRememberMeToken(
//                    authToken.getUsername(),
//                    authToken.getSeries(),
//                    authToken.getToken(),
//                    authToken.getLastAccess()
//            );
//        }
//
//        return persistentRememberMeToken;
//    }
//
//    @Override
//    public void removeUserTokens(String username) {
//        Transaction transaction = null;
//
//        try (Session session = sessionFactory.openSession()) {
//            transaction = session.beginTransaction();
//
//            Query query = session.createQuery("delete from AuthToken where username = :username");
//            query.setParameter("username", username);
//
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) transaction.rollback();
//
//            e.printStackTrace();
//        }
//    }
//
//    public AuthToken findOneBySeries(String series) {
//        try (Session session = sessionFactory.openSession()) {
//            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//            CriteriaQuery<AuthToken> query = criteriaBuilder.createQuery(AuthToken.class);
//
//            Root<AuthToken> root = query.from(AuthToken.class);
//
//            Path<String> rId = root.get("id");
//
//            query.select(root).where(criteriaBuilder.equal(rId, series));
//
//            return session.createQuery(query).getSingleResult();
//        }
//    }
//}
