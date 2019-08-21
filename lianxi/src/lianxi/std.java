package lianxi;
import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
public class std extends JPanel{
	String cs=" ";
	String input="";
	String beifen="";
	public JButton[] jbs = new JButton[20];
	JButton save = new JButton("保存");
	JButton copy = new JButton("复制");
	JButton clear = new JButton("清除");
	JTextField tf = new JTextField();
	JTextArea text = new JTextArea();
	JPanel panel1 = new JPanel();
	StringBuffer strBuf = new StringBuffer();
	JScrollPane js = new JScrollPane(text);// 滚动条
	ArrayList<String> strArray = new ArrayList<String>();
  public std() {
    setGUIComponent();
  }
  public void setGUIComponent(){
    //初始化组件
	    setLayout(null);
		tf.setHorizontalAlignment(JTextField.RIGHT);// 右对齐
		// tf.setEditable(false);//文本框禁止编辑
		tf.setBounds(0, 10, 350, 30);
		panel1.setLayout(new GridLayout(4, 5, 0, 0));
		panel1.setBounds(0, 50, 350, 120);
		// text.setLineWrap(true);//自动换行
		js.setBounds(360, 5, 200, 130);		
		save.setBounds(360, 140, 60, 30);
		copy.setBounds(430, 140, 60, 30);
		clear.setBounds(500,140, 60, 30);		
		
		strArray.add("1");	strArray.add("2");
		strArray.add("3");	strArray.add("+");
		strArray.add("c");	strArray.add("4");
		strArray.add("5");	strArray.add("6");
		strArray.add("-");	strArray.add("退格");	
		strArray.add("7");	strArray.add("8");
		strArray.add("9");	strArray.add("*");
		strArray.add("√");strArray.add("0");
		strArray.add(".");strArray.add("%");
		strArray.add("/");	strArray.add("=");
		for (int i = 0; i < jbs.length; i++) {
			jbs[i] = new JButton(strArray.get(i) + "");
			jbs[i].setSize(70, 30);
			panel1.add(jbs[i]);
			jbs[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					String s = arg0.getActionCommand();
					//System.out.println("123");
					if (s.equals("=") == false) {// 不为等于时全显示
						cs += s;
						
					}
					if (s.equals("c")) {
						cs = " ";
						input = "";
						beifen = "";
					}
					if (s.equals("退格")) {
						int m = input.length();
						int n = beifen.length();
						int flag = 0;
						for (int i = 0; i < cs.length(); i++) {
							if (tf.getText().equals("") && cs.charAt(i) == '退') {
								flag = 1;
								cs = " ";
								return;
							}
						}
						if (flag == 0) {
							if (m == 1) {
								input = input.substring(0, 0);// 防止输入一个数字就退格
								tf.setText("");
								return;
							}
							if (input.charAt(m - 2) == '+' || input.charAt(m - 2) == '-' || input.charAt(m - 2) == '*'
									|| input.charAt(m - 2) == '/' || input.charAt(m - 2) == '%' || input.charAt(m - 2) == '√') {
								input = input.substring(0, input.length() - 3);
							} else
								input = input.substring(0, input.length() - 1);
							cs = cs.substring(0, cs.length() - 3);
						}
					}
					tf.setText(cs);
					if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("%")|| s.equals("√")) {
						input += " " + s + " ";
					} else if (s.equals("=")) {
						String input1 = "";
						String show = "";
						for (int i = 0; i < input.length(); i++) {
							if (input.charAt(i) != 'c') {
								input1 += input.charAt(i);
							}
						}
						show = input1 + "=" + " " +stdC.compute(input);
						strBuf.append(show + "\n");
						text.setText(strBuf.toString());
					} else if (s.equals("退格") == false) {
						/*这里如果改成if (s.equals("退格") == false||s.equals("c") == false)
						 的话，那么当输入"c"时逻辑判断第一个成立，逻辑短路，条件成立。那么就将"c"假如到了input
						因此，在compute计算函数中才将"c"取出*/
						input += s;
					}
				}
			});
			
		}
		add(tf);		add(js);
		add(panel1);
		add(save);		add(copy);
		add(clear);
		
		save.addActionListener(new ActionListener() { // 按钮动作事件处理
			public void actionPerformed(ActionEvent e) {
				BufferedWriter out = null;
				int flag=0;
				String Filename="d://writeFile.txt";//更改文件路径
				File file = new File(Filename);
				
				try {
					out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
					out.append(text.getText());
				} catch (Exception e0) {
					e0.printStackTrace();
				} finally {
					try {
						out.flush();
						out.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "已保存在文件"+Filename+"中");
				}
			}

		});
		copy.addActionListener(new ActionListener() { // 按钮动作事件处理
			public void actionPerformed(ActionEvent e) {
				text.copy();
			}
		});
		clear.addActionListener(new ActionListener() { // 按钮动作事件处理
			public void actionPerformed(ActionEvent e) {
				text.setText("");
				strBuf.setLength(0); // 清空strBuf中的数据
			}
		});
		setVisible(true);    setSize(600, 300);
  }
  
}

