package task.command;

import task.Task;

public class Now extends Command {

	@Override
	public void run() throws Exception {
		if (Task.isTaskRunnig()) {
			System.out.println(Task.getRunningTaskName() + " running");
		} else {
			System.out.println("���s���̃^�X�N�͂���܂���B");
		}
	}

}
