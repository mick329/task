package task.util;

import java.util.Calendar;
import java.util.Properties;

public final class CalendarUtil {

	public static Calendar getArbitraryTime(String argument) {
		if (StringUtil.isTime(argument)) {
			String[] time = argument.split(":");
			return getArbitraryTime(time[0], time[1]);
		}
		return Calendar.getInstance();
	}

	public static Calendar getArbitraryTime(Calendar date, String argument) {
		if (StringUtil.isTime(argument)) {
			String[] time = argument.split(":");
			return getArbitraryTime(date, time[0], time[1]);
		}else{
			return date;
		}
	}

	private static Calendar getArbitraryTime(String hour, String minute) {
		return getArbitraryTime(Calendar.getInstance(), hour, minute);
	}

	private static Calendar getArbitraryTime(Calendar date, String hour, String minute) {
		date.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
		date.set(Calendar.MINUTE, Integer.parseInt(minute));
		return date;
	}

	public static Calendar getOfficeStartTime() {
		Properties prop = TaskProperties.getInstance();
		return getArbitraryTime(prop.getProperty("OFFICE_START_HOUR"), prop.getProperty("OFFICE_START_MINUTE"));
	}

	public static Calendar getOfficeEndTime() {
		Properties prop = TaskProperties.getInstance();
		return getArbitraryTime(prop.getProperty("OFFICE_END_HOUR"), prop.getProperty("OFFICE_END_MINUTE"));
	}

	public static Calendar getRestEndTime() {
		Properties prop = TaskProperties.getInstance();
		return getArbitraryTime(prop.getProperty("REST_END_HOUR"), prop.getProperty("REST_END_MINUTE"));
	}

	public static Calendar getLunchBreakStartTime() {
		Properties prop = TaskProperties.getInstance();
		return getArbitraryTime(prop.getProperty("LUNCH_BREAK_START_HOUR"),
				prop.getProperty("LUNCH_BREAK_START_MINUTE"));
	}

	public static Calendar getLunchBreakEndTime() {
		Properties prop = TaskProperties.getInstance();
		return getArbitraryTime(prop.getProperty("LUNCH_BREAK_END_HOUR"), prop.getProperty("LUNCH_BREAK_END_MINUTE"));
	}
}
