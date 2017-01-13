package task.command;

import java.io.IOException;
import java.util.List;

import task.io.TaskFiles;

public class Delete extends Command {

	private String taskIndex;

	public Delete(String taskIndex) {
		this.taskIndex = taskIndex;
	}

	@Override
	public void run() throws IOException {
		List<String> todoList = TaskFiles.getTaskList();
		int targetTaskIndex = Integer.parseInt(taskIndex);
		if (existsTaskIndex(todoList, targetTaskIndex)) {
			String task = todoList.get(targetTaskIndex - 1);
			todoList.remove(targetTaskIndex - 1);
			System.out.println("タスクを削除しました。(" + task + ")");
			TaskFiles.writeTaskFile(todoList);
		} else {
			wrongTaskIndex();
		}
		showTodoList(todoList);
	}

}
