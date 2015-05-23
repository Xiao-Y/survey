package com.xiaoy.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaoy.basic.dao.BasicDao;
import com.xiaoy.basic.service.impl.BasicServiceImpl;
import com.xiaoy.dao.QuestionDao;
import com.xiaoy.entities.Question;
import com.xiaoy.service.QuestionService;

@Service("questionService")
public class QuestionServiceImpl extends BasicServiceImpl<Question> implements QuestionService
{

	private QuestionDao questionDao;
	
	@Override
	@Resource
	public void setBasicDao(BasicDao<Question> basicDao)
	{
		super.basicDao = basicDao;
		this.questionDao = (QuestionDao) basicDao;
	}

}
