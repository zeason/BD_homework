package com.assignment.survey.bean.service;

import java.util.List;

import com.assignment.survey.bean.Survey;

public interface SurveyService {
	Survey get(Long id);
	
	Long save(Survey entity);
	
	void delete(Long id);

	List<Survey> getByCreator(Long id);
	
	List<Survey> getAnswered(Long id);

	boolean isAnswered(Long id);

	void update(Survey survey);
}
