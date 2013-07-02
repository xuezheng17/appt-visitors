/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.dao.impl;

import com.opgea.visitors.dao.PersonDAO;
import com.opgea.visitors.domain.entities.Person;
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
public class PersonDAOImpl implements PersonDAO{

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public Person create(Person person) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.save(person);
        tx.commit();
        session.close();
        return person;
    }

    @Override
    public Person update(Person person) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        tx.begin();
        session.update(person);
        tx.commit();
        session.close();
        return person;
    }

    @Override
    public Person remove(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Person find(Long id) {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("Person.findAllById");
        query.setParameter("id", id);
        Person person = (Person)query.uniqueResult();
        session.close();
        return person;
    }

    @Override
    public List<Person> findAll() {
        Session session = sessionFactory.openSession();
        Query query = session.getNamedQuery("from Person p");
        List<Person> persons = query.list();
        session.close();
        return persons;
    }

    @Override
    public List<Person> findAllByCompanyId(Long companyId) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Person p WHERE p.company.id="+companyId);
        List<Person> persons = query.list();
        System.out.println("Person List size: "+persons.size());
        session.close();
        return persons;
    }

    @Override
    public List<Person> findAllByDepartmentId(Long appartmentId) {
       Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Person p WHERE p.appartment.id="+appartmentId);
        List<Person> persons = query.list();
        System.out.println("Person List size: "+persons.size());
        session.close();
        return persons;
    }

    public List<Person> searchPersons(Long companyId, String searchKey) {
        Session session = sessionFactory.openSession();
        StringBuilder query = new StringBuilder();
        query.append(" SELECT p FROM Person p WHERE p.company.id = ");
        query.append(companyId);
        if(!searchKey.equalsIgnoreCase("")){
            query.append(" AND p.metaData like ");
            query.append("'%");
            query.append(searchKey);
            query.append("%'");
        }
        System.out.println("Search Query:"+query.toString());
        Query queryObject = session.createQuery(query.toString());
        List<Person> persons =  queryObject.list();
        return persons;
    }
    
}
