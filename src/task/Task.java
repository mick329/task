package task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import task.command.Command;
import task.io.TaskFiles;
import task.util.CalendarUtil;

public class Task {

	private static boolean doTaskControl = true;
	private static Calendar taskStartTime;
	private static String runningTaskName;
	private static boolean isTaskRunnig;

	public static void main(String[] args) {

		Command command = null;

		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		service.scheduleAtFixedRate(new StopTaskAtLunchBreak(), 0, 15, TimeUnit.MINUTES);

		if (isBeforOfficeStartTime()) {
			startTask(CalendarUtil.getOfficeStartTime(), ProvisionTask.MORNING_ASSEMBLY.getTaskName());
		}

		try (Scanner scanner = new Scanner(System.in)) {

			Files.createDirectories(Paths.get(TaskFiles.getDirectoryPath()));

			String line = null;

			while (doTaskControl) {
				line = scanner.nextLine();
				command = CommandFactory.createCommand(line);
				try {
					command.run();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			service.shutdown();
		}

	}

	private static boolean isBeforOfficeStartTime() {
		return Calendar.getInstance().before(CalendarUtil.getOfficeStartTime());
	}

	public static void startTask(Calendar taskStartTime, String taskName) {
		Task.taskStartTime = taskStartTime;
		Task.runningTaskName = taskName;
		isTaskRunnig = true;
		System.out.println(taskName + " running");
	}

	public static void initTaskStatus() {
		taskStartTime = null;
		runningTaskName = "";
		isTaskRunnig = false;
	}

	public static void stopTaskControl() {
		doTaskControl = false;
	}

	public static boolean isTaskRunnig() {
		return isTaskRunnig;
	}

	public static String getRunningTaskName() {
		return runningTaskName;
	}

	public static Calendar getTaskStartTime() {
		return taskStartTime;
	}

}