package task.command;

import java.io.IOException;
import java.util.Calendar;

import task.io.TaskFiles;
import task.util.StringUtil;
import task.util.TaskProperties;

public class Open extends Command {

	private static final String TASK_FILE_NAME = "task";

	private String date;

	public Open() {
		date = StringUtil.getDateText(Calendar.getInstance());
	}

	public Open(String date) {
		if (TASK_FILE_NAME.equals(date)) {
			this.date = date;
		} else {
			this.date = convertLogFileName(date);
		}
	}

	@Override
	public void run() throws Exception {

		if (StringUtil.isEmpty(date)) {
			throw new IllegalArgumentException(
					"�t�@�C�������w�肵�Ă��������B(yyMMdd or yyyyMMdd)");
		}

		if (TASK_FILE_NAME.equals(date)) {
			openFile(TaskProperties.getInstance().getProperty("TASK_FILE_NAME"));
		} else {
			openFile(date + ".log");
		}
	}

	private String convertLogFileName(String date) {
		if (date.length() == 8) {
			return date.substring(0, 4) + "-" + date.substring(4, 6) + "-"
					+ date.substring(6, 8);
		}
		if (date.length() == 6) {
			return "20" + date.substring(0, 2) + "-" + date.substring(2, 4)
					+ "-" + date.substring(4, 6);
		}
		return null;
	}

	private void openFile(String fullFileName) throws IOException {
		Runtime runtime = Runtime.getRuntime();
		System.out.println(fullFileName + "���J���܂��B");
		runtime.exec("notepad " + TaskFiles.getDirectoryPath() + fullFileName);
	}
}
