package task.io;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import task.util.StringUtil;
import task.util.TaskProperties;

public class TaskFiles {

	public static List<String> getTaskList() {
		return readAllLines(getTaskFilePath());
	}

	private static List<String> readAllLines(Path path) {
		if (path.toFile().exists()) {
			List<String> list = null;
			try {
				list = Files.readAllLines(path, StandardCharsets.UTF_8);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return list;
		} else {
			return new ArrayList<String>();
		}
	}

	private static Path getTaskFilePath() {
		String taskFilePath = getDirectoryPath() + TaskProperties.getInstance().getProperty("TASK_FILE_NAME");
		return new File(taskFilePath).toPath();
	}

	public static String getDirectoryPath() {
		return TaskProperties.getInstance().getProperty("DIRECTORY_PATH");
	}

	public static void writeTaskFile(List<String> taskList) throws IOException {
		writeFile(getTaskFilePath(), taskList);
	}

	private static void writeFile(Path path, List<String> writeList) throws IOException {
		if (writeList == null) {
			// 何もしない
			return;
		}
		try {
			Files.write(path, writeList, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.WRITE,
					StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			System.err.println("タスクの書き込みに失敗しました。");
			throw e;
		}
	}

	public static void writeLogFile(List<String> logList) throws IOException {
		writeFile(getLogFilePath(), logList);
	}

	public static List<String> getLogList() {
		return getLogList(Calendar.getInstance());
	}

	public static List<String> getLogList(Calendar day) {
		Path path = getLogFilePath(day);

		if (path.toFile().exists()) {
			List<String> list = null;
			try {
				list = Files.readAllLines(path, StandardCharsets.UTF_8);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return list;
		} else {
			return new ArrayList<String>();
		}
	}

	private static Path getLogFilePath() {
		return new File(getDirectoryPath() + StringUtil.getDayText(Calendar.getInstance()) + ".log").toPath();
	}

	private static Path getLogFilePath(Calendar day) {
		return new File(getDirectoryPath() + StringUtil.getDayText(day) + ".log").toPath();
	}

}
