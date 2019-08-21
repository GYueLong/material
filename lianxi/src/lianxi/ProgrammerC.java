package lianxi;
//进制转化
//二进制——>十进制、	十进制——>二进制
//八进制——>十进制、	十进制——>八进制
//十六进制——>十进制、	十进制——>十六进制

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Vector;

public class ProgrammerC {
	String str1="";
	public int transform1(int[] er_shi,int k) {//k为要转化的进制数
		int ans=0;
		for(int i=er_shi.length-1;i>=0;i--) {		
			for(int j=0;j<er_shi.length-1-i;j++)
				er_shi[i]*=k;
			ans+=er_shi[i];
		}
		return ans;
	}
	public String transform2(String s,int k) {//k为要转化的进制数
		int ans = Integer.parseInt(s);
		int chushu;
		String s1="";
		Vector  b=new Vector();
		chushu=ans;
		while(chushu!=0) {
			if(k!=16) {
			b.add(chushu%k);
			chushu=chushu/k;
			}else {
					if(chushu%k==10||chushu%k==11||chushu%k==12||chushu%k==13||chushu%k==14||chushu%k==15) {
				    	switch(chushu%k) {
				    		case 10:b.add("A");break;
				    		case 11:b.add("B");break;
				    		case 12:b.add("C");break;
				    		case 13:b.add("D");break;
				    		case 14:b.add("E");break;
				    		case 15:b.add("F");break;
				    		}
						}else b.add(chushu%k);
					          chushu=chushu/k;			
						
			}
		}	
	    for(int i=0;i<b.size();i++) {
    	s1+=b.get(i);	    	
	    }

      return new StringBuffer(s1).reverse().toString();//将字符串反向输出
	}

}
