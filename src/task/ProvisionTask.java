package task;

public enum ProvisionTask {

	MORNING_ASSEMBLY("����"), INQUIRY("�₢���킹"), MEETING("�ł����킹");

	private String taskName;

	private ProvisionTask(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskName() {
		return taskName;
	}

	private static ProvisionTask getProvisionTask(String taskName) {
		for (ProvisionTask provisionTask : ProvisionTask.values()) {
			if (provisionTask.getTaskName().equals(taskName)) {
				return provisionTask;
			}
		}
		return null;
	}

	public static boolean isNotProvisionTask(String taskName) {
		return getProvisionTask(taskName) == null;
	}

}
