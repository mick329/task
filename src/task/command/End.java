package task.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import task.ProvisionTask;
import task.Task;
import task.io.TaskFiles;
import task.util.StringUtil;

public class End extends Command {

	private String time;

	public End() {
		this(StringUtil.getNowTimeText());
	}

	public End(String time) {
		this.time = time;
	}

	@Override
	public void run() throws IOException {
		if (Task.isTaskRunnig()) {

			if (writeLogFile(time)) {
				String runningTaskName = Task.getRunningTaskName();
				System.out.println(runningTaskName + " end");

				if (ProvisionTask.isProvisionTask(runningTaskName)) {

					// todoからタスクを削除
					List<String> todoList = TaskFiles.getTaskList();
					List<String> newTodoList = new ArrayList<>();

					for (String task : todoList) {
						if (task.indexOf(runningTaskName) == -1) {
							newTodoList.add(task);
						}
					}

					TaskFiles.writeTaskFile(newTodoList);
				}

				Task.initTaskStatus();
			}
		} else {
			System.out.println("実行中のタスクはありません。");
		}
	}

}
