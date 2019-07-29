package com.git.activities.utils;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component
public class DateConvertionUtility {
	
	public Date getWeekDateFromMilliSec(Long timestamp) {
		
		Long timestmp = (long) timestamp * 1000;
		Date date = new Date(timestamp);
		return date;
	}
	

}
