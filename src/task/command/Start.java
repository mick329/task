package task.command;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import task.Task;
import task.TaskFile;
import task.io.TaskFiles;

public class Start extends Command {

	private String taskIndex;

	public Start(String taskIndex) {
		this.taskIndex = taskIndex;
	}

	@Override
	public void run() throws IOException {
		Calendar now = Calendar.getInstance();
		List<String> taskList = TaskFiles.getTaskList();
		int targetIndex = Integer.parseInt(taskIndex);

		if (existsTaskIndex(taskList, targetIndex)) {
			if (Task.isTaskRunnig()) {
				new Stop().run();
			}
			TaskFile taskFile = new TaskFile(taskList.get(targetIndex - 1));
			Task.startTask(now, taskFile.getTaskName());
		} else {
			wrongTaskIndex();
		}
	}

}
