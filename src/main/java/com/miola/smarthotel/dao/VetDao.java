package com.miola.smarthotel.dao;

import com.miola.smarthotel.model.Veterinarian;
// import com.miola.smarthotel.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Code created by Andrius on 2020-09-26
 */
public class VetDao {

    public Boolean createVet(Veterinarian veterinarian) {
        /*Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(veterinarian);
            transaction.commit();
            return transaction.getStatus() == TransactionStatus.COMMITTED;
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }*/
        return false;
    }

    public void updateVet(Veterinarian veterinarian) {
//        Transaction transaction = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            session.update(veterinarian);
//            transaction.commit();
//        } catch (Exception ex) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            ex.printStackTrace();
//        }
    }

    public void deleteVet(Veterinarian veterinarian) {
        /*Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(veterinarian);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }*/
    }

    public Veterinarian getVet(Long id) {
        /*try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(Veterinarian.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

         */
        return null;
    }

    public List<Veterinarian> getVets() {
        /*try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT  FROM Veterinarian v", Veterinarian.class).list();
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }*/
        return  null;
    }

    public Long getVetsNumber() {
        /*try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<?> query = session.createQuery("SELECT count(v) FROM Veterinarian v");
            return (Long) query.uniqueResult();
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0L;
        }*/
        return 0L;
    }
}
