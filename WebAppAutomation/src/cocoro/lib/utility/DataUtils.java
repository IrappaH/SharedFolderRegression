package cocoro.lib.utility;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import cocoro.enums.Month;
import cocoro.enums.Year;

public class DataUtils {
	public static String join(String delimiter, String... inArray) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < inArray.length; i++) {
			sb.append(inArray[i]);
			if (i < inArray.length - 1) {
				sb.append(delimiter);
			}
		}
		return sb.toString();
	}

	public static String joinPaths(String... inArray) {
		return join(File.separator, inArray);
	}
	
	public static String getCurrentYear() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy");
		Date d = new Date();
		return dateFormat.format(d);
	}
	
	public static String getPreviousYear() {
		
		Calendar cal = Calendar.getInstance();
		//Date today = cal.getTime();
		cal.add(Calendar.YEAR, -1); // to get previous year add -1
		Date previousYear = cal.getTime();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy");
		//Date d = new Date();
		return dateFormat.format(previousYear);
	}
	
	public static String getNextYear() {
		
		Calendar cal = Calendar.getInstance();
		//Date today = cal.getTime();
		cal.add(Calendar.YEAR, 1); // to get previous year add -1
		Date previousYear = cal.getTime();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy");
		//Date d = new Date();
		return dateFormat.format(previousYear);
	}
	
	public static String getAggressionPeriodDate(Year year,Month month) {
		return year.getValue()+"-"+month.getValue();
	}

	public static String getDateTimeString() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		Date d = new Date();
		return dateFormat.format(d);
	}

	public static String getDateTimeStringWithMiliSeconds() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_sss");
		Date d = new Date();
		return dateFormat.format(d);
	}

	public static boolean validateDateFormat(String format, String value) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		try {
			dateFormat.parse(value);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
	public static String getPreviousYearDateTime()
	{
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");  
	    LocalDateTime now = LocalDateTime.now();  
	    String dateAndTime = dateFormat.format(now);
	    String[] dateandTime = dateAndTime.split("\\s"); 
	    String[] time = dateandTime[1].split(":");
//	    String seconds = time[2].replaceAll(time[2],"00");
	    String[]date = dateandTime[0].split("/");
	    int year = Integer.parseInt(date[2]);
	    int yearValue =  year + 1;
//	    return (date[0]+"/"+date[1]+"/"+yearValue+" "+time[0]+":"+time[1]+":"+seconds);
	    return (date[0]+"/"+date[1]+"/"+yearValue+" "+time[0]+":"+time[1]);
	}
	
	public static String getEarlyMinuteDateTime()
	{
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");  
	    LocalDateTime now = LocalDateTime.now();  
	    String dateAndTime = dateFormat.format(now);
	    String[] dateandTime = dateAndTime.split("\\s"); 
	    String[] time = dateandTime[1].split(":");
	    int minutes = Integer.parseInt(time[1]);
	    int minutesValue = minutes + 2;
	    return (dateandTime[0]+" "+time[0]+":"+minutesValue);
	}
	
	public static String getPreviousYearDate()
	{
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");  
	    LocalDateTime now = LocalDateTime.now();  
	    String presentDate = dateFormat.format(now);
	    String[]date = presentDate.split("/");
	    int year = Integer.parseInt(date[2]);
	    int yearValue =  year + 1;
	    return (date[0]+"/"+date[1]+"/"+yearValue);
	}
	
	public static String getPresentDate()
	{
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");  
	    LocalDateTime now = LocalDateTime.now();  
	    return dateFormat.format(now);
	}
	
	public static String getPreviousDateTime()
	{
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");  
	    LocalDateTime now = LocalDateTime.now();  
	    String dateAndTime = dateFormat.format(now);
	    String[] dateandTime = dateAndTime.split("\\s"); 
	    String[]date = dateandTime[0].split("/");
	    String[] time = dateandTime[1].split(":");
	    int day = Integer.parseInt(date[1]);
	    int dayValue =  day - 1;
	    return (date[0]+"/"+dayValue+"/"+date[2]+" "+time[0]+":"+time[1]);
	}
	
	public static String getLessThen2000YearDateTime()
	{
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");  
	    LocalDateTime now = LocalDateTime.now();  
	    String dateAndTime = dateFormat.format(now);
	    String[] dateandTime = dateAndTime.split("\\s"); 
	    String[] time = dateandTime[1].split(":");
	    String[]date = dateandTime[0].split("/");
	    int year = Integer.parseInt(date[2]);
	    int yearValue =  year-20;
	    return (date[0]+"/"+date[1]+"/"+yearValue+" "+time[0]+":"+time[1]);
	}
	
	public static String getDownloadPath()
	{
		String home = System.getProperty("user.home");
		//Download folder is used since Script Execution always happens in English OS.
		home = home + "\\Downloads";
		return home; 
	}
	
	public static boolean validateMacFormat(String value)
	{
		final String PATTERN = "^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$";	
		   try {
			   Pattern pattern = Pattern.compile(PATTERN);
			   pattern.matcher(value);
//			   return matcher.matches();
				return true;
			} catch (Exception ex) {
				return false;
			}
		
		
	}
	
}
