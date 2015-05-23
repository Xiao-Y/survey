package com.xiaoy.service.impl;

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

}
