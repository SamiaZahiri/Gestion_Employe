/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import classe.Profile;
import dao.IDao;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author PC
 */
public class ProfileService implements IDao<Profile>{

    @Override
    public boolean create(Profile o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(o);
            tx.commit();
            session.close();
            return true;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            session.close();
            return false;
        }
        }

    @Override
    public boolean update(Profile o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(o);
            tx.commit();
            session.close();
            return true;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            session.close();
            return false;
        }
        }

    @Override
    public boolean delete(Profile o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(o);
            tx.commit();
            session.close();
            return true;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            session.close();
            return false;
        }
        }

    @Override
    public List<Profile> findAll() {
        List<Profile> profils = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            profils = session.createQuery("from Profile").list();
            tx.commit();
            session.close();
            return profils;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            session.close();
            return profils;
        }
        }

    @Override
    public Profile findById(int id) {
        Profile p = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            p = (Profile) session.get(Profile.class, id);
            tx.commit();
            session.close();
            return p;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            session.close();
            return p;
        }
        }
    
}
