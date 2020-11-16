package com.assignment.survey.service;

public interface EmailService {
    
    /**
     * send email
     * @throws Exception 
     */
    public void sendMailSimple(String surveyUrl, String to) throws Exception;

}
