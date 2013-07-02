/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.service;

import com.opgea.visitors.web.dto.AppartmentDTO;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface AppartmentService {
    
    public AppartmentDTO create(AppartmentDTO appartmentDTO);
    public AppartmentDTO update(AppartmentDTO AppartmentDTO);
    public AppartmentDTO remove(Long id);
    public AppartmentDTO find(Long id);
    public List<AppartmentDTO> findAll();
    public List<AppartmentDTO> findAllByCompanyId(Long companyId);
}
