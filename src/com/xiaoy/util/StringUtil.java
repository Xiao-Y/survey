package com.xiaoy.util;

/**
 * 字符串的操作
 * 
 * @author XiaoY
 * @date: 2015年5月23日 下午10:24:53
 */
public class StringUtil
{
	/**
	 * 对指定的字符串，按照指定的格式拆分
	 * 
	 * @param str
	 *            源字符串
	 * @param tag
	 *            拆分格式
	 * @return String[]
	 */
	public static String[] str2Arr(String str, String tag)
	{
		if (!ValiDateUtil.isValid(str))
		{
			String[] arr = str.split(tag);
			return arr;
		}
		return null;
	}
}
