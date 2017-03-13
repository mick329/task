package task;

public enum ProvisionTask {

	MORNING_ASSEMBLY("朝礼"), INQUIRY("問い合わせ"), MEETING("打ち合わせ");

	private String taskName;

	private ProvisionTask(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskName() {
		return taskName;
	}

	private static ProvisionTask getProvisionTask(String taskName) {
		for (ProvisionTask provisionTask : ProvisionTask.values()) {
			if (taskName.startsWith(provisionTask.getTaskName())) {
				return provisionTask;
			}
		}
		return null;
	}

	public static boolean isNotProvisionTask(String taskName) {
		return getProvisionTask(taskName) == null;
	}

}
