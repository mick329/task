package task.command;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import task.DescendingOrderOfWorkingHour;
import task.LogFile;
import task.io.TaskFiles;
import task.util.StringUtil;

public class Total extends Command {

	private String days;

	public Total() {
		// デフォルトは1週間とする
		days = "7";
	}

	public Total(String days) {
		this.days = days;
	}

	@Override
	public void run() {

		if (StringUtil.isNotPositiveInteger(days)) {
			System.out.println("集計したい日数を正しく入力してください。");
			return;
		}

		List<LogFile> aggregateLogFileList = new ArrayList<>();
		List<LogFile> logFileList = null;
		Calendar day = null;

		// 指定日数分を集計する
		for (int i = 0; i < Integer.parseInt(days); i++) {

			day = Calendar.getInstance();
			day.add(Calendar.DAY_OF_MONTH, -1 * i);

			logFileList = TaskFiles.getLogFileList(day);

			for (LogFile logFile : logFileList) {
				if (aggregateLogFileList.contains(logFile)) {
					aggregateLogFileList.get(
							aggregateLogFileList.indexOf(logFile)).aggregate(
							logFile);
				} else {
					aggregateLogFileList.add(logFile);
				}
			}
		}

		// 作業時間でソート
		Collections.sort(aggregateLogFileList,
				new DescendingOrderOfWorkingHour());

		for (LogFile logFile : aggregateLogFileList) {
			System.out.println(StringUtil.createShowLogText(logFile));
		}
	}
}
