/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.dao;

import com.opgea.visitors.domain.entities.Appartment;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface AppartmentDAO {
    
    public Appartment create(Appartment appartment);
    public Appartment update(Appartment appartment);
    public Appartment remove(Long id);
    public Appartment find(Long id);
    public List<Appartment> findAll();
    public List<Appartment> findAllByCompanyId(Long companyId);
}
