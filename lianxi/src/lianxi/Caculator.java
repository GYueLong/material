package lianxi;
import java.awt.*;
import javax.swing.*;

public class Caculator extends JFrame {
	JTabbedPane dbTabPane;	//选项卡面板
    JPanel inputPanel;		// 录入面板;
	JPanel viewPanel;		// 浏览面板;
	std stdPanel;
	Programmer ProgrammerPanel;

	public Caculator() {
		super("计算器");
		setGUIComponent();
	}

	public void setGUIComponent() {
		Container c = getContentPane();
		dbTabPane = new JTabbedPane();
		// 定义录入面板
		inputPanel = new JPanel();
		inputPanel.setLayout(null);
		stdPanel = new std(); // 录入界面面板
		inputPanel.add(stdPanel);		
		dbTabPane.add(inputPanel,"标准" );

		// 定义浏览面板
		viewPanel = new JPanel();
		viewPanel.setLayout(null);
		ProgrammerPanel=new Programmer();
		viewPanel.add(ProgrammerPanel);
		dbTabPane.add(viewPanel,"程序员");

		c.add(dbTabPane);
}
	
	public static void main(String[] args) {
		Caculator sm = new Caculator();
		stdC stdc=new stdC();
		Programmer programmer=new Programmer();
		sm.setSize(600, 230);
		sm.setVisible(true);
		sm.setResizable(false);//窗口大小不可改变
		sm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
