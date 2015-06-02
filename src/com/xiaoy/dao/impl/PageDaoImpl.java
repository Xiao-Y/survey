package com.xiaoy.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xiaoy.basic.dao.impl.BasicDaoImpl;
import com.xiaoy.dao.PageDao;
import com.xiaoy.entities.Page;

@Repository("pageDao")
public class PageDaoImpl extends BasicDaoImpl<Page> implements PageDao
{
	@Override
	public List<Page> getNextPage(Page targPage)
	{
		String hql = "from Page p where p.survey.id = ? and p.orderno > ? order by p.orderno asc";
		List<Page> list = this.findEntityByHQL(hql, targPage.getSurvey().getId(), targPage.getOrderno());
		return list;
	}

	@Override
	public Long isLastPage(Page targPage)
	{
		String hql = "select count(*) from Page p where p.survey.id = ? and p.orderno > ? ";
		Long count = (Long) this.uniqueResult(hql, targPage.getSurvey().getId(), targPage.getOrderno());
		return count;
	}

	@Override
	public List<Page> getPrePage(Page targPage)
	{
		String hql = "from Page p where p.survey.id = ? and p.orderno < ? order by p.orderno desc";
		List<Page> list = this.findEntityByHQL(hql, targPage.getSurvey().getId(), targPage.getOrderno());
		return list;
	}

	@Override
	public Long isFirstPage(Page targPage)
	{
		String hql = "select count(*) from Page p where p.survey.id = ? and p.orderno < ? ";
		Long count = (Long) this.uniqueResult(hql, targPage.getSurvey().getId(), targPage.getOrderno());
		return count;
	}

	@Override
	public Page getFirstPage(Integer sid)
	{
		String hql = "from Page p where p.survey.id = ? order by p.orderno asc";
		List<Page> list = this.findEntityByHQL(hql, sid);
		return list.get(0);
	}
}
