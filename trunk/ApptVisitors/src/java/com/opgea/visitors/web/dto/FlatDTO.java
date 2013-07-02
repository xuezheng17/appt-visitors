/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.web.dto;

/**
 *
 * @author Ramesh
 */
public class FlatDTO {
    
    private Long id;
    private String flatNo;
    private Long appartmentId;
    private String appartmentName;

    public FlatDTO() {
    }

    public Long getAppartmentId() {
        return appartmentId;
    }

    public void setAppartmentId(Long appartmentId) {
        this.appartmentId = appartmentId;
    }

    public String getAppartmentName() {
        return appartmentName;
    }

    public void setAppartmentName(String appartmentName) {
        this.appartmentName = appartmentName;
    }

    public String getFlatNo() {
        return flatNo;
    }

    public void setFlatNo(String flatNo) {
        this.flatNo = flatNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FlatDTO other = (FlatDTO) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "FlatDTO{" + "id=" + id + ", flatName=" + flatNo + ", appartmentId=" + appartmentId + ", appartmentName=" + appartmentName + '}';
    }

   
    
}
