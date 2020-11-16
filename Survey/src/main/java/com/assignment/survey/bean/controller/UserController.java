package com.assignment.survey.bean.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.assignment.survey.bean.User;
import com.assignment.survey.bean.service.SurveyService;
import com.assignment.survey.bean.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SurveyService surveyService;
	
	/**
	 * Login if a user already signup
	 * 
	 * @param name
	 * @param email
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("/login")
	public String loginOrSignup(String name,String email,HttpSession session,Model model){
		
		User user= userService.getByNameEmail(name, email);
		if(null == user){
			user = new User();
			user.setEmail(email);
			user.setName(name);
			userService.save(user);
		}
			
		session.setAttribute("user", user);
		model.addAttribute("mycreation", surveyService.getByCreator(user.getId()));
		model.addAttribute("answered", surveyService.getAnswered(user.getId()));
		
		return "home";
	}
	
	/**
	 * For back to home page
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("/home")
	public String backHome(HttpSession session,Model model){
		
		User user= (User)session.getAttribute("user");
		if(null == user){
			return "login";
		}

		model.addAttribute("mycreation", surveyService.getByCreator(user.getId()));
		model.addAttribute("answered", surveyService.getAnswered(user.getId()));
		
		return "home";
	}
	
	/**
	 * Show login page
	 * 
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	
}
