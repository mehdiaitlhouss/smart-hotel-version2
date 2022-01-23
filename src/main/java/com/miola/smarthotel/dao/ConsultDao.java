package com.miola.smarthotel.dao;

import com.miola.smarthotel.model.Consult;
import com.miola.smarthotel.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Code created by Andrius on 2020-09-26
 */
public class ConsultDao {

    public boolean createConsult(Consult consult) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(consult);
            transaction.commit();
            return transaction.getStatus() == TransactionStatus.COMMITTED;
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
        return false;
    }

    public void updateConsult(Consult consult) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(consult);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    public void deleteConsult(Consult consult) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(consult);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    public Consult getConsult(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(Consult.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<Consult> getConsults() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Consult", Consult.class).list();
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Consult> getConsultInterval() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            TypedQuery<Consult> query = session.createQuery("SELECT c FROM Consult c WHERE visitDate BETWEEN :start AND :end", Consult.class );
            query.setParameter("start", LocalDate.now());
            query.setParameter("end", LocalDate.now().plusDays(14L));
            return query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Long getNumberOfVisits() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<?> query = session.createQuery("SELECT count(c) FROM Consult c");
            return (Long) query.uniqueResult();
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0L;
        }
    }
}
