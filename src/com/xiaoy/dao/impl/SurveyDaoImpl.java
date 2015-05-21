package com.xiaoy.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xiaoy.basic.dao.impl.BasicDaoImpl;
import com.xiaoy.dao.SurveyDao;
import com.xiaoy.entities.Survey;
import com.xiaoy.entities.User;

@Repository("surveyDao")
public class SurveyDaoImpl extends BasicDaoImpl<Survey> implements SurveyDao
{

	@Override
	public List<Survey> findMySurveys(User user)
	{
		String hql = "from Survey s where s.user.id = :id";
		
		Map<String, Object> param = new HashMap<>();
		param.put("id", user.getId());
		
		List<Survey> list = this.findEntityByHQL(hql, param);
		return list;
	}
}
