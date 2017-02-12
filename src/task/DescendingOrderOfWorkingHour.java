package task;

import java.util.Comparator;

public class DescendingOrderOfWorkingHour implements Comparator<LogFile> {

	@Override
	public int compare(LogFile entry1, LogFile entry2) {
		return (entry2.getWorkingHoursInMinute()).compareTo(entry1
				.getWorkingHoursInMinute());
	}

}
