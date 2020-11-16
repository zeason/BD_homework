package com.assignment.survey.bean.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.survey.bean.Option;
import com.assignment.survey.bean.service.OptionService;
import com.assignment.survey.dao.OptionDao;

@Service("optionService")
public class OptionServiceImpl implements OptionService {

	@Autowired  
    private OptionDao dao;
	
	@Override
	public Option get(Long id) {
		return dao.get(id);
	}

	@Override
	public Long save(Option entity) {
		return dao.save(entity);
	}

	@Override
	public void delete(Long id) {
		dao.delete(id);

	}

	@Override
	public List<Option> getByQuestionId(Long id) {
		return dao.getByQuestionId(id);
	}

}
