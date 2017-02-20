package task.command;

import java.io.IOException;
import java.util.List;

import task.io.TaskFiles;
import task.util.StringUtil;

public class Add extends Command {

	private String taskText;

	public Add(String taskText) {
		this.taskText = taskText;
	}

	@Override
	public void run() throws IOException {
		List<String> todoList = TaskFiles.getTaskList();

		String[] taskArray = taskText.split(",");

		for (String task : taskArray) {
			if (StringUtil.isNotEmpty(task)) {
				todoList.add(task);
				System.out.println("タスクを追加しました。(" + todoList.size() + ":" + task + ")");
			}
		}

		TaskFiles.writeTaskFile(todoList);

		showTodoList(todoList);
	}

}
