/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.service;

import com.opgea.visitors.domain.qualifier.PersonType;
import com.opgea.visitors.web.dto.PersonDTO;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface PersonService {
    
    public PersonDTO create(PersonDTO employeeDTO);
    public PersonDTO update(PersonDTO employeeDTO);
    public PersonDTO remove(Long id);
    public PersonDTO find(Long id);
    public List<PersonDTO> searchPersons(Long companyId, String searchKey, PersonType employeeType);
    public List<PersonDTO> findAll();
    public List<PersonDTO> findAllByCompanyId(Long companyId, PersonType employeeType);
    public List<PersonDTO> findAllByDepartmentId(Long departmentId);
}
