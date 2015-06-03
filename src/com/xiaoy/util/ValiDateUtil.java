package com.xiaoy.util;

import java.util.Collection;

public class ValiDateUtil
{
	/**
	 * 字符串判空
	 * 
	 * @param str
	 *            原字符串
	 * @return true 为空<br/>
	 *         false 不为空<br/>
	 */
	public static boolean isValid(String str)
	{
		return str == null || "".equals(str.trim());
	}

	/**
	 * 验证集合的有效性
	 * 
	 * @param coll
	 *            待证集合
	 * @return true 不为空<br/>
	 *         false 为空<br/>
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isValid(Collection coll)
	{
		return coll != null && coll.size() > 0;
	}

	/**
	 * 验证数组是否为空
	 * 
	 * @param coll
	 *            待证集合
	 * @return true 为空<br/>
	 *         false 不为空<br/>
	 */
	public static boolean isValid(String[] coll)
	{
		if (coll != null && coll.length > 0)
		{
			return false;
		}
		return true;
	}
}
