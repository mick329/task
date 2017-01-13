package task;

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;

public class DescendingOrderOfWorkingHour implements Comparator<Map.Entry<String, Integer>> {

	@Override
	public int compare(Entry<String, Integer> entry1, Entry<String, Integer> entry2) {
		return ((Integer) entry2.getValue()).compareTo((Integer) entry1.getValue());
	}

}
