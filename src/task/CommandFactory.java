package task;

import task.command.Add;
import task.command.Command;
import task.command.Delete;
import task.command.End;
import task.command.Help;
import task.command.Inquiry;
import task.command.List;
import task.command.Meeting;
import task.command.NullCommand;
import task.command.Quit;
import task.command.Ranking;
import task.command.Start;
import task.command.Stop;
import task.command.Total;
import task.util.StringUtil;

public class CommandFactory {

	public static Command createCommand(String line) {

		String[] command = line.split(" ");

		switch (command[0]) {
		// タスク追加
		case "add":
		case "a":
			if (command[1] == null) {
				System.out.println("タスクを入力してください。");
				break;
			}
			return new Add(command[1]);

		// タスク削除
		case "delete":
		case "d":
			if (command.length == 1) {
				System.out.println("削除するタスク番号を入力してください。");
				break;
			}
			if (StringUtil.isNotNumber(command[1])) {
				System.out.println("タスク番号は数値で指定してください。");
				break;
			}
			return new Delete(command[1]);

		// タスク表示
		case "list":
		case "l":
			return new List();

		// タスク開始
		case "start":
		case "st":
			if (command.length == 1) {
				System.out.println("開始するタスク番号を指定してください。");
				break;
			}
			if (StringUtil.isNotNumber(command[1])) {
				System.out.println("タスク番号は数値で指定してください。");
				break;
			}
			return new Start(command[1]);

		// タスク中断
		case "stop":
		case "sp":
			if (command.length == 1) {
				return new Stop();
			} else {
				return new Stop(command[1]);
			}

			// タスク完了
		case "end":
		case "e":
			if (command.length == 1) {
				return new End();
			} else {
				return new End(command[1]);
			}

			// 問い合わせ対応
		case "inquiry":
		case "i":
			return new Inquiry();

		// 打ち合わせ
		case "meeting":
		case "m":
			return new Meeting();

		// 今日のタスク時間の降順ランキング
		case "ranking":
		case "r":
			return new Ranking();

		// ヘルプ
		case "help":
		case "h":
			return new Help();

		// 総タスク時間の降順ランキング
		case "total":
		case "t":
			if (command.length == 1) {
				return new Total();
			} else {
				return new Total(command[1]);
			}

		// 終了
		case "quit":
		case "q":
			return new Quit();

		default:
			break;
		}

		return NullCommand.getInstance();
	}

}
