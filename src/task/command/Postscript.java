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
				System.out.println("�ȉ��̃^�X�N�̂ݒǋL�ł��܂��B");
				for (ProvisionTask provisionTask : ProvisionTask.values()) {
					System.out.print(provisionTask.getTaskName() + " ");
				}
			} else {
				Task.addRunningTaskName(addText);
				System.out.println("�^�X�N���ɒǋL���܂����B");
				System.out.println(Task.getRunningTaskName() + " running");
			}
		} else {
			System.out.println("���s���̃^�X�N�͂���܂���B");
		}
	}

}
