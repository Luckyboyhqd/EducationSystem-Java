package com.educational.utils;

import java.util.UUID;

public class UuidUtils {

	/**
	 * 工具类 生成UUID
	 */
	
	//生成UUID
	public static String getUUID()
	{
		return UUID.randomUUID().toString().replace("-", "");
	}
}
