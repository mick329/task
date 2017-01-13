package task.command;

import java.io.IOException;

import org.junit.Test;

public class AddTest {
	
	private Add add;
	
	@Test
	public void notAddEmptyString() throws IOException{
		add = new Add("");
		add.run();
	}

}
