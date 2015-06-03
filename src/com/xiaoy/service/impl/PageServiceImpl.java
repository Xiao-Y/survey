package com.xiaoy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaoy.basic.dao.BasicDao;
import com.xiaoy.basic.service.impl.BasicServiceImpl;
import com.xiaoy.dao.PageDao;
import com.xiaoy.entities.Page;
import com.xiaoy.service.PageService;

@Service("pageService")
public class PageServiceImpl extends BasicServiceImpl<Page> implements PageService
{

	private PageDao pageDao;
	
	@Override
	@Resource(name="pageDao")
	public void setBasicDao(BasicDao<Page> basicDao)
	{
		super.basicDao = basicDao;
		this.pageDao = (PageDao) basicDao;
	}

	@Override
	public Page getFirstPage(Integer sid)
	{
		return pageDao.getFirstPage(sid);
	}

	@Override
	public Page getPrePage(Integer currPid)
	{
		Page p = pageDao.getEntity(currPid);
		List<Page> list = pageDao.getPrePage(p);
		return list.get(0);
	}

	@Override
	public Page getNextPaeg(Integer currPid)
	{
		Page p = pageDao.getEntity(currPid);
		List<Page> list = pageDao.getNextPage(p);
		return list.get(0);
	}
}
