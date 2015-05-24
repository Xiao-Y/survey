package com.xiaoy.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaoy.basic.dao.BasicDao;
import com.xiaoy.basic.service.impl.BasicServiceImpl;
import com.xiaoy.dao.AnswerDao;
import com.xiaoy.entities.Answer;
import com.xiaoy.service.AnswerService;

@Service("answerService")
public class AnswerServiceImpl extends BasicServiceImpl<Answer> implements AnswerService
{

	private AnswerDao answerDao;
	
	@Override
	@Resource
	public void setBasicDao(BasicDao<Answer> basicDao)
	{
		super.basicDao = basicDao;
		this.answerDao = (AnswerDao) basicDao;
	}

}
