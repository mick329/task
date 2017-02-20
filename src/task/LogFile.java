package task;

import java.util.Calendar;

import task.util.CalendarUtil;

public class LogFile {

	/** タスク名 */
	private String taskName;
	/** 作業時間(分) */
	private Integer workingHoursInMinute;
	/** 開始時刻 */
	private Calendar startTime;
	/** 終了時刻 */
	private Calendar endTime;

	public LogFile() {

	}

	public LogFile(Calendar day, String line) {
		String[] text = line.split(",");
		startTime = CalendarUtil.getArbitraryTime(day, text[0]);
		endTime = CalendarUtil.getArbitraryTime(day, text[1]);
		workingHoursInMinute = Integer.parseInt(text[2]);
		taskName = text[3];
	}

	public void aggregate(LogFile logFile) {
		workingHoursInMinute += logFile.workingHoursInMinute;
		if (startTime.compareTo(logFile.startTime) > 0) {
			startTime = logFile.startTime;
		}
		if (endTime.compareTo(logFile.endTime) < 0) {
			endTime = logFile.endTime;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((taskName == null) ? 0 : taskName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LogFile other = (LogFile) obj;
		if (taskName == null) {
			if (other.taskName != null)
				return false;
		} else if (!taskName.equals(other.taskName))
			return false;
		return true;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Integer getWorkingHoursInMinute() {
		return workingHoursInMinute;
	}

	public void setWorkingHoursInMinute(Integer workingHoursInMinute) {
		this.workingHoursInMinute = workingHoursInMinute;
	}

	public Calendar getStartTime() {
		return startTime;
	}

	public void setStartTime(Calendar startTime) {
		this.startTime = startTime;
	}

	public Calendar getEndTime() {
		return endTime;
	}

	public void setEndTime(Calendar endTime) {
		this.endTime = endTime;
	}

}
