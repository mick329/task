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
		// �f�t�H���g��1�T�ԂƂ���
		days = "7";
	}

	public Total(String days) {
		this.days = days;
	}

	@Override
	public void run() {

		if (StringUtil.isNotPositiveInteger(days)) {
			System.out.println("�W�v�����������𐳂������͂��Ă��������B");
			return;
		}

		List<LogFile> aggregateLogFileList = new ArrayList<>();
		List<LogFile> logFileList = null;
		Calendar day = null;

		// �w����������W�v����
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

		// ��Ǝ��ԂŃ\�[�g
		Collections.sort(aggregateLogFileList,
				new DescendingOrderOfWorkingHour());

		for (LogFile logFile : aggregateLogFileList) {
			System.out.println(StringUtil.createShowLogText(logFile));
		}
	}
}
