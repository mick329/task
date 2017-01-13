package task.command;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import task.Task;
import task.io.TaskFiles;

public class Start extends Command {
	
	private String taskIndex;
	
	public Start(String taskIndex){
		this.taskIndex = taskIndex;
	}

	@Override
	public void run() throws IOException {
		Calendar now = Calendar.getInstance();
		List<String> todoList = TaskFiles.getTaskList();
		int targetIndex = Integer.parseInt(taskIndex);

		if (existsTaskIndex(todoList, targetIndex)) {
			if (Task.isTaskRunnig()) {
				new Stop().run();
			}
			Task.startTask(now, todoList.get(targetIndex - 1));
		} else {
			wrongTaskIndex();
		}
	}

}
