package task.command;

import java.util.Calendar;

import task.io.TaskFiles;
import task.util.StringUtil;

public class Open extends Command {

	private String date;

	public Open() {
		date = StringUtil.getDateText(Calendar.getInstance());
	}

	public Open(String date) {
		this.date = convertLogFileName(date);
	}

	@Override
	public void run() throws Exception {

		if (StringUtil.isEmpty(date)) {
			throw new IllegalArgumentException(
					"ファイル名を指定してください。(yyMMdd or yyyyMMdd)");
		}

		Runtime runtime = Runtime.getRuntime();
		String fileName = date + ".log";
		System.out.println(fileName + "を開きます。");
		runtime.exec("notepad " + TaskFiles.getDirectoryPath() + fileName);
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
}
