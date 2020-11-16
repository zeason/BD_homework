package com.assignment.survey.bean.service;

import java.util.List;

import com.assignment.survey.bean.Option;

public interface OptionService {
	Option get(Long id);
	
	Long save(Option entity);
	
	List<Option> getByQuestionId(Long id);
	
	void delete(Long id);
}
