package task.io;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import task.util.StringUtil;
import task.util.TaskProperties;

public class TaskFilesTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	public Calendar now = Calendar.getInstance();

	@Before
	public void deleteTaskFile() throws IOException {
		String taskFilePath = TaskProperties.getInstance().getProperty("DIRECTORY_PATH")
				+ TaskProperties.getInstance().getProperty("TASK_FILE_NAME");
		String logFilePath = TaskProperties.getInstance().getProperty("DIRECTORY_PATH") + StringUtil.getDayText(now)
				+ ".log";
		String doneFilePath = TaskProperties.getInstance().getProperty("DIRECTORY_PATH")
				+ TaskProperties.getInstance().getProperty("DONE_FILE_NAME");
		Files.deleteIfExists(new File(taskFilePath).toPath());
		Files.deleteIfExists(new File(logFilePath).toPath());
		Files.deleteIfExists(new File(doneFilePath).toPath());
	}

	@Test
	public void notExistsTaskFileReturnBlankList() {
		List<String> taskList = TaskFiles.getTaskList();
		assertThat(taskList.size(), is(0));
	}

	@Test
	public void givenBlankListNotWiteTaskFile() throws IOException {
		TaskFiles.writeTaskFile(new ArrayList<String>());
		List<String> taskList = TaskFiles.getTaskList();
		assertThat(taskList.size(), is(0));
	}

	@Test
	public void givenStringListWiteTaskFile() throws IOException {
		List<String> addStringList = new ArrayList<>();
		addStringList.add("test1");
		addStringList.add("test2");
		TaskFiles.writeTaskFile(addStringList);
		List<String> taskList = TaskFiles.getTaskList();
		assertThat(taskList.size(), is(2));
	}

	@Test
	public void givenNullTaskListNotThrowException() throws IOException {
		TaskFiles.writeTaskFile(null);
	}
	
	@Test
	public void givenNullLogListNotThrowException() throws IOException {
		TaskFiles.writeLogFile(null);
	}

	@Test
	public void givenStringListWiteLogFile() throws IOException {
		List<String> addStringList = new ArrayList<>();
		addStringList.add("test1");
		addStringList.add("test2");
		TaskFiles.writeLogFile(addStringList);
		List<String> logList = TaskFiles.getLogList();
		assertThat(logList.size(), is(2));
	}

	@Test
	public void notExistsLogFileReturnBlankList() {
		TaskFiles.getLogList();
		List<String> logList = TaskFiles.getLogList();
		assertThat(logList.size(), is(0));
	}

	@Test
	public void notExistsLogFileOfSpecifyTimeReturnBlankList() {
		TaskFiles.getLogList(now);
		List<String> logList = TaskFiles.getLogList();
		assertThat(logList.size(), is(0));
	}

	@Test
	public void givenNullThrowNullPointerException() {
		thrown.expect(NullPointerException.class);
		TaskFiles.getLogList(null);
	}
	
	@Test
	public void givenNullDoneListNotThrowException() throws IOException {
		TaskFiles.writeDoneFile(null);
	}

	
	@Test
	public void notExistsDoneFileReturnBlankList() {
		List<String> doneList = TaskFiles.getDoneList();
		assertThat(doneList.size(), is(0));
	}
	
	@Test
	public void givenStringListWiteDoneFile() throws IOException {
		List<String> addStringList = new ArrayList<>();
		addStringList.add("test1");
		addStringList.add("test2");
		TaskFiles.writeDoneFile(addStringList);
		List<String> logList = TaskFiles.getDoneList();
		assertThat(logList.size(), is(2));
	}

}
