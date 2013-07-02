/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.dao.impl;

import com.opgea.visitors.dao.AppartmentDAO;
import com.opgea.visitors.domain.entities.Appartment;
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
public class AppartmentDAOImpl implements AppartmentDAO{

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public Appartment create(Appartment appartment) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.save(appartment);
        tx.commit();
        session.close();
        return appartment;
    }

    @Override
    public Appartment update(Appartment appartment) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.update(appartment);
        tx.commit();
        session.close();
        return appartment;
    }

    @Override
    public Appartment remove(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");        
    }

    @Override
    public Appartment find(Long id) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Appartment.findById");
        query.setParameter("id", id);
        Appartment Appartment = (Appartment) query.uniqueResult();
        session.close();
        return Appartment;
    }

    @Override
    public List<Appartment> findAll() {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Appartment.findAll");
        List<Appartment> appartments = query.list();
        return appartments;
    }

    @Override
    public List<Appartment> findAllByCompanyId(Long companyId) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Appartment.findAllByCompanyId");
        query.setParameter("companyId", companyId);
        List<Appartment> appartments = query.list();
        return appartments;
    }
    
}
