package controller;

import java.awt.Color;


import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;


import java.io.File;
import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.httpclient.HttpException;
import org.apache.log4j.Logger;
import org.apache.poi.hpsf.Array;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import pojo.photoverification;
import pojo.student;
import pojo.teacher;


import Mytool.Mytool;
import Mytool.PhoneCode1;
import Service.ScService;
import Service.Servicetzs;
import Service.StudentService;
import Service.TeacherService;

@Controller
@SessionAttributes(value = { "list", "name", "resetname", "student","adminname","teacherid","id","updatepwd"})
public class controller {
	@Autowired
	private StudentService Ser;
	@Autowired
	private Servicetzs ser;
	@Autowired
	private TeacherService ter;
	@Autowired
	private ScService sc;
	static Logger logger = Logger.getLogger(controller.class);
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(Integer id, String pwd, String power,
			HttpServletResponse response,HttpSession session) throws IOException {//登陆
		ModelAndView model = new ModelAndView();
		PrintWriter out = response.getWriter();
		session.setMaxInactiveInterval(65000);
			if (power.equals("customer")) {
				String Pwd = Mytool.GetMd5(pwd);
				student i = ser.login(id, Pwd);
				if (i!=null) {
					model.setViewName("redirect:/topage");
					/*session.setAttribute("name", id);
					session.setAttribute("student", i);*/
					model.addObject("name", id);
					model.addObject("student",i);  
					return model;
				} else {
					out.print("账号或密码错误");
					out.print("<a href=index.jsp>返回首页</a>");
					out.close();
					return null;
				}
			} else if(power.equals("governor")){
				boolean i = ser.governorLogin(id, pwd);
				if (i) {
					model.setViewName("redirect:/admintopage");
					model.addObject("adminname", id);
					return model;
				} else {
					out.print("账号或密码错误");
					out.print("<a href=index.jsp>返回首页</a>");
					out.close();
					return null;
				}
			}else{
				String Pwd = Mytool.GetMd5(pwd);
				teacher i =ter.Selectlogin(id, Pwd);
				if(i!=null){
					model.addObject("id", id);
					model.setViewName("redirect:/teachertopage");
					model.addObject("teacherid", i);
					return model;
				}else{
					out.print("账号或密码错误");
					out.print("<a href=index.jsp>返回首页</a>");
					out.close();
					return null;
				}
			}
	}
	@RequestMapping("/topage")
	public String topage(){
		return "Student/main";
	}
	@RequestMapping("/admintopage")
	public String admintopage(){
		return "Administrator";
	}
	@RequestMapping("/teachertopage")
	public String teachertopage(){
		return "Teacher/main";
	}
	
	@RequestMapping(value = "/checklogin", method = RequestMethod.POST)
	@ResponseBody
	//-1账号密码错误，1账号密码正确，-2验证码错误，-1500输出格式不对
	public Integer checklogin(String name, String pwd, String power,HttpSession session,String checkVerification) throws IOException {//登陆
		String code = (String) session.getAttribute("checkCode");
		Integer id = Mytool.turn(name);
		if(id==-1500){
			return -1500;
		}
		if(code.equalsIgnoreCase(checkVerification)){//如果验证码一样
			if (power.equals("customer")) {
				String Pwd = Mytool.GetMd5(pwd);
				student i = ser.login(id, Pwd);
				if (i!=null) {
					return 1;
				} else {
					return -1;
				}
			} else if(power.equals("governor")){
				boolean i = ser.governorLogin(id, pwd);
				if (i) {
					return 1;
				} else {
					return -1;
				}
			}else{
				String Pwd = Mytool.GetMd5(pwd);
				teacher i =ter.Selectlogin(id, Pwd);
				if(i!=null){
					return 1;
				}else{
					return -1;
				}
			}
		}else{
			return -2;//验证码错误
		}
	}
	@RequestMapping(value = "/servlet", method = RequestMethod.POST)
	public String Ajax(HttpServletResponse response, HttpServletRequest request)//AJAX
			throws IOException {
		String name = request.getParameter("tzs");
		int id = Mytool.turn(name);
		student i = ser.Ajax(id);
		PrintWriter out = response.getWriter();
		if (i!=null) {
			out.print(1);
		} else {
			out.print(0);
		}
		return null;
	}
	@RequestMapping("/Cancellation")
	public String Cancellation(HttpSession session)//注销
			throws IOException, ServletException {
		session.invalidate();
		return "index";
	}
	@RequestMapping("/Verification")
	public void Verification(HttpServletRequest request,HttpServletResponse resp,HttpSession session) throws IOException{
		 String checkCode = Mytool.GetCheckCode();
		 session.setAttribute("checkCode", checkCode);
		 BufferedImage image = new BufferedImage(80,30,BufferedImage.TYPE_INT_RGB);
		 Graphics g = image.getGraphics();
		    //设置画笔颜色，用灰色做背景
		    g.setColor(Color.WHITE);
		    //向Image中填充灰色
		    g.fillRect(0,0,80,30);
		    Random r = new Random();
		    //设置3条干扰线
		    for (int i = 0; i < 3; i++) {
		        g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
		        g.drawLine(r.nextInt(80), r.nextInt(30), r.nextInt(80), r.nextInt(80));
		    }
		    //设置验证码字符串的颜色
		    g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
		    //设置字符的大小
		    g.setFont(new Font("黑体",Font.BOLD,24));
		    //在图片中写入验证码字符串
		    g.drawString(checkCode,15,20);
		    //将Image对象以PNG格式输出给所有的客户端
		    ImageIO.write(image,"PNG",resp.getOutputStream());
	}
/*	@RequestMapping(value="/SendCode",method=RequestMethod.POST)
	@ResponseBody
	public Integer SendCode(HttpSession session) throws HttpException, IOException{
		student stu = (student) session.getAttribute("student");
		String phone = stu.getPhone().toString();
		if(phone!=null){
			Map<String, String>  m = PhoneCode.CheckPhone(phone);
			String result = m.get("result");
			Integer Rusult = Mytool.turn(result);
			System.out.println("result"+result);
			String code = m.get("code");
			session.setAttribute("code",code);
			session.setMaxInactiveInterval(60*3);
			return Rusult;
		}else{
			return -10000;
		}
	}*/
	@RequestMapping(value="/CheckPhone",method=RequestMethod.POST)
	@ResponseBody
	public boolean CheckPhoneCode(String Code,HttpSession session){
		try{
		String code = (String) session.getAttribute("code");
		if(Code==null){
			return false;
		}
		if(code.equalsIgnoreCase(Code)){
			return true;
		}else{
			return false;
		}
		}catch(NullPointerException e){
			logger.error(e.getMessage());
			return false;
		}catch(Exception e){
			logger.error(e.getMessage());
			return false;
		}
	}
	@RequestMapping(value="/SendCode1",method=RequestMethod.POST)
	@ResponseBody
	public boolean SendCode1(final HttpSession session) throws HttpException, IOException{
		student stu = (student) session.getAttribute("student");
		teacher Ter = (teacher) session.getAttribute("teacherid");
		if(stu!=null){
			String phone = stu.getPhone().toString();
			if(phone!=null){
				Map<String, Object>  m = PhoneCode1.CheckPhone1(phone);
				StringBuilder result = (StringBuilder) m.get("result");
				System.out.println("result"+result);
				String code = (String) m.get("code");
				session.setAttribute("code",code);
				Timer timer = new Timer();
	    	    timer.schedule(new TimerTask() {
	    	       public void run() {
	    	         session.removeAttribute("code");
	    	      }
	    	     }, 60000);// 设定指定的时间time,此处为2000毫秒
				return true;
			}else{
				return false;
			}
		}else{
			String terphone = Ter.getPhone().toString();
			if(terphone!=null){
				Map<String, Object>  m = PhoneCode1.CheckPhone1(terphone);
				StringBuilder result = (StringBuilder) m.get("result");
				System.out.println("result"+result);
				String code = (String) m.get("code");
				session.setAttribute("code",code);
				Timer timer = new Timer();
	    	    timer.schedule(new TimerTask() {
	    	       public void run() {
	    	         session.removeAttribute("code");
	    	      }
	    	     }, 60000);// 设定指定的时间time,此处为60000毫秒
				return true;
			}else{
				return false;
			}
		}

	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(@RequestParam(value ="file") MultipartFile file,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException {
		PrintWriter out = response.getWriter();
		Integer stuid = (Integer) session.getAttribute("name");
		Integer teacherid = (Integer) session.getAttribute("id");
		if(stuid!=null){
			if (!file.isEmpty()) {
				//String path = request.getServletContext().getRealPath("/upload/");
				String path = "D:\\photo\\";
				String filename = file.getOriginalFilename();
				// String lastpath = path+Ser.SelectPhoto(stuid);
				/*File lastfile = new File(lastpath);
				if(!lastfile.exists()){
					//lastfile.mkdir();
					lastfile.delete();
				}*/
				File f = new File(path);
				if(!f.exists()){
					f.mkdir();
				}
				try {
					String Filepath = path+filename;
					//String name = "upload"+filename;
					file.transferTo(new File(Filepath));
					boolean i =Ser.updatephoto(filename, stuid);
					if(i){
						out.print("上传成功");
						return "redirect:/Studentinformation";
					}else{
						out.print("上传失败");
						return "redirect:/Studentinformation";
					}
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
				return "redirect:/Studentinformation";
			} else {
				out.print("上传失败");
				out.close();
				return null;
			}
		}else{
			if (!file.isEmpty()) {
				//String path = request.getServletContext().getRealPath("/teacher/");
				String path = "D:\\photo\\";
				String filename = "teacher"+file.getOriginalFilename();
			   // String lastpath = path+ter.selectphoto(teacherid);
				/*File lastfile = new File(lastpath);
				if(!lastfile.exists()){
					lastfile.mkdir();
				}
				lastfile.delete();*/
				File f = new File(path);
				if(!f.exists()){
					f.mkdir();
				}
				try {
					String Filepath = path+filename;
					//String name = "teacher"+filename;
					file.transferTo(new File(Filepath));
					boolean i =ter.updatephoto(teacherid, filename);
					if(i){
						out.print("上传成功");
						return "redirect:/TeacherInfo";
					}else{
						out.print("上传失败");
						return "redirect:/TeacherInfo";
					}
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
				return "redirect:/TeacherInfo";
			} else {
				out.print("上传失败");
				out.close();
				return null;
			}
		}
	}
	@RequestMapping("/openElective")
	@ResponseBody
	public boolean openElective(HttpSession session){
		try {
			String status = sc.cidstatus();
			if(status=="1"){
				return false;
			}else{
				sc.updatecidstatus();
			return true;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}
	
	@RequestMapping("/closeElective")
	@ResponseBody
	public boolean closeElective(HttpSession session){
		try {
			String status = sc.cidstatus();
			if(status=="0"){
				return false;
			}else{
				sc.closecidstatus();
				return true;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}
	@RequestMapping("/photocode")
	public ModelAndView photocode(){//获得图片验证码
		ModelAndView model = new ModelAndView();
		Random ra = new Random();
		List<photoverification> list = new ArrayList<photoverification>();
		List<Integer> list1 = new ArrayList<Integer>();
		for(int i =0;i<4;i++){
			Integer pid = ra.nextInt(4);
			//logger.error(pid);
			photoverification p = ser.Code(i);//获得图片信息
			Integer id = p.getPid();
			/*if(list1.size()==0){
				System.out.println(list.size());
				list1.add(id);
				continue;
			}else*/ 
			/*if(list1.size()<4){
				logger.error(list1.size());
				Iterator<Integer> it = list1.iterator();
					while(it.hasNext()){
						Integer a = it.next();
						if(pid!=a){
							System.out.println("id"+id);
							System.out.println("pid"+pid);
							it.remove();
							list1.add(id);
							list.add(p);
						}
					}
			}*/
			list.add(p);
		}
		model.setViewName("Teacher/updatepwd");
		model.addObject("updatepwd", list);
		return model;
		
	}
	@RequestMapping("/studentphotocode")
	public ModelAndView studentphotocode(){//获得图片验证码
		ModelAndView model = new ModelAndView();
		//Random ra = new Random();
		List<photoverification> list = new ArrayList<photoverification>();
		for(int i =0;i<4;i++){
			//Integer pid = ra.nextInt(4);
			photoverification p = ser.Code(i);//获得图片信息
			//Integer id = p.getPid();
			list.add(p);
		}
		model.setViewName("Student/updatepwd");
		model.addObject("updatepwd", list);
		return model;
		
	}
	@RequestMapping(value="/Checkphotocode",method=RequestMethod.POST)
	@ResponseBody
	public boolean Checkphotocode(String photocode,String describes){
		try {
			Integer pid = Mytool.turn(photocode);
			photoverification p = ser.Code(pid);
			if(describes.equals(p.getDescribes())){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}
	@RequestMapping("/optentest")
	@ResponseBody
	public boolean optentest(){
		boolean test = sc.opentest();
		if(test){
			return true;
		}else{
			return false;
		}
	}
	@RequestMapping("/closttest")
	@ResponseBody
	public boolean closttest(){
		boolean test = sc.closetest();
		if(test){
			return true;
		}else{
			return false;
		}
	}
}
