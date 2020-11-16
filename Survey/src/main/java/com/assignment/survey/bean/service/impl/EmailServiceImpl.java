package com.assignment.survey.bean.service.impl;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.assignment.survey.bean.service.EmailService;

@Service("emailService")
public class EmailServiceImpl implements EmailService {
	
	@Autowired
    private JavaMailSender javaMailSender;
	@Autowired
    private SimpleMailMessage simpleMailMessage;

    /**
     * @param surveyUrl survey's url
     * @param to             receiver email address
     */
    @Override
    public void sendMailSimple(String surveyUrl, String to) throws Exception {

        try {
            simpleMailMessage.setTo(to);
            //simpleMailMessage.setSubject("You have a survey");
            simpleMailMessage.setText(String.format("Please click the link to access the suvey:\n %s", surveyUrl));

            javaMailSender.send(simpleMailMessage);

        } catch (Exception e) {
            throw new MessagingException("failed to send mail!", e);
        }
    }
}