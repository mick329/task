package task.command;

import java.io.IOException;

import task.Task;
import task.util.StringUtil;

public class Stop extends Command {

	private String time;

	public Stop() {
		this(StringUtil.getNowTimeText());
	}

	public Stop(String time) {
		this.time = time;
	}

	@Override
	public void run() throws IOException {
		if (Task.isTaskRunnig()) {
			if (writeLogFile(time)) {
				System.out.println(Task.getRunningTaskName() + " stop");
				Task.initTaskStatus();
			}
		} else {
			System.out.println("実行中のタスクはありません。");
		}
	}

}
