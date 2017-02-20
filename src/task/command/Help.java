package task.command;

public class Help extends Command {

	@Override
	public void run() {
		System.out.println(" a：タスク追加     l：タスク表示 st：タスク開始   sp：タスク中断 e：タスク完了");
		System.out.println(" i：問い合わせ対応 m：打ち合わせ  h：コマンド一覧  q：終了");
	}

}
