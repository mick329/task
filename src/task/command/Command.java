package task.command;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import task.Task;
import task.TaskFile;
import task.io.TaskFiles;
import task.util.CalendarUtil;
import task.util.StringUtil;

public abstract class Command {

	public abstract void run() throws Exception;

	protected void showTodoList(List<String> taskList) {

		int index = 1;
		TaskFile taskFile = null;

		for (String taskText : taskList) {
			taskFile = new TaskFile(taskText);
			// 表示はタスク名、期間
			System.out.println(index++ + ":" + taskFile.getTaskText());
		}
	}

	protected boolean existsTaskIndex(List<String> todoList, int taskIndex) {
		return taskIndex > 0 && taskIndex <= todoList.size();
	}

	protected void wrongTaskIndex() {
		System.out.println("正しいタスク番号を指定してください。");
	}

	protected boolean writeLogFile(String time) throws IOException {
		if (isNotTime(time)) {
			return false;
		}

		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

		Calendar arbitraryTime = CalendarUtil.getArbitraryTime(time);

		Calendar taskStartTime = Task.getTaskStartTime();

		long diffTime = (arbitraryTime.getTimeInMillis() - taskStartTime
				.getTimeInMillis()) / (1000 * 60);

		List<String> logList = TaskFiles.getLogLineTextList();
		logList.add(timeFormat.format(taskStartTime.getTime()) + ","
				+ timeFormat.format(arbitraryTime.getTime()) + "," + diffTime
				+ "," + Task.getRunningTaskName());

		TaskFiles.writeLogFile(logList);
		return true;
	}

	private boolean isNotTime(String time) {
		if (StringUtil.isNotTime(time)) {
			System.out.println("正しい時刻を指定してください。");
			return true;
		}
		return false;
	}

}
