package com.assignment.survey.bean.service;

import com.assignment.survey.bean.Answer;

public interface AnswerService {
	Answer get(Long id);
	
	Answer getAnswer(Long userId, Long questionId);
	
	Long save(Answer entity);
	
	void delete(Long id);
}
