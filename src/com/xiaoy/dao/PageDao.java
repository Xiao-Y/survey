package com.xiaoy.dao;

import java.util.List;

import com.xiaoy.basic.dao.BasicDao;
import com.xiaoy.entities.Page;

public interface PageDao extends BasicDao<Page>
{

	/**
	 * 查询指定页面的下一页
	 * @param targPage
	 * @return
	 */
	List<Page> getNextPage(Page targPage);

	/**
	 * 判断指定页面是否是所在调查尾页
	 * @param targPage
	 * @return
	 */
	Long isLastPage(Page targPage);

	/**
	 * 查询指定页面的上一页
	 * @param targPage
	 * @return
	 */
	List<Page> getPrePage(Page targPage);

	/**
	 * 判断指定页面是否是所在调查首页
	 * @param targPage
	 * @return
	 */
	Long isFirstPage(Page targPage);

}
