package task.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;

public final class StringUtil {

	public static boolean isTime(String text) {
		Pattern pattern = Pattern.compile("([0-9]|[0-1][0-9]|[2][0-3]):([0-9]|[0-5][0-9])$");
		return pattern.matcher(text).matches();
	}

	public static boolean isNotTime(String text) {
		return !isTime(text);
	}

	public static boolean isNumber(String text) {
		try {
			Integer.parseInt(text);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean isNotNumber(String text) {
		return !isNumber(text);
	}

	public static String getNowTimeText() {
		return getTimeText(Calendar.getInstance());
	}

	public static String getTimeText(Calendar cal) {
		return cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE);
	}

	public static String getDayText(Calendar day) {
		if (day == null) {
			throw new NullPointerException();
		}
		SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dayFormat.format(day.getTime());
	}

	public static boolean isEmpty(String text) {
		return text == null || text.length() == 0;
	}

	public static boolean isNotEmpty(String text) {
		return !isEmpty(text);
	}

	public static String convertHourAndMinute(int minuteValue) {
		if (minuteValue <= 0) {
			return "0��";
		}
		int hour = minuteValue / 60;
		int minute = minuteValue % 60;
		if (hour == 0) {
			return minute + "��";
		} else {
			return hour + "���� " + minute + "��";
		}
	}

}