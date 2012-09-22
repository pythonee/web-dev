package com.mashup.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeFormater {

	public String getTime() {
		Date d = new Date(); // Get current date to d.
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss"); // Set date format
		String strDate = fm.format(d); // Get date to string strDate via format fm.
		return strDate;
	}
}
