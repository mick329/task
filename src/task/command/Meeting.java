package task.command;

import java.io.IOException;
import java.util.Calendar;

import task.ProvisionTask;
import task.Task;

public class Meeting extends Command {

	@Override
	public void run() throws IOException {
		if (Task.isTaskRunnig()) {
			new Stop().run();
		}
		Task.startTask(Calendar.getInstance(), ProvisionTask.MEETING.getTaskName());
	}

}
