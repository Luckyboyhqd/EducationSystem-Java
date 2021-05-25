package com.educational.utils;

import java.text.SimpleDateFormat;
import java.util.Date;


public class GetDate {

	public static Date getDate(){
		Date videoUploadTime=null;

		Date date=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1 = sdf.format(date);
		try {
			 videoUploadTime=sdf.parse(date1);

			 return  videoUploadTime;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  videoUploadTime;
	}
}
