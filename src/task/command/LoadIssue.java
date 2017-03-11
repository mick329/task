package task.command;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import task.io.TaskFiles;
import task.util.StringUtil;
import task.util.TaskProperties;

import com.taskadapter.redmineapi.IssueManager;
import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.RedmineManagerFactory;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.internal.ResultsWrapper;

public class LoadIssue extends Command {

	@Override
	public void run() throws Exception {

		ResultsWrapper<Issue> issueList = getIssueList();

		java.util.List<String> taskList = TaskFiles.getTaskList();

		java.util.List<String> newTodoList = new ArrayList<String>();
		String issueId = null;

		for (String taskText : taskList) {
			issueId = taskText.split(",")[0];
			// 自分で追加したタスクのみ取り出す
			if ("0".equals(issueId)) {
				newTodoList.add(taskText);
			}
		}

		StringBuilder addTaskText = null;

		// redmineから取得したタスクを追加する
		for (Issue issue : issueList.getResults()) {
			addTaskText = new StringBuilder();
			addTaskText.append(issue.getId() + ",");
			addTaskText.append(issue.getProjectName() + "_");
			addTaskText.append(issue.getSubject() + ",");
			addTaskText.append(getDateText(issue.getStartDate()) + ",");
			addTaskText.append(getDateText(issue.getDueDate()));
			newTodoList.add(addTaskText.toString());
		}

		TaskFiles.writeTaskFile(newTodoList);

		System.out.println("RedMineからタスクを取り込みました。");

		showTodoList(newTodoList);
	}

	private ResultsWrapper<Issue> getIssueList() throws RedmineException {
		Properties prop = TaskProperties.getInstance();

		String uri = prop.getProperty("REDMINE_URL_HOST");
		String apiAccessKey = prop.getProperty("API_ACCESS_KEY");

		RedmineManager manager = RedmineManagerFactory.createWithApiKey(uri,
				apiAccessKey);
		IssueManager issueManager = manager.getIssueManager();

		Map<String, String> params = new HashMap<String, String>();
		params.put("assigned_to_id", "me");
		params.put("sort", prop.getProperty("SORT"));

		return issueManager.getIssues(params);
	}

	private String getDateText(Date date) {
		if (date != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			return StringUtil.getDateText(cal);
		} else {
			return "";
		}
	}
}
