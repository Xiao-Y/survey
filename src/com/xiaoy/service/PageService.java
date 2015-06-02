package com.xiaoy.service;

import com.xiaoy.basic.service.BasicService;
import com.xiaoy.entities.Page;

public interface PageService extends BasicService<Page>
{

	/**
	 * 查询调查的首页
	 * @param sid
	 * @return
	 */
	Page getFirstPage(Integer sid);

}
