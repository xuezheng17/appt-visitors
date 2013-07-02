/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.visitors.web.controller;

import com.opgea.constraints.SessionConstraints;
import com.opgea.visitors.domain.model.ExtJSFormResult;
import com.opgea.visitors.domain.model.FileUploadBean;
import com.opgea.visitors.domain.model.JsonModelMap;
import com.opgea.visitors.domain.model.SessionData;
import com.opgea.visitors.domain.qualifier.PersonType;
import com.opgea.visitors.service.PersonService;
import com.opgea.visitors.web.dto.PersonDTO;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Ramesh
 */
@Controller
@RequestMapping(value="/user")
public class PersonController {
    
    private static final String FILE_UPLOAD_SUCCESS_RESPONSE = 
		"{\"success\":%s,\"message\":\"%s\",\"fileData\":{\"size\":%s," +
		" \"name\":\"%s\", \"type\":\"%s\"}}";
    
    @Autowired
    private PersonService personService;
    
    
    protected String buildSuccessFileUploadResonse(String message, long fileSize, String fileName, String fileType){
		return String.format(FILE_UPLOAD_SUCCESS_RESPONSE, Boolean.TRUE, message,fileSize,fileName,fileType);
	}

    
    @RequestMapping(value="create", method=RequestMethod.POST)
    public @ResponseBody Map<String, Object> create(PersonDTO personDTO, 
                                HttpServletRequest request,
                                HttpServletResponse response) throws IOException{
        
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute(SessionConstraints.SESSION_DATA.name());
        
        System.out.println("Flat Name: "+personDTO.getFlatHiddenName());
        System.out.println("Appartment Name: "+personDTO.getAppartmentHiddenName());
        
        personDTO.setCompanyId(sessionData.getCompanyId());
        personDTO = personService.create(personDTO);
        
        return JsonModelMap.success().data(personDTO.getFirstName()+" saved successfully!");
    }
    
    @RequestMapping(value="personList", method= RequestMethod.GET)
    public @ResponseBody
            Map<String, Object> getPersonList(HttpServletRequest request){
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute(SessionConstraints.SESSION_DATA.name());
        List<PersonDTO> empDTOList = personService.findAllByCompanyId(sessionData.getCompanyId(), 
                                                                            PersonType.values()[sessionData.getEmployeeType()]);

        return JsonModelMap.success().data(empDTOList);
    }
    
    @RequestMapping(value="person", method= RequestMethod.GET)
    public @ResponseBody
            Map<String, Object> getPerson(@RequestParam(required=false, 
                    value="personId")Long personId,  HttpServletRequest request){
        PersonDTO empDTO = personService.find(personId);
        return JsonModelMap.success().data(empDTO);
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "uploadImage")
    public @ResponseBody
            Map<String, Object> uploadImage(FileUploadBean uploadItem, 
            BindingResult result,
            HttpServletResponse response) throws IOException{
        
        ExtJSFormResult extjsFormResult = new ExtJSFormResult();
        String status = "Success";
        if(result.hasErrors()){
            for(ObjectError error: result.getAllErrors()){
                System.err.println("Error: " + error.getCode() +  " - " + error.getDefaultMessage());
            }
            extjsFormResult.setSuccess(false);
            status = "Failure";
        }

        PersonDTO personDTO = personService.find(uploadItem.getPersonId());
        personDTO.setPicture(uploadItem.getFile().getBytes());
        personService.create(personDTO);
        
        response.setContentType("text/html");
        HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(response);
        Writer out = responseWrapper.getWriter();
        String json = buildSuccessFileUploadResonse("File uploaded Successfully", 
                                                    uploadItem.getFile().getSize(),
                                                    uploadItem.getFile().getOriginalFilename(),
                                                    uploadItem.getFile().getContentType());
        out.write(json);
        out.close();

        
        return JsonModelMap.success().data(status);
    }
    
    
    @RequestMapping(method=RequestMethod.GET, value="searchPersons", params={}  )
    public @ResponseBody Map<String, Object> searchPersons(@RequestParam(value="searchKey", required=false)
                                String searchKey, HttpServletRequest request){
        HttpSession session = request.getSession();
        SessionData sessionData = (SessionData) session.getAttribute(SessionConstraints.SESSION_DATA.name());
        //Long employeeId = Long.parseLong(request.getParameter("employeeId").toString());
        System.out.println("Search Persons Controller: "+searchKey);
        List<PersonDTO> persons = personService.searchPersons(
                sessionData.getCompanyId(), 
                searchKey, 
                PersonType.values()[sessionData.getEmployeeType()]);
        
        return JsonModelMap.success().data(persons);
    }
   
}
