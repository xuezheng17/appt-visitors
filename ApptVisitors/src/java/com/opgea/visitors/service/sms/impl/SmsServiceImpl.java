/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.service.sms.impl;

import com.opgea.visitors.dao.VisitorDAO;
import com.opgea.visitors.domain.entities.Person;
import com.opgea.visitors.domain.entities.Visitor;
import com.opgea.visitors.domain.model.SmsModel;
import com.opgea.visitors.service.sms.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ramesh
 */
@Service
public class SmsServiceImpl implements SmsService{

    @Autowired
    private VisitorDAO visitorDAO;

    public SmsModel getSmsModel(Long visitorId) {
        Visitor visitor = visitorDAO.find(visitorId);
        Person person = visitor.getEmployee();
                
        SmsModel smsModel = new SmsModel();
        smsModel.setVisitorId(visitor.getId());
        smsModel.setVisitorName(visitor.getName());
        smsModel.setVisitorContact(visitor.getContactNo());
        smsModel.setVisitorFrom(visitor.getFromCompany());
        
        smsModel.setToPhoneNo(person.getContactNo());
        smsModel.setToName(person.getFirstName()+" "+person.getMiddleInitial()+" "+person.getLastName());
        smsModel.setToFlatNo(person.getFlat().getFlatNo());
        
        smsModel.setFrom("ACL");
        smsModel.setMessage("New Notification");
        return smsModel;
    }
    
}
