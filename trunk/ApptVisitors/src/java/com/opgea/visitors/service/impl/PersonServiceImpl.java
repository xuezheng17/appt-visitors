/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.service.impl;

import com.opgea.visitors.dao.CompanyDAO;
import com.opgea.visitors.dao.AppartmentDAO;
import com.opgea.visitors.dao.FlatDAO;
import com.opgea.visitors.dao.PersonDAO;
import com.opgea.visitors.dao.LoginDAO;
import com.opgea.visitors.domain.entities.Company;
import com.opgea.visitors.domain.entities.Appartment;
import com.opgea.visitors.domain.entities.Flat;
import com.opgea.visitors.domain.entities.Person;
import com.opgea.visitors.domain.entities.Login;
import com.opgea.visitors.domain.model.EmployeeStatus;
import com.opgea.visitors.domain.qualifier.PersonType;
import com.opgea.visitors.service.ApplicationService;
import com.opgea.visitors.service.PersonService;
import com.opgea.visitors.web.dto.PersonDTO;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ramesh
 */
@Service
public class PersonServiceImpl implements PersonService{

    @Autowired
    private PersonDAO personDAO;
    @Autowired
    private CompanyDAO companyDAO;
    @Autowired
    private FlatDAO designationDAO;
    @Autowired
    private AppartmentDAO departmentDAO;
    @Autowired
    private LoginDAO loginDAO;
    @Autowired
    private ApplicationService applicationService;
    
    @Override
    public PersonDTO create(PersonDTO personDTO) {
        Person person = null;
        Company company = companyDAO.find(personDTO.getCompanyId());
        Flat flat = designationDAO.find(personDTO.getFlatId());
        Appartment appartment = departmentDAO.find(personDTO.getAppartmentId());
        if(personDTO.getId() > 0){
            person = personDAO.find(personDTO.getId());
        }else{
            person = new Person();
        }
        
        person.setPersonType(PersonType.values()[personDTO.getPersonType()]);
        person.setFirstName(personDTO.getFirstName());
        person.setMiddleInitial(personDTO.getMiddleInitial());
        person.setLastName(personDTO.getLastName());
        person.setContactNo(personDTO.getPhone1());
        person.setCompany(company);
        person.setFlat(flat);
        person.setAppartment(appartment);
        if(personDTO.getPicture()!= null && personDTO.getPicture().length > 0){
            person.setPicture(personDTO.getPicture());
        }

        if(personDTO.getId() > 0){
            System.out.println("Person MetaData: "+personDTO.getMetaData());
            person.setMetaData(personDTO.getMetaData());
            personDAO.update(person);
        }else{
            System.out.println("Person MetaData: "+personDTO.getMetaData());
            person.setEmail(personDTO.getEmail());
            person.setMetaData(personDTO.getMetaData());
            personDAO.create(person);
            Login login = new Login();
            login.setLoginId(personDTO.getEmail());
            login.setPassword(String.valueOf(Calendar.getInstance().getTimeInMillis()));
            login.setIsActive(Boolean.TRUE);
            login.setEmployee(person);
            loginDAO.create(login);
        }

        return personDTO;

    }

    @Override
    public PersonDTO update(PersonDTO employeeDTO) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PersonDTO remove(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PersonDTO find(Long id) {
        Person person = personDAO.find(id);
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(person.getId());
        personDTO.setPersonType(person.getPersonType().ordinal());
        personDTO.setPersonTypeName(person.getPersonType().toString());
        personDTO.setFirstName(person.getFirstName());
        personDTO.setMiddleInitial(person.getMiddleInitial());
        personDTO.setLastName(person.getLastName());
        if(person.getFlat() != null){
            personDTO.setFlatId(person.getFlat().getId());
            personDTO.setFlatHiddenName(person.getFlat().getFlatNo());
        }
        personDTO.setEmail(person.getEmail());
        personDTO.setPhone1(person.getContactNo());
        personDTO.setPicture(person.getPicture());
        
        personDTO.setCompanyId(person.getCompany().getId());
        if(person.getAppartment() != null){
            personDTO.setAppartmentId(person.getAppartment().getId());
            personDTO.setAppartmentHiddenName(person.getAppartment().getName());
        }
        EmployeeStatus employeeStatus = new EmployeeStatus();
        employeeStatus.setCompanyId(personDTO.getCompanyId());
        employeeStatus.setEmployeeId(personDTO.getId());
        personDTO.setOnlineStatusId(applicationService.findEmployeeOnlineStatus(employeeStatus).ordinal());
        
        return personDTO;
    }

    @Override
    public List<PersonDTO> findAll() {
        List<Person> persons = personDAO.findAll();
        List<PersonDTO> personList = new ArrayList<PersonDTO>();
        for(Person person : persons){
            PersonDTO personDTO = new PersonDTO();
            personDTO.setId(person.getId());
            personDTO.setPersonType(person.getPersonType().ordinal());
            personDTO.setPersonTypeName(person.getPersonType().toString());
            personDTO.setFirstName(person.getFirstName());
            personDTO.setMiddleInitial(person.getMiddleInitial());
            personDTO.setLastName(person.getLastName());
            if(person.getFlat() != null){
                personDTO.setFlatId(person.getFlat().getId());
                personDTO.setFlatHiddenName(person.getFlat().getFlatNo());
            }
            personDTO.setEmail(person.getEmail());
            personDTO.setPhone1(person.getContactNo());
            personDTO.setPicture(person.getPicture());
            
            personDTO.setCompanyId(person.getCompany().getId());
            if(person.getAppartment() != null){
                personDTO.setAppartmentId(person.getAppartment().getId());
                personDTO.setAppartmentHiddenName(person.getAppartment().getName());
            }
            EmployeeStatus employeeStatus = new EmployeeStatus();
            employeeStatus.setCompanyId(personDTO.getCompanyId());
            employeeStatus.setEmployeeId(personDTO.getId());
            personDTO.setOnlineStatusId(applicationService.findEmployeeOnlineStatus(employeeStatus).ordinal());
            personList.add(personDTO);
        }
        return personList;
    }

    @Override
    public List<PersonDTO> findAllByCompanyId(Long companyId, PersonType personType) {
        List<Person> persons = personDAO.findAllByCompanyId(companyId);
        List<PersonDTO> personList = new ArrayList<PersonDTO>();
        for(Person person : persons){
            PersonDTO personDTO = new PersonDTO();
            personDTO.setId(person.getId());
            personDTO.setPersonType(person.getPersonType().ordinal());
            personDTO.setPersonTypeName(person.getPersonType().toString());
            personDTO.setFirstName(person.getFirstName());
            personDTO.setMiddleInitial(person.getMiddleInitial());
            personDTO.setLastName(person.getLastName());
            if(person.getFlat() != null){
                personDTO.setFlatId(person.getFlat().getId());
                personDTO.setFlatHiddenName(person.getFlat().getFlatNo());
            }
            personDTO.setEmail(person.getEmail());
            personDTO.setPhone1(person.getContactNo());
            personDTO.setPicture(person.getPicture());
            
            personDTO.setCompanyId(person.getCompany().getId());
            if(person.getAppartment() != null){
                personDTO.setAppartmentId(person.getAppartment().getId());
                personDTO.setAppartmentHiddenName(person.getAppartment().getName());
            }
            if(personType == PersonType.ADMIN){
                Login login = loginDAO.find(person.getEmail());
                if(login != null){
                    personDTO.setPassword(login.getPassword());
                }
            }
            EmployeeStatus employeeStatus = new EmployeeStatus();
            employeeStatus.setCompanyId(personDTO.getCompanyId());
            employeeStatus.setEmployeeId(personDTO.getId());
            personDTO.setOnlineStatusId(applicationService.findEmployeeOnlineStatus(employeeStatus).ordinal());
            personList.add(personDTO);
        }
        return personList;
    }

    @Override
    public List<PersonDTO> findAllByDepartmentId(Long departmentId) {
        List<Person> persons = personDAO.findAllByDepartmentId(departmentId);
        List<PersonDTO> personList = new ArrayList<PersonDTO>();
        for(Person person : persons){
            PersonDTO personDTO = new PersonDTO();
            personDTO.setId(person.getId());
            personDTO.setPersonType(person.getPersonType().ordinal());
            personDTO.setPersonTypeName(person.getPersonType().toString());
            personDTO.setFirstName(person.getFirstName());
            personDTO.setMiddleInitial(person.getMiddleInitial());
            personDTO.setLastName(person.getLastName());
            if(person.getFlat() != null){
                personDTO.setFlatId(person.getFlat().getId());
                personDTO.setFlatHiddenName(person.getFlat().getFlatNo());
            }
            personDTO.setEmail(person.getEmail());
            personDTO.setPhone1(person.getContactNo());
            personDTO.setPicture(person.getPicture());
            
            personDTO.setCompanyId(person.getCompany().getId());
            if(person.getAppartment() != null){
                personDTO.setAppartmentId(person.getAppartment().getId());
                personDTO.setAppartmentHiddenName(person.getAppartment().getName());
            }
            EmployeeStatus employeeStatus = new EmployeeStatus();
            employeeStatus.setCompanyId(personDTO.getCompanyId());
            employeeStatus.setEmployeeId(personDTO.getId());
            personDTO.setOnlineStatusId(applicationService.findEmployeeOnlineStatus(employeeStatus).ordinal());
            personList.add(personDTO);
        }
        return personList;
    }

    public List<PersonDTO> searchPersons(Long companyId, String searchKey, PersonType employeeType) {
        List<Person> persons = personDAO.searchPersons(companyId, searchKey);
        List<PersonDTO> personList = new ArrayList<PersonDTO>();
        for(Person person : persons){
            PersonDTO personDTO = new PersonDTO();
            personDTO.setId(person.getId());
            personDTO.setPersonType(person.getPersonType().ordinal());
            personDTO.setPersonTypeName(person.getPersonType().toString());
            personDTO.setFirstName(person.getFirstName());
            personDTO.setMiddleInitial(person.getMiddleInitial());
            personDTO.setLastName(person.getLastName());
            if(person.getFlat() != null){
                personDTO.setFlatId(person.getFlat().getId());
                personDTO.setFlatHiddenName(person.getFlat().getFlatNo());
            }
            personDTO.setEmail(person.getEmail());
            personDTO.setPhone1(person.getContactNo());
            personDTO.setPicture(person.getPicture());
            
            personDTO.setCompanyId(person.getCompany().getId());
            if(person.getAppartment() != null){
                personDTO.setAppartmentId(person.getAppartment().getId());
                personDTO.setAppartmentHiddenName(person.getAppartment().getName());
            }
            if(employeeType == PersonType.ADMIN){
                Login login = loginDAO.find(person.getEmail());
                if(login != null){
                    personDTO.setPassword(login.getPassword());
                }
            }else{
                personDTO.setPassword("");
            }
            EmployeeStatus employeeStatus = new EmployeeStatus();
            employeeStatus.setCompanyId(personDTO.getCompanyId());
            employeeStatus.setEmployeeId(personDTO.getId());
            personDTO.setOnlineStatusId(applicationService.findEmployeeOnlineStatus(employeeStatus).ordinal());
            personList.add(personDTO);
        }
        return personList;
    }
    
}
