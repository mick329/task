package task.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import task.LogFile;
import task.ProvisionTask;
import task.Task;
import task.TaskFile;
import task.io.TaskFiles;
import task.util.StringUtil;
import task.util.TaskProperties;

import com.taskadapter.redmineapi.IssueManager;
import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.RedmineManagerFactory;
import com.taskadapter.redmineapi.TimeEntryManager;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.bean.IssueFactory;
import com.taskadapter.redmineapi.bean.TimeEntry;
import com.taskadapter.redmineapi.bean.TimeEntryFactory;

public class End extends Command {

	private String time;

	public End() {
		time = StringUtil.getNowTimeText();
	}

	@Override
	public void run() throws IOException, RedmineException {
		if (Task.isTaskRunnig()) {

			if (writeLogFile(time)) {

				String runningTaskName = Task.getRunningTaskName();

				if (ProvisionTask.isNotProvisionTask(runningTaskName)) {

					// todoからタスクを削除
					List<String> todoList = TaskFiles.getTaskList();
					List<String> newTodoList = new ArrayList<>();
					TaskFile taskFile = null;
					TaskFile endTaskFile = null;

					for (String taskText : todoList) {
						taskFile = new TaskFile(taskText);
						if (!taskFile.getTaskName().equals(runningTaskName)) {
							newTodoList.add(taskText);
						} else {
							endTaskFile = new TaskFile(taskText);
						}
					}

					TaskFiles.writeTaskFile(newTodoList);

					if (endTaskFile.getIssueId() != 0) {

						// TODO 共通化
						Properties prop = TaskProperties.getInstance();
						String uri = prop.getProperty("REDMINE_URL_HOST");
						String apiAccessKey = prop
								.getProperty("API_ACCESS_KEY");

						RedmineManager manager = RedmineManagerFactory
								.createWithApiKey(uri, apiAccessKey);
						IssueManager issueManager = manager.getIssueManager();

						// RedMineを更新
						Issue issue = IssueFactory.create(endTaskFile
								.getIssueId());
						issue.setDoneRatio(100);
						issue.setStatusId(Integer.parseInt(prop
								.getProperty("END_STATUS_ID")));
						issueManager.update(issue);

						TimeEntryManager timeEntryManager = manager
								.getTimeEntryManager();
						TimeEntry timeEntry = TimeEntryFactory.create();
						timeEntry.setIssueId(endTaskFile.getIssueId());
						timeEntry.setActivityId(Integer.parseInt(prop
								.getProperty("TIME_ENTRY_ACTIVITY_ID")));
						timeEntry
								.setHours(calculatesWorkingTime(runningTaskName));
						timeEntryManager.createTimeEntry(timeEntry);
					}

				}

				System.out.println(runningTaskName + " end");

				Task.initTaskStatus();
			}

		} else {
			System.out.println("実行中のタスクはありません。");
		}
	}

	private Float calculatesWorkingTime(String taskName) {

		List<LogFile> logFileList = null;
		Calendar day = null;
		LogFile logFile = null;

		// 指定日数分を集計する
		for (int i = 0; i < 31; i++) {

			day = Calendar.getInstance();
			day.add(Calendar.DAY_OF_MONTH, -1 * i);

			logFileList = TaskFiles.getLogFileList(day);

			for (LogFile tmpLogFile : logFileList) {

				if (tmpLogFile.getTaskName().equals(taskName)) {
					if (logFile == null) {
						logFile = tmpLogFile;
					} else {
						logFile.aggregate(tmpLogFile);
					}
				}
			}
		}

		return (logFile.getWorkingHoursInMinute() / 60F);
	}
}
