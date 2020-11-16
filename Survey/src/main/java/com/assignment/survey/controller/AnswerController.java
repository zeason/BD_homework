package com.assignment.survey.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.assignment.survey.bean.Answer;
import com.assignment.survey.bean.Option;
import com.assignment.survey.bean.Question;
import com.assignment.survey.bean.Survey;
import com.assignment.survey.bean.User;
import com.assignment.survey.service.AnswerService;
import com.assignment.survey.service.OptionService;
import com.assignment.survey.service.QuestionService;
import com.assignment.survey.service.SurveyService;
import com.assignment.survey.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/answer")
public class AnswerController {
	@Autowired
	private SurveyService surveyService;
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private OptionService optionService;
	
	@Autowired
	private AnswerService answerService;
	
	@Autowired
	private UserService userService;
	
	/**
	 * Retrieve user's answers and show page
	 * 
	 * @param id
	 * @param session
	 * @param model
	 * @return answer page or error page name
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String showAnswer(@PathVariable Long id, HttpSession session, Model model) {
		Survey survey = surveyService.get(id);
		User user = (User) session.getAttribute("user");
		if (null != survey && null != user) {
			survey.setQuestions(questionService.getBySurveyId(id));
			for (Question q : survey.getQuestions()) {
				q.setOptions(optionService.getByQuestionId(q.getId()));
				Long answerId = answerService.getAnswer(user.getId(), q.getId()).getOptionId();
				for (Option o : q.getOptions()) {
					if (o.getId() == answerId) {
						o.setChecked("checked");
						break;
					}
				}
			}
			model.addAttribute("survey", survey);
			return "answer";
		} else {
			return "error";
		}
	}
	
	/**
	 * Save user's answer of survey
	 * 
	 * @param request
	 * @param id
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/save/{id}", method = RequestMethod.POST)
    public String saveAnswer(HttpServletRequest request, @PathVariable Long id, Model model) 
    throws IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
    	User user = userService.getByNameEmail(name, email);
    	if (null == user) {
    		user = new User();
    		user.setEmail(email);
    		user.setName(name);
    		userService.save(user);
    	}
    	Survey survey = surveyService.get(id);
    	if (null == survey) {
    		model.addAttribute("msg", "Survey not exist.");
    		return "error";
    	}
    	if (surveyService.getAnswered(user.getId()).stream().anyMatch(s -> s.getId().equals(id))) {
    		return String.format("redirect:/answer/%d", id);  
    	}
    	survey.setQuestions(questionService.getBySurveyId(id));
		Map<String, String[]> m = request.getParameterMap();
    	for (Question q : survey.getQuestions()) {
    		if (m.containsKey(String.valueOf(q.getId()))) {
	        	Answer answer = new Answer();
	        	answer.setQuestionId(q.getId());
	        	answer.setUserId(user.getId());
	        	q.setOptions(optionService.getByQuestionId(q.getId()));
	        	for (Option o : q.getOptions()) {
					if (o.getId().equals(Long.valueOf(request.getParameter(String.valueOf(q.getId()))))) {
						answer.setOptionId(o.getId());
						o.setChecked("checked");
						break;
					}
				}
	        	answerService.save(answer);
    		}
        }
    	model.addAttribute("suvey", survey);
    	return "answer";
    }
	
}
