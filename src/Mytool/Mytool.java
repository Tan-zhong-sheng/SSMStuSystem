package Mytool;


import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpSession;

import org.springframework.util.DigestUtils;



public class Mytool{
	public static final Integer end = 8;
	
	public static Integer turn(String a) {
		int i = 0;
		try {
			i = Integer.parseInt(a);
		}catch(NumberFormatException e){
			e.printStackTrace();
			return -1500;
		}
		return i;
	}
	
	public static Integer total(Integer count){
		Integer total = (count - (count%end))/end;
		return total;
	}
	
	public static Integer start(Integer pageindex){
		Integer start=(pageindex-1)*end;
		return start;
	}
	public static String pageindex(String currentPage){
		if(currentPage==null){//判断当前页面
	  		return "1";
	  	}else{
	  		return currentPage;
	  	}
	}
	public static Integer pagesize(Integer pageindex,Integer total){
		if(pageindex>total){
			return total;
		}else if(pageindex<=0){
			pageindex=1;
			return pageindex;
		}
		return pageindex;
	}
	public static Integer totalsize(Integer count,Integer total){
		if(count % Mytool.end != 0){
			total++;
			return total;
	     }else{
	    	return total;
	     }
	}
	public static String GetMd5(String pwd){
		char [] key = pwd.toCharArray();
		String result = null;
		for(int i = 0; i <key.length/2; i++){
			result+=Integer.toBinaryString(key[i]);
		}
		String password = DigestUtils.md5DigestAsHex(pwd.getBytes())+result;
		return password;
	}
	
/*	public static boolean CheckMd5(String pwd,String pwd1){
		String password = DigestUtils.md5DigestAsHex(pwd.getBytes());
		String password1 = DigestUtils.md5DigestAsHex(pwd1.getBytes());
		if(password.equals(password1)){
			return true;
		}else{
			return false;
		}
	}*/

	public static String GetCheckCode(){
		String code = "0123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
		StringBuffer str = new StringBuffer();
		Random ra = new Random();
		for(int i = 0 ; i<4; i++){
			str.append(code.charAt(ra.nextInt(code.length())));
		}
		return str.toString();
	}
	public static String distinguish(int status){
		if(status==0){
			return "舞弊";
		}else if(status==2){
			return "缺考";
		}else{
			return "正常考试";
		}
	}
}
