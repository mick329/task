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

	private static Calendar getArbitraryTime(String hour, String minute) {
		Calendar arbitraryTime = Calendar.getInstance();
		arbitraryTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
		arbitraryTime.set(Calendar.MINUTE, Integer.parseInt(minute));
		return arbitraryTime;
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
