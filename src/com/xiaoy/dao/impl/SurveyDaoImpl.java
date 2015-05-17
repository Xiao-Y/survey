package com.xiaoy.dao.impl;

import org.springframework.stereotype.Repository;

import com.xiaoy.basic.dao.impl.BasicDaoImpl;
import com.xiaoy.dao.SurveyDao;
import com.xiaoy.entities.Survey;

@Repository("surveyDao")
public class SurveyDaoImpl extends BasicDaoImpl<Survey> implements SurveyDao
{

}
