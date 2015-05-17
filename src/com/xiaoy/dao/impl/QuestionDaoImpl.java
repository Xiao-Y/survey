package com.xiaoy.dao.impl;

import org.springframework.stereotype.Repository;

import com.xiaoy.basic.dao.impl.BasicDaoImpl;
import com.xiaoy.dao.QuestionDao;
import com.xiaoy.entities.Question;


@Repository("questionDao")
public class QuestionDaoImpl extends BasicDaoImpl<Question> implements QuestionDao
{

}
