/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.dao.impl;

import com.opgea.visitors.dao.FlatDAO;
import com.opgea.visitors.domain.entities.Flat;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ramesh
 */
@Repository
public class FlatDAOImpl implements FlatDAO{

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public Flat create(Flat flat) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.save(flat);
        tx.commit();
        session.close();
        return flat;
    }

    @Override
    public Flat update(Flat flat) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.update(flat);
        tx.commit();
        session.close();
        return flat;
    }

    @Override
    public Flat remove(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");        
    }

    @Override
    public Flat find(Long id) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Flat.findById");
        query.setParameter("id", id);
        Flat flat = (Flat) query.uniqueResult();
        session.close();
        return flat;
    }

    @Override
    public List<Flat> findAll() {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Flat.findAll");
        List<Flat> flats = query.list();
        return flats;
    }

    @Override
    public List<Flat> findAllByAppartmentId(Long appartmentId) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Flat.findAllByAppartmentId");
        query.setParameter("appartmentId", appartmentId);
        List<Flat> flats = query.list();
        return flats;
    }
    
}
