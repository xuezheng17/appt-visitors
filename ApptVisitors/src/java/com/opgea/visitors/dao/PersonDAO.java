/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.dao;

import com.opgea.visitors.domain.entities.Person;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface PersonDAO {
    
    public Person create(Person person);
    public Person update(Person person);
    public Person remove(Long id);
    public Person find(Long id);
    public List<Person> searchPersons(Long companyId, String searchKey) ;
    public List<Person> findAll();
    public List<Person> findAllByCompanyId(Long companyId);
    public List<Person> findAllByDepartmentId(Long departmentId);
}
