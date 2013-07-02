/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.service.impl;

import com.opgea.visitors.dao.CompanyDAO;
import com.opgea.visitors.dao.AppartmentDAO;
import com.opgea.visitors.domain.entities.Company;
import com.opgea.visitors.domain.entities.Appartment;
import com.opgea.visitors.service.AppartmentService;
import com.opgea.visitors.web.dto.AppartmentDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ramesh
 */
@Service
public class AppartmentServiceImpl implements AppartmentService{

    
    @Autowired
    private AppartmentDAO appartmentDAO;
    
    @Autowired
    private CompanyDAO companyDAO;
    
    @Override
    public AppartmentDTO create(AppartmentDTO appartmentDTO) {
        Appartment appartment = new Appartment();
        if(appartmentDTO.getId() > 0){
            appartment = appartmentDAO.find(appartmentDTO.getId());
        }
        
        appartment.setName(appartmentDTO.getName());
        
        if(appartmentDTO.getId() > 0){
            appartmentDAO.update(appartment);
        }else{
            Company company = companyDAO.find(appartmentDTO.getCompanyId());
            appartment.setCompany(company);
            appartmentDAO.create(appartment);
        }
        return appartmentDTO;
    }

    @Override
    public AppartmentDTO update(AppartmentDTO appartmentDTO) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public AppartmentDTO remove(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public AppartmentDTO find(Long id) {
        Appartment appartment = appartmentDAO.find(id);
        AppartmentDTO appartmentDTO = new AppartmentDTO();
        appartmentDTO.setId(appartment.getId());
        appartmentDTO.setName(appartment.getName());
        //designationDTO.setCompanyId(designation.getCompany().getId());
        return appartmentDTO;
    }

    @Override
    public List<AppartmentDTO> findAll() {
        List<Appartment> appartments = appartmentDAO.findAll();
        List<AppartmentDTO> appartmentList = new ArrayList<AppartmentDTO>();
        for(Appartment appartment: appartments){
            AppartmentDTO appartmentDTO = new AppartmentDTO();
            appartmentDTO.setId(appartment.getId());
            appartmentDTO.setName(appartment.getName());
            //designationDTO.setCompanyId(designation.getCompany().getId());
            appartmentList.add(appartmentDTO);
        }
        return appartmentList;
    }

    @Override
    public List<AppartmentDTO> findAllByCompanyId(Long companyId) {
        List<Appartment> appartments = appartmentDAO.findAllByCompanyId(companyId);
        List<AppartmentDTO> appartmentList = new ArrayList<AppartmentDTO>();
        for(Appartment appartment: appartments){
            AppartmentDTO appartmentDTO = new AppartmentDTO();
            appartmentDTO.setId(appartment.getId());
            appartmentDTO.setName(appartment.getName());
            //designationDTO.setCompanyId(designation.getCompany().getId());
            appartmentList.add(appartmentDTO);
        }
        return appartmentList;
    }

    
}
