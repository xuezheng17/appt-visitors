/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.web.dto;

import java.util.Date;

/**
 *
 * @author Ramesh
 */
public class PersonDTO {
    
    
    private Long id;
    private Long companyId;
    private Integer personType;
    private String personTypeName;
    private String firstName;
    private String middleInitial;
    private String lastName;
    private Date dateOfBirth;
    private Long branchId;
    
    private String email;
    private String phone1;
    private String phone2;
    private byte[] picture;
    
    private String empCode;
    private Long flatId;
    private String flatHiddenName;
    private Long appartmentId;
    private String appartmentHiddenName;
    private Integer onlineStatusId = 0;
    private String password = "";
    private String metaData;
    

    public PersonDTO() {
    }

    public PersonDTO(Long id, Long companyId, Integer personType, String personTypeName, String firstName, String middleInitial, String lastName, Date dateOfBirth, Long branchId, String email, String phone1, String phone2, byte[] picture, String empCode, Long flatId, String flatHiddenName, Long appartmentId, String appartmentHiddenName, String metaData) {
        this.id = id;
        this.companyId = companyId;
        this.personType = personType;
        this.personTypeName = personTypeName;
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.branchId = branchId;
        this.email = email;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.picture = picture;
        this.empCode = empCode;
        this.flatId = flatId;
        this.flatHiddenName = flatHiddenName;
        this.appartmentId = appartmentId;
        this.appartmentHiddenName = appartmentHiddenName;
        this.metaData = metaData;
    }

    public Long getAppartmentId() {
        return appartmentId;
    }

    public void setAppartmentId(Long appartmentId) {
        this.appartmentId = appartmentId;
    }

    public String getAppartmentHiddenName() {
        return appartmentHiddenName;
    }

    public void setAppartmentHiddenName(String appartmentHiddenName) {
        this.appartmentHiddenName = appartmentHiddenName;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Long getFlatId() {
        return flatId;
    }

    public void setFlatId(Long flatId) {
        this.flatId = flatId;
    }

    public String getFlatHiddenName() {
        return flatHiddenName;
    }

    public void setFlatHiddenName(String flatHiddenName) {
        this.flatHiddenName = flatHiddenName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

    public Integer getOnlineStatusId() {
        return onlineStatusId;
    }

    public void setOnlineStatusId(Integer onlineStatusId) {
        this.onlineStatusId = onlineStatusId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPersonType() {
        return personType;
    }

    public void setPersonType(Integer personType) {
        this.personType = personType;
    }

    public String getPersonTypeName() {
        return personTypeName;
    }

    public void setPersonTypeName(String personTypeName) {
        this.personTypeName = personTypeName;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    

    public String getMetaData() {
        StringBuilder metaBuilder = new StringBuilder();
        metaBuilder.append(this.firstName);
        metaBuilder.append(" ");
        metaBuilder.append(this.middleInitial);
        metaBuilder.append(" ");
        metaBuilder.append(this.lastName);
        metaBuilder.append(" ");
        metaBuilder.append(this.flatHiddenName);
        metaBuilder.append(" ");
        metaBuilder.append(this.appartmentHiddenName);
        metaBuilder.append(" ");
        metaBuilder.append(this.phone1);
        metaBuilder.append(" ");
        metaBuilder.append(this.phone2);
        metaBuilder.append(" ");
        metaBuilder.append(this.email);
        this.metaData = metaBuilder.toString();
        return metaData;
    }

    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }

    @Override
    public String toString() {
        return "PersonDTO{" + "id=" + id + ", companyId=" + companyId + ", personType=" + personType + ", personTypeName=" + personTypeName + ", firstName=" + firstName + ", middleInitial=" + middleInitial + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth + ", branchId=" + branchId + ", email=" + email + ", phone1=" + phone1 + ", phone2=" + phone2 + ", picture=" + picture + ", empCode=" + empCode + ", flatId=" + flatId + ", flatHiddenName=" + flatHiddenName + ", appartmentId=" + appartmentId + ", appartmentHiddenName=" + appartmentHiddenName + ", onlineStatusId=" + onlineStatusId + ", password=" + password + ", metaData=" + metaData + '}';
    }

    
    
    
    
}
