package task.util;

import java.io.InputStream;
import java.util.Properties;

public final class TaskProperties {

	private TaskProperties() {

	}

	private static class TaskPropertiesHolder {
		private static final Properties instance = getTaskProperties();

		private static Properties getTaskProperties() {
			Properties properties = new Properties();
			try (InputStream is = TaskProperties.class.getClassLoader().getResourceAsStream("task.properties")) {
				properties.load(is);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return properties;
		}
	}

	public static Properties getInstance() {
		return TaskPropertiesHolder.instance;
	}

}
