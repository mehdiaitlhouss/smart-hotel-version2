package com.miola.smarthotel.dao;

import com.miola.smarthotel.model.User;
import com.miola.smarthotel.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 * Code created by Andrius on 2020-10-08
 */
public class UserDao {

    public User getConnectedUser(String userName, String password) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            TypedQuery<User> query = session.createQuery("SELECT id, userName, password FROM User  WHERE userName = :name AND password = :pass", User.class );
            query.setParameter("name", userName);
            query.setParameter("pass", password);
            return query.getSingleResult();
        } catch (NoResultException ex) {
            System.err.println("User not found");
            return null;
        }
    }

    public void createUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(user);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    public User getUser(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(User.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
