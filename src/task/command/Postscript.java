package task.command;

import task.ProvisionTask;
import task.Task;

public class Postscript extends Command {

	private String addText;

	public Postscript(String addText) {
		this.addText = addText;
	}

	@Override
	public void run() throws Exception {
		if (Task.isTaskRunnig()) {
			if (ProvisionTask.isNotProvisionTask(Task.getRunningTaskName())) {
				System.out.println("以下のタスクのみ追記できます。");
				for (ProvisionTask provisionTask : ProvisionTask.values()) {
					System.out.print(provisionTask.getTaskName() + " ");
				}
			} else {
				Task.addRunningTaskName(addText);
				System.out.println("タスク名に追記しました。");
				System.out.println(Task.getRunningTaskName() + " running");
			}
		} else {
			System.out.println("実行中のタスクはありません。");
		}
	}

}
