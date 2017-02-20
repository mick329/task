package task.command;

public class NullCommand extends Command {

	private NullCommand() {

	}

	private static class NullTaskHolder {
		private static final NullCommand instance = new NullCommand();
	}

	public static NullCommand getInstance() {
		return NullTaskHolder.instance;
	}

	@Override
	public void run() {
		// 何もしない
	}

}
