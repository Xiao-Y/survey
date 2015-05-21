package com.xiaoy.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class App
{
	public static void main(String[] args)
	{
		char[] chars = new char[]{'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		StringBuffer buffer = new StringBuffer("");
		String str = "abc";
		byte[] bytes = str.getBytes();
		
		try
		{
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] targ = md.digest(bytes);
			for(byte b : targ)
			{
				buffer.append(chars[(b >> 4) & 0x0F]);
				buffer.append(chars[b & 0x0F]);
			}
			System.out.println(buffer.toString());
		} catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
	}
}
