package com.xiaoy.dao.impl;

import org.springframework.stereotype.Repository;

import com.xiaoy.basic.dao.impl.BasicDaoImpl;
import com.xiaoy.dao.PageDao;
import com.xiaoy.entities.Page;

@Repository("pageDao")
public class PageDaoImpl extends BasicDaoImpl<Page> implements PageDao
{

}
