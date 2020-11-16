package com.assignment.survey.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.assignment.survey.SurveySerializer;
import com.assignment.survey.bean.Option;
import com.assignment.survey.bean.Question;
import com.assignment.survey.bean.Survey;
import com.assignment.survey.bean.User;
import com.assignment.survey.service.OptionService;
import com.assignment.survey.service.QuestionService;
import com.assignment.survey.service.SurveyService;

@Controller
@RequestMapping("/survey")
public class SurveyController {
	@Autowired
	private SurveyService surveyService;
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private OptionService optionService;
	
	/**
	 * Show survey for user to answer
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String showSurvey(@PathVariable Long id, Model model) {
		Survey survey = surveyService.get(id);
		if (null != survey) {
			List<Question> questions = questionService.getBySurveyId(id);
			for (Question q : questions) {
				q.setOptions(optionService.getByQuestionId(q.getId()));
			}
			survey.setQuestions(questions);
			model.addAttribute("survey", survey);
			return "survey";
		} else {
			return "error";
		}
	}
	
	/**
	 * Save user-created survey to database
	 * 
	 * @param json
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/new", method = RequestMethod.POST)
    public String saveSurvey(@RequestBody String json, HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (null == user) {
			model.addAttribute("msg", "Not login");
			return "error";
		}
		Survey survey = SurveySerializer.deserialize(json);
		survey.setCreator(user.getId());
        Long surveyId = surveyService.save(survey);
        List<Question> list = survey.getQuestions();
        for (int i = 1; i <= list.size(); i++) {
        	Question q = list.get(i - 1);
        	q.setSurveyId(surveyId);
        	q.setOrder(i);
        	Long questionId = questionService.save(q);
        	List<Option> optionList = q.getOptions();
        	for (int j = 1; j <= optionList.size(); j++) {
        		Option o = optionList.get(j - 1);
        		o.setQuestionId(Long.valueOf(questionId));
        		o.setOrder(j);
        		optionService.save(o);
        	}
        }
        model.addAttribute("survey", survey);
        return "survey";
    }
	
	/**
	 * Update survey after user edited it
	 * 
	 * @param id
	 * @param json
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String updateSurvey(@PathVariable Long id, @RequestBody String json, HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (null == user) {
			model.addAttribute("msg", "Not login");
			return "error";
		}
		Survey survey = SurveySerializer.deserialize(json);
		Survey oldSurvey = surveyService.get(id);
		if (!surveyService.isAnswered(id) && user.getId().equals(oldSurvey.getCreator())) {
			survey.setId(id);
			survey.setCreator(user.getId());
	        surveyService.update(survey);
	        for (Question q : questionService.getBySurveyId(id)) {
	        	for (Option o : optionService.getByQuestionId(q.getId())) {
	        		optionService.delete(o.getId());
	        	}
	        	questionService.delete(q.getId());
	        }
	        List<Question> list = survey.getQuestions();
	        for (int i = 1; i <= list.size(); i++) {
	        	Question q = list.get(i - 1);
	        	q.setSurveyId(id);
	        	q.setOrder(i);
	        	Long questionId = questionService.save(q);
	        	List<Option> optionList = q.getOptions();
	        	for (int j = 1; j <= optionList.size(); j++) {
	        		Option o = optionList.get(j - 1);
	        		o.setQuestionId(Long.valueOf(questionId));
	        		o.setOrder(j);
	        		optionService.save(o);
	        	}
	        }
	        model.addAttribute("survey", survey);
	        return "survey";
		}
		model.addAttribute("msg", "Survey already published or you are not allowed to modify.");
		return "error";
    }
	
	/**
	 * Delete survey
	 * 
	 * @param id
	 * @param request
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/deleteSurvey/{id}")
    public String deleteSurvey(@PathVariable Long id, HttpServletRequest request, HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (null == user) {
			model.addAttribute("msg", "Not login");
			return "error";
		}
		Survey survey = surveyService.get(id);
		if (!surveyService.isAnswered(survey.getId())) {
	        for (Question q : questionService.getBySurveyId(survey.getId())) {
	        	for (Option o : optionService.getByQuestionId(q.getId())) {
	        		optionService.delete(o.getId());
	        	}
	        	questionService.delete(q.getId());
	        }
	        surveyService.delete(survey.getId());
	        model.addAttribute("mycreation", surveyService.getByCreator(user.getId()));
			model.addAttribute("answered", surveyService.getAnswered(user.getId()));
			String referer = request.getHeader("Referer");
		    return "redirect:"+ referer;
		} 
		model.addAttribute("msg", "Survey not exist");
		return "error";
    }
	
	/**
	 * Show survey editor for creating new survey
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/newSurvey", method = RequestMethod.GET)
	public String showSurveyEditor(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (null != user) {
			model.addAttribute("json", "");
			model.addAttribute("id", 0);
			model.addAttribute("userId", user.getId());
			return "editor";
		} else {
			model.addAttribute("msg", "Not login");
			return "error";
		}
	}
	
	/**
	 * Show survey editor for editing existed one
	 * 
	 * @param id
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/editSurvey/{id}", method = RequestMethod.GET)
	public String startEditSurvey(@PathVariable Long id, HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (null == user) {
			model.addAttribute("msg", "Not login");
			return "error";
		}
		Survey survey = surveyService.get(id);
		if (null != survey) {
			List<Question> questions = questionService.getBySurveyId(id);
			for (Question q : questions) {
				q.setOptions(optionService.getByQuestionId(q.getId()));
			}
			survey.setQuestions(questions);
			String surveyJson = SurveySerializer.serialize(survey);
			model.addAttribute("json", surveyJson);
			model.addAttribute("id", id);
			return "editor";
		} else {
			model.addAttribute("msg", "Survey not exist");
			return "error";
		}
	}
	
	/**
	 * Send survey to user
	 * 
	 * @param id
	 * @param request
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/sendEmail/{id}")
    public String sendEmail(@PathVariable Long id, HttpServletRequest request, HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (null == user) {
			model.addAttribute("msg", "Not login");
			return "error";
		}
		model.addAttribute("url", request.getLocalAddr()+ "survey/" + id);
		return "sendEmail";
    }
}
