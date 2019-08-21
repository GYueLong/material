package lianxi;

import javax.swing.JButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Integer;
import javax.swing.*;

public class Programmer extends JPanel{
    JTextField er_shi1=new JTextField();
	JTextField er_shi2=new JTextField();
	JTextField shi_er1=new JTextField();
	JTextField shi_er2=new JTextField();
	JTextField ba_shi1=new JTextField();
	JTextField ba_shi2=new JTextField();
	JTextField shi_ba1=new JTextField();
	JTextField shi_ba2=new JTextField();
	JTextField shiliu_shi1=new JTextField();
	JTextField shiliu_shi2=new JTextField();
	JTextField shi_shiliu1=new JTextField();
	JTextField shi_shiliu2=new JTextField();
	
	JLabel ershi=new JLabel("二进制—十进制");
	JLabel shier=new JLabel("十进制—二进制");
	JLabel bashi=new JLabel("八进制—十进制");
	JLabel shiba=new JLabel("十进制—八进制");
	JLabel shiliushi=new JLabel("十六进制—十进制");
	JLabel shishiliu=new JLabel("十进制—十六进制");
	
	JButton jbt1=new JButton("转化");
	JButton jbt2=new JButton("转化");
	JButton jbt3=new JButton("转化");
	JButton jbt4=new JButton("转化");
	JButton jbt5=new JButton("转化");
	JButton jbt6=new JButton("转化");
	
    JPanel Pjpanel=new JPanel();
    JPanel panel=new JPanel();
    ProgrammerC pro=new ProgrammerC();	
	
    String str1="",str2="",str3="",str4="",str5="",str6="",str7="",str8="",str9="",str10="";
   
	public Programmer(){
	
	panel.setLayout(new GridLayout(6,4));

	panel.add(er_shi1);panel.add(ershi);panel.add(er_shi2);panel.add(jbt1);
	panel.add(shi_er1);panel.add(shier);panel.add(shi_er2);panel.add(jbt2);
	panel.add(ba_shi1);panel.add(bashi);panel.add(ba_shi2);panel.add(jbt3);
	panel.add(shi_ba1);panel.add(shiba);panel.add(shi_ba2);panel.add(jbt4);
	panel.add(shiliu_shi1);panel.add(shiliushi);panel.add(shiliu_shi2);panel.add(jbt5);
	panel.add(shi_shiliu1);panel.add(shishiliu);panel.add(shi_shiliu2);panel.add(jbt6);
	Pjpanel.setLayout(new BorderLayout());
	Pjpanel.add(panel,BorderLayout.CENTER);
	add(Pjpanel);
	
	
	jbt1.addActionListener(new ActionListener() { // 按钮动作事件处理
		public void actionPerformed(ActionEvent e) {	//换成整数		
			int flag=0;			
			str1=er_shi1.getText();
			int [] zhengxing = new int[str1.length()];
			char[] ans1 = str1.toCharArray();	//将字符串转化为字符数组		
			for(int i=0;i<ans1.length;i++) {
				if(ans1[i]!='0'&&ans1[i]!='1') flag=1;							
			}
			if(flag==1) {JOptionPane.showMessageDialog(null, "请输入0或1！");
			return;
			}
			for(int i=0; i<str1.length(); i++){//
				zhengxing[i] = Integer.parseInt(str1.charAt(i)+"");
				}
			
			str2=String.valueOf(pro.transform1(zhengxing,2));
			er_shi2.setText(str2);
		}
		
	});
	
	jbt2.addActionListener(new ActionListener() { // 按钮动作事件处理
		public void actionPerformed(ActionEvent e) {
				str3=shi_er1.getText();
				int flag=0;
				int [] zhengxing = new int[str3.length()];
				char[] ans1 = str3.toCharArray();	//将字符串转化为字符数组		
				for(int i=0;i<ans1.length;i++) {
					if(ans1[i]<'0') flag=1;							
				}
				if(flag==1) {JOptionPane.showMessageDialog(null, "请输入整数");
				return;
				}
				shi_er2.setText(pro.transform2(str3,2));
			}
	});
	
	jbt3.addActionListener(new ActionListener() { // 按钮动作事件处理
		public void actionPerformed(ActionEvent e) {
			int flag=0;			
			str4=ba_shi1.getText();
			int [] zhengxing = new int[str4.length()];
			char[] ans1 = str4.toCharArray();	//将字符串转化为字符数组		
			for(int i=0;i<ans1.length;i++) {
				if(ans1[i]<'0'||ans1[i]>'7') flag=1;

			}
			if(flag==1) {
				JOptionPane.showMessageDialog(null, "请输入0-7之间的数！");
				return;
			}
			for(int i=0; i<str4.length(); i++){//
				zhengxing[i] = Integer.parseInt(str4.charAt(i)+"");
				}			

			str5=String.valueOf(pro.transform1(zhengxing,8));
			ba_shi2.setText(str5);
			}
	});
	
	jbt4.addActionListener(new ActionListener() { // 按钮动作事件处理
		public void actionPerformed(ActionEvent e) {
			str6=shi_ba1.getText();
			int flag=0;
			int [] zhengxing = new int[str6.length()];
			char[] ans1 = str6.toCharArray();	//将字符串转化为字符数组		
			for(int i=0;i<ans1.length;i++) {
				if(ans1[i]<'0') flag=1;							
			}
			if(flag==1) {JOptionPane.showMessageDialog(null, "请输入整数");
			return;
			}
			shi_ba2.setText(pro.transform2(str6,8));
			}
	});
	
	jbt5.addActionListener(new ActionListener() { // 按钮动作事件处理
		public void actionPerformed(ActionEvent e) {
			int flag=0;			
			str7=shiliu_shi1.getText();
			int [] zhengxing = new int[str7.length()];
			char[] ans1 = str7.toCharArray();	//将字符串转化为字符数组		
			for(int i=0;i<ans1.length;i++) {
				if((ans1[i]<'0'||ans1[i]>'9')&&(ans1[i]<'A'||ans1[i]>'F')&&(ans1[i]<'a'||ans1[i]>'f')) flag=1;
			}
			if(flag==1) {
				JOptionPane.showMessageDialog(null, "请输入0-F之间的数！");
				return;
			}
			for(int i=0; i<str7.length(); i++){//
				if(str7.charAt(i)=='A'||str7.charAt(i)=='B'||str7.charAt(i)=='C'||str7.charAt(i)=='D'||str7.charAt(i)=='E'||str7.charAt(i)=='F') {
					zhengxing[i]=str7.charAt(i)-55;
				}else if(str7.charAt(i)=='a'||str7.charAt(i)=='b'||str7.charAt(i)=='c'||str7.charAt(i)=='d'||str7.charAt(i)=='e'||str7.charAt(i)=='f'){
					zhengxing[i]=str7.charAt(i)-87;
				}else
				zhengxing[i] = Integer.parseInt(str7.charAt(i)+"");
				
				}			
			str8=String.valueOf(pro.transform1(zhengxing,16));
			shiliu_shi2.setText(str8);
			}
	});
	
	jbt6.addActionListener(new ActionListener() { // 按钮动作事件处理
		public void actionPerformed(ActionEvent e) {
			str9=shi_shiliu1.getText();
			int flag=0;
			int [] zhengxing = new int[str9.length()];
			char[] ans1 = str9.toCharArray();	//将字符串转化为字符数组		
			for(int i=0;i<ans1.length;i++) {
				if(ans1[i]<'0') flag=1;							
			}
			if(flag==1) {JOptionPane.showMessageDialog(null, "请输入整数");
			return;
			}
			shi_shiliu2.setText(pro.transform2(str9,16));
		}
	});	
    setVisible(true);    setSize(600, 300);
	}

}
