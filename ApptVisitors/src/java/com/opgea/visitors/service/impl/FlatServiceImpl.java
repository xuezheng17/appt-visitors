/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.service.impl;

import com.opgea.visitors.dao.AppartmentDAO;
import com.opgea.visitors.dao.FlatDAO;
import com.opgea.visitors.domain.entities.Appartment;
import com.opgea.visitors.domain.entities.Flat;
import com.opgea.visitors.service.FlatService;
import com.opgea.visitors.web.dto.FlatDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ramesh
 */
@Service
public class FlatServiceImpl implements FlatService{

    @Autowired
    private FlatDAO flatDAO;
    
    @Autowired
    private AppartmentDAO appartmentDAO;
    
    @Override
    public FlatDTO create(FlatDTO flatDTO) {
        Flat flat = new Flat();
        if(flatDTO.getId() > 0){
            flat = flatDAO.find(flatDTO.getId());
        }
        
        flat.setFlatNo(flatDTO.getFlatNo());
        
        if(flatDTO.getId() > 0){
            flatDAO.update(flat);
        }else{
            Appartment appartment = appartmentDAO.find(flatDTO.getAppartmentId());
            flat.setAppartment(appartment);
            flatDAO.create(flat);
        }
        
        return flatDTO;
    }

    @Override
    public FlatDTO update(FlatDTO designationDTO) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public FlatDTO remove(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public FlatDTO find(Long id) {
        Flat flat = flatDAO.find(id);
        FlatDTO flatDTO = new FlatDTO();
        flatDTO.setId(flat.getId());
        flatDTO.setFlatNo(flat.getFlatNo());
        flatDTO.setAppartmentId(flat.getAppartment().getId());
        flatDTO.setAppartmentName(flat.getAppartment().getName());
        //designationDTO.setCompanyId(designation.getCompany().getId());
        return flatDTO;
    }

    @Override
    public List<FlatDTO> findAll() {
        List<Flat> flats = flatDAO.findAll();
        List<FlatDTO> flatList = new ArrayList<FlatDTO>();
        for(Flat flat : flats){
            FlatDTO flatDTO = new FlatDTO();
            flatDTO.setId(flat.getId());
            flatDTO.setFlatNo(flat.getFlatNo());
            flatDTO.setAppartmentId(flat.getAppartment().getId());
            flatDTO.setAppartmentName(flat.getAppartment().getName());
            //designationDTO.setCompanyId(designation.getCompany().getId());
            flatList.add(flatDTO);
        }
        return flatList;
    }

    @Override
    public List<FlatDTO> findAllByAppartmentId(Long appartmentId) {
        List<Flat> flats = flatDAO.findAllByAppartmentId(appartmentId);
        List<FlatDTO> flatList = new ArrayList<FlatDTO>();
        for(Flat flat : flats){
            FlatDTO flatDTO = new FlatDTO();
            flatDTO.setId(flat.getId());
            flatDTO.setFlatNo(flat.getFlatNo());
            flatDTO.setAppartmentId(flat.getAppartment().getId());
            flatDTO.setAppartmentName(flat.getAppartment().getName());
            //designationDTO.setCompanyId(designation.getCompany().getId());
            flatList.add(flatDTO);
        }
        return flatList;
    }
    
}
