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
		// �^�X�N�ǉ�
		case "add":
		case "a":
			if (command[1] == null) {
				System.out.println("�^�X�N����͂��Ă��������B");
				break;
			}
			return new Add(command[1]);

		// �^�X�N�폜
		case "delete":
		case "d":
			if (command.length == 1) {
				System.out.println("�폜����^�X�N�ԍ�����͂��Ă��������B");
				break;
			}
			if (StringUtil.isNotNumber(command[1])) {
				System.out.println("�^�X�N�ԍ��͐��l�Ŏw�肵�Ă��������B");
				break;
			}
			return new Delete(command[1]);

		// �^�X�N�\��
		case "list":
		case "l":
			return new List();

		// �^�X�N�J�n
		case "start":
		case "st":
			if (command.length == 1) {
				System.out.println("�J�n����^�X�N�ԍ����w�肵�Ă��������B");
				break;
			}
			if (StringUtil.isNotNumber(command[1])) {
				System.out.println("�^�X�N�ԍ��͐��l�Ŏw�肵�Ă��������B");
				break;
			}
			return new Start(command[1]);

		// �^�X�N���f
		case "stop":
		case "sp":
			if (command.length == 1) {
				return new Stop();
			} else {
				return new Stop(command[1]);
			}

			// �^�X�N����
		case "end":
		case "e":
			if (command.length == 1) {
				return new End();
			} else {
				return new End(command[1]);
			}

			// �₢���킹�Ή�
		case "inquiry":
		case "i":
			return new Inquiry();

		// �ł����킹
		case "meeting":
		case "m":
			return new Meeting();

		// �����̃^�X�N���Ԃ̍~�������L���O
		case "ranking":
		case "r":
			return new Ranking();

		// �w���v
		case "help":
		case "h":
			return new Help();

		// ���^�X�N���Ԃ̍~�������L���O
		case "total":
		case "t":
			if (command.length == 1) {
				return new Total();
			} else {
				return new Total(command[1]);
			}

		// �I��
		case "quit":
		case "q":
			return new Quit();

		default:
			break;
		}

		return NullCommand.getInstance();
	}

}
