/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.service;

import com.opgea.visitors.web.dto.FlatDTO;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface FlatService {
    
    public FlatDTO create(FlatDTO designationDTO);
    public FlatDTO update(FlatDTO designationDTO);
    public FlatDTO remove(Long id);
    public FlatDTO find(Long id);
    public List<FlatDTO> findAll();
    public List<FlatDTO> findAllByAppartmentId(Long departmentId);
}
