package com.xiaoy.dao.impl;

import org.springframework.stereotype.Repository;

import com.xiaoy.basic.dao.impl.BasicDaoImpl;
import com.xiaoy.dao.AnswerDao;
import com.xiaoy.entities.Answer;

@Repository("answerDao")
public class AnswerDaoImpl extends BasicDaoImpl<Answer> implements AnswerDao
{

}
