package com.cs451.chadapplication.Entity;

import java.io.Serializable;

public class ApplicationID implements Serializable {

    private String umkcEmail;
    private String classCode;

    public ApplicationID() {
    }

    public ApplicationID(String umkcEmail, String classCode) {
        this.umkcEmail = umkcEmail;
        this.classCode = classCode;
    }

    public String getUmkcEmail() {
        return umkcEmail;
    }

    public void setUmkcEmail(String umkcEmail) {
        this.umkcEmail = umkcEmail;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }
    
}
