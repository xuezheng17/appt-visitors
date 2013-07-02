/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.web.controller;

import com.opgea.visitors.domain.model.JsonModelMap;
import com.opgea.visitors.service.FlatService;
import com.opgea.visitors.web.dto.FlatDTO;
import java.util.List;
import java.util.Map;
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
@RequestMapping(value="flat")
public class FlatController {
    
    @Autowired
    private FlatService designationService;
    
    
    @RequestMapping(method= RequestMethod.POST, value="create")
    public @ResponseBody Map<String, Object> create(FlatDTO flatDTO){
        flatDTO.setAppartmentId(flatDTO.getAppartmentId());
        designationService.create(flatDTO); 
        return JsonModelMap.success().data(flatDTO.getFlatNo());
    }
    
    @RequestMapping(method= RequestMethod.GET, value="flatList")
    public @ResponseBody Map<String, Object> getDesginationList(FlatDTO flatDTO){
        List<FlatDTO> flatList =
                designationService.findAllByAppartmentId(flatDTO.getAppartmentId());
        return JsonModelMap.success().data(flatList);
    }
}
