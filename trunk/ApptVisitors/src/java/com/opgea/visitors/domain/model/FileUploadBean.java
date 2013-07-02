package com.opgea.visitors.domain.model;




import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FileUploadBean {

    private Long personId;
    private CommonsMultipartFile file;

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public CommonsMultipartFile getFile() {
        return file;
    }

    public void setFile(CommonsMultipartFile file) {
        this.file = file;
    }
        
        
}
