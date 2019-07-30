package com.git.activities.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class DateConvertionUtility {
	
	public Date getWeekDateFromMilliSec(int millisec) {
		
		Long timestmp = (long) millisec * 1000;
		Date date = new Date(timestmp);
		return date;
	}
	
   public Date parseDate(String date1) throws ParseException {
	   
	   SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");  
	   java.util.Date date= formatter.parse(date1);
	return (Date) date; 
   }
}
