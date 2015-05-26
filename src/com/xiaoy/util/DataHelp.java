package com.xiaoy.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
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

	/**
	 * 深度复制,复制的整个对象图
	 */
	public static Serializable deeplyCopy(Serializable src)
	{
		try
		{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(src);
			oos.close();
			baos.close();

			byte[] bytes = baos.toByteArray();
			ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			Serializable copy = (Serializable) ois.readObject();
			ois.close();
			bais.close();
			return copy;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
