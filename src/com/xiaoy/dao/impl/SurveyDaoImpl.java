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

	@Override
	public void toggleStatus(Integer sid)
	{
		Survey s = this.getEntity(sid);
		//在Survey的closed字段中添加了upate=false,作用就是当使用hibernate中的update时不更新，但是使用HQL语句会更新
		String hql = "update Survey s set s.closed = ? where s.id = ?";
		this.batchEntityByHQL(hql, !s.getClosed(), sid);
	}

	@Override
	public List<Survey> findAllAvailableSurveys()
	{
		String hql = "from Survey s where s.closed = ?";
		return this.findEntityByHQL(hql, false);
	}
}
