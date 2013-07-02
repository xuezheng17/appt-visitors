/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.service.sms;

import com.opgea.visitors.domain.model.SmsModel;

/**
 *
 * @author Ramesh
 */
public interface SmsService {
    
    public SmsModel getSmsModel(Long visitorId);
}
