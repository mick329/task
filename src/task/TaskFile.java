package task;

import org.seasar.util.lang.StringUtil;

public class TaskFile {

	/** issueId */
	private Integer issueId;
	/** タスク名(プロジェクト名_タスク名) */
	private String taskName;
	/** 開始日時 */
	private String startDate;
	/** 終了日時 */
	private String dueDate;

	public TaskFile(String taskText) {
		String[] text = taskText.split(",");
		issueId = Integer.parseInt(text[0]);
		taskName = text[1];
		if (text.length > 2) {
			startDate = text[2];
		} else {
			startDate = "";
		}
		if (text.length > 3) {
			dueDate = text[3];
		} else {
			dueDate = "";
		}

	}

	public String getTaskText() {
		StringBuilder taskText = new StringBuilder();
		if (StringUtil.isNotEmpty(startDate)) {
			taskText.append(startDate + " ");
		}
		if (StringUtil.isNotEmpty(dueDate)) {
			taskText.append(dueDate + " ");
		}
		taskText.append(taskName + "(" + issueId + ")");
		return taskText.toString();
	}

	public Integer getIssueId() {
		return issueId;
	}

	public void setIssueId(Integer issueId) {
		this.issueId = issueId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

}
