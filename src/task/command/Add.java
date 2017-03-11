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

		// 現在のタスクリストを取得
		List<String> todoList = TaskFiles.getTaskList();

		// 入力された値を,で区切る
		String[] taskArray = taskText.split(",");

		for (String task : taskArray) {
			if (StringUtil.isNotEmpty(task)) {
				// issueIdは0で現在のリストに追記
				todoList.add("0," + task + ",,");
				System.out.println("タスクを追加しました。(" + todoList.size() + ":"
						+ task + ")");
			}
		}

		TaskFiles.writeTaskFile(todoList);

		showTodoList(todoList);
	}

}
