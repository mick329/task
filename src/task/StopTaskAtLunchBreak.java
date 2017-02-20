package task;

import java.io.IOException;
import java.util.Calendar;

import task.command.Stop;
import task.util.CalendarUtil;
import task.util.StringUtil;

public class StopTaskAtLunchBreak implements Runnable {

	@Override
	public void run() {
		if (Task.isTaskRunnig()) {
			Calendar now = Calendar.getInstance();
			Calendar lunchBreakStartTime = CalendarUtil.getLunchBreakStartTime();
			Calendar lunchBreakEndTime = CalendarUtil.getLunchBreakEndTime();
			// 昼食時間は自動的にタスクを止める
			if (now.after(lunchBreakStartTime) && now.before(lunchBreakEndTime)) {
				try {
					new Stop(lunchBreakStartTime()).run();
				} catch (IOException e) {
					// 何もしない
				}
			}

		}
	}

	private static String lunchBreakStartTime() {
		return StringUtil.getTimeText(CalendarUtil.getLunchBreakStartTime());
	}

}
