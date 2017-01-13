package task.command;

import task.io.TaskFiles;

public class List extends Command {

	@Override
	public void run() {
		java.util.List<String> todoList = TaskFiles.getTaskList();
		if (todoList.size() == 0) {
			System.out.println("�^�X�N���o�^����Ă��܂���B");
			return;
		}
		showTodoList(todoList);
	}

}
