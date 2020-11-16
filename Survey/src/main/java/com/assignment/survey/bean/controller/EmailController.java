package com.assignment.survey.bean.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.assignment.survey.bean.service.EmailService;

@Controller
public class EmailController {
	@Autowired
	EmailService emailService;

	/**
	 * Send survey url to user
	 * 
	 * @param surveyUrl
	 * @param to
	 * @param request
	 * @return
	 */
	@RequestMapping("sendEmail")
	public String sendEmailTask(String surveyUrl, String to, HttpServletRequest request) {
		try {
			emailService.sendMailSimple(surveyUrl, to);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String referer = request.getHeader("Referer");
	    return "redirect:"+ referer;
	}

}
