package task.command;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import task.DescendingOrderOfWorkingHour;
import task.io.TaskFiles;
import task.util.StringUtil;

public class Total extends Command {

	private String days;

	public Total() {
		// �f�t�H���g��1�T�ԂƂ���
		days = "7";
	}

	public Total(String days) {
		this.days = days;
	}

	@Override
	public void run() {

		if (StringUtil.isNotPositiveInteger(days)) {
			System.out.println("�W�v�����������𐳂������͂��Ă��������B");
			return;
		}

		Map<String, Integer> showTask = new HashMap<>();
		String[] tmp = null;
		String task = null;
		int minute = 0;
		List<String> logList = null;
		Calendar day = null;

		// �w����������W�v����
		for (int i = 0; i < Integer.parseInt(days); i++) {

			day = Calendar.getInstance();
			day.add(Calendar.DAY_OF_MONTH, -1 * i);
			day.getTime();

			logList = TaskFiles.getLogList(day);

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
		}

		List<Map.Entry<String, Integer>> entries = new ArrayList<Map.Entry<String, Integer>>(
				showTask.entrySet());
		Collections.sort(entries, new DescendingOrderOfWorkingHour());

		// ���e��\��
		for (Entry<String, Integer> s : entries) {
			System.out.println(s.getKey() + ":" + s.getValue());
		}
	}

}
