package task.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import task.DescendingOrderOfWorkingHour;
import task.io.TaskFiles;
import task.util.StringUtil;

public class Ranking extends Command {

	@Override
	public void run() {
		List<String> logList = TaskFiles.getLogList();
		Map<String, Integer> showTask = new HashMap<>();
		String[] tmp = null;
		String task = null;
		int minute = 0;
		for (String line : logList) {
			tmp = line.split(",");
			task = tmp[3];
			minute = Integer.parseInt(tmp[2]);
			if (showTask.containsKey(task)) {
				showTask.put(task, showTask.get(task) + minute);
			} else {
				showTask.put(task, minute);
			}
		}
		List<Map.Entry<String, Integer>> entries = new ArrayList<Map.Entry<String, Integer>>(showTask.entrySet());
		Collections.sort(entries, new DescendingOrderOfWorkingHour());

		// “à—e‚ð•\Ž¦
		for (Entry<String, Integer> s : entries) {
			System.out.println(s.getKey() + ": " + StringUtil.convertHourAndMinute(s.getValue()));
		}
	}

}
