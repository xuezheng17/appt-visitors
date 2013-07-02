/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.domain.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ramesh
 */
@Entity
@Table(name = "flat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Flat.findById", query = "SELECT f FROM Flat f WHERE f.id = :id"),
    @NamedQuery(name = "Flat.findAll", query = "SELECT f FROM Flat f"),
    @NamedQuery(name = "Flat.findAllByAppartmentId", query = "SELECT f FROM Flat f WHERE f.appartment.id = :appartmentId")
 }
)
public class Flat implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    private String flatNo;
    /*
    @ManyToOne
    private Company company;
     * 
     */
    @ManyToOne
    private Appartment appartment;

    public Flat() {
    }

    /*
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
     */ 
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlatNo() {
        return flatNo;
    }

    public void setFlatNo(String flatNo) {
        this.flatNo = flatNo;
    }

    public Appartment getAppartment() {
        return appartment;
    }

    public void setAppartment(Appartment appartment) {
        this.appartment = appartment;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Flat other = (Flat) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Designation{" + "id=" + id + ", flatNo=" + flatNo + ", appartment=" + appartment + '}';
    }
    
    
}
