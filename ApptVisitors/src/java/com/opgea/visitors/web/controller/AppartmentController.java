/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.web.controller;

import com.opgea.constraints.SessionConstraints;
import com.opgea.visitors.domain.model.JsonModelMap;
import com.opgea.visitors.domain.model.SessionData;
import com.opgea.visitors.service.AppartmentService;
import com.opgea.visitors.web.dto.AppartmentDTO;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Ramesh
 */
@Controller
@RequestMapping(value="appartment")
public class AppartmentController {
    
     @Autowired
    private AppartmentService appartmentService;
    
    
    @RequestMapping(method= RequestMethod.POST, value="create")
    public @ResponseBody Map<String, Object> create(AppartmentDTO appartmentDTO,
                                        HttpServletRequest request){
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) 
                session.getAttribute(SessionConstraints.SESSION_DATA.name());
        appartmentDTO.setCompanyId(sessionData.getCompanyId());
        appartmentService.create(appartmentDTO); 
        return JsonModelMap.success().data(appartmentDTO.getName());
    }
    
    @RequestMapping(method= RequestMethod.GET, value="appartmentList")
    public @ResponseBody Map<String, Object> getDesginationList(AppartmentDTO appartmentDTO,
                                        HttpServletRequest request){
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) 
                session.getAttribute(SessionConstraints.SESSION_DATA.name());
        List<AppartmentDTO> appartmentList =
                appartmentService.findAllByCompanyId(sessionData.getCompanyId());
        return JsonModelMap.success().data(appartmentList);
    }
}
