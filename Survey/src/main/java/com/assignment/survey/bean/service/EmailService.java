package com.assignment.survey.bean.service;

public interface EmailService {
    
    /**
     * send email
     * @throws Exception 
     */
    public void sendMailSimple(String surveyUrl, String to) throws Exception;

}
