package task.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import task.DescendingOrderOfWorkingHour;
import task.LogFile;
import task.io.TaskFiles;
import task.util.StringUtil;

public class Ranking extends Command {

	@Override
	public void run() {

		List<LogFile> logFileList = TaskFiles.getLogFileList();
		List<LogFile> aggregateLogFileList = new ArrayList<>();

		for (LogFile logFile : logFileList) {
			if (aggregateLogFileList.contains(logFile)) {
				aggregateLogFileList.get(aggregateLogFileList.indexOf(logFile))
						.aggregate(logFile);
			} else {
				aggregateLogFileList.add(logFile);
			}
		}

		Collections.sort(aggregateLogFileList,
				new DescendingOrderOfWorkingHour());

		// “à—e‚ð•\Ž¦
		for (LogFile logFile : aggregateLogFileList) {
			System.out.println(StringUtil.createShowLogText(logFile));
		}
	}

}
