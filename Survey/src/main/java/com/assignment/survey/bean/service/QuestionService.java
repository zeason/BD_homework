package com.assignment.survey.bean.service;

import java.util.List;

import com.assignment.survey.bean.Question;

public interface QuestionService {
	Question get(Long id);
	
	Long save(Question entity);
	
	List<Question> getBySurveyId(Long id);
	
	void delete(Long id);
}
