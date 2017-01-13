package task.command;

import java.io.IOException;
import java.util.Calendar;

import task.Task;
import task.util.CalendarUtil;

public class Quit extends Command {

	@Override
	public void run() throws IOException {
		if (Task.isTaskRunnig()) {
			Calendar now = Calendar.getInstance();
			Calendar officeEndTime = CalendarUtil.getOfficeEndTime();
			Calendar restEndTime = CalendarUtil.getRestEndTime();
			if (officeEndTime.before(now) && now.after(restEndTime)) {
				new Stop(officeEndTime.get(Calendar.HOUR_OF_DAY) + ":" + officeEndTime.get(Calendar.MINUTE)).run();
			} else {
				new Stop().run();
			}
		}
		Task.stopTaskControl();
	}

}
