package com.xiaoy.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DataHelp
{
	/**
	 * MD5加密
	 * 
	 * @param str
	 *            原数据
	 * @return 加密后的数据
	 */
	public static String md5(String str)
	{
		char[] chars = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		StringBuffer buffer = new StringBuffer("");
		byte[] bytes = str.getBytes();

		try
		{
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] targ = md.digest(bytes);
			for (byte b : targ)
			{
				buffer.append(chars[(b >> 4) & 0x0F]);
				buffer.append(chars[b & 0x0F]);
			}
			return buffer.toString();
		} catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
