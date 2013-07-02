/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.dao;

import com.opgea.visitors.domain.entities.Flat;
import java.util.List;

/**
 *
 * @author Ramesh
 */
public interface FlatDAO {
    
    public Flat create(Flat flat);
    public Flat update(Flat flat);
    public Flat remove(Long id);
    public Flat find(Long id);
    public List<Flat> findAll();
    public List<Flat> findAllByAppartmentId(Long appartmentId);
}
