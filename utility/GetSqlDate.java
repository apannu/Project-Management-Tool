package com.capgemini.cisco.portal.utility;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class GetSqlDate 
{
	public static Date getDate(String ciscoDOJ)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date date = null;
		try 
		{
			date = simpleDateFormat.parse(ciscoDOJ);
		} 
		catch (ParseException e1) 
		{
			System.out.println("Problem in solving date");
		}
		java.sql.Date currentDate = new Date(date.getTime()); 
		System.out.println(currentDate);
		return currentDate;
	}
}
