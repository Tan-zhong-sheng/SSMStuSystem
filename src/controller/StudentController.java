package controller;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import pojo.*;
import Mytool.Mytool;
import Service.ScService;
import Service.Servicetzs;
import Service.StudentService;

@Controller
@SessionAttributes(value={"studentmain" ,"number","Information","onescope"})
public class StudentController {
	@Autowired
	private StudentService ser;
	@Autowired
	private Servicetzs Ser;
	@Autowired
	private ScService SC;
	@Autowired
	private SC s;
	static Logger logger = Logger.getLogger(StudentController.class);
	
	@RequestMapping(value="/studentselectall")
	public ModelAndView selectall(HttpServletRequest request){
			ModelAndView model = new ModelAndView();
		  	String currentPage=request.getParameter("pageindex");
		  	currentPage = Mytool.pageindex(currentPage);
		  	Integer pageindex=Mytool.turn(currentPage);
		  	Integer count=ser.studentcount();
		  	Integer total = Mytool.total(count); //总页数
		  	total = Mytool.totalsize(count, total);
			pageindex = Mytool.pagesize(pageindex, total);
	        Integer start=Mytool.start(pageindex);
	        List<student> list=new ArrayList<student>();
	        list=ser.studentall(start, Mytool.end);
	        if(list!=null){
		        model.setViewName("Student/select");
		        model.addObject("studentmain",list);
		        model.addObject("total", total);
		        model.addObject("pageindex", pageindex);
		        model.addObject("count", count);
		        return model;
	        }else{
	        	model.setViewName("Student/SelectError");
	        	return model;
	        }
	}
	
	@RequestMapping("/Studentinformation")
	public ModelAndView Studentinformation(HttpSession session){
		ModelAndView model = new ModelAndView();
		Integer Stuid = (Integer) session.getAttribute("name");
		student Stu = ser.Studentinformation(Stuid);
		if(Stu!=null){
			model.setViewName("Student/Information");
			model.addObject("Information",Stu);
			return model;
		}else{
			model.setViewName("Student/main");
			return model;
		}
	}
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView register(@Validated student User,BindingResult result,HttpServletResponse response,HttpServletRequest request) {//添加学生
		try {
			PrintWriter out = response.getWriter();
			if(result.getErrorCount() > 0){
				List<ObjectError> list=result.getAllErrors();
				for(ObjectError error : list){
					out.print(error.getDefaultMessage());
					logger.error(error.getDefaultMessage());
				}
			}
			String pwd = Mytool.GetMd5(User.getPwd());
			User.setPwd(pwd);
			ModelAndView model = new ModelAndView();
			boolean i = ser.register(User);
			if (i) {
				try {
					Integer stuid = User.getStuid();
					s.setStuid(stuid);
					s.setGrade(User.getGrade());
					s.setStatus("1");
					List<Integer> cid = SC.SelectSc(User.getMajor(),User.getGrade());
					Iterator<Integer>  list1 = cid.iterator();
					while(list1.hasNext()){
						Integer Cid = list1.next();
						s.setCid(Cid);
						SC.addSc(s);
					}
				  	String currentPage=request.getParameter("pageindex");
				  	if(currentPage==null){
				  		currentPage="1";
				  	} 
				  	Integer pageindex=Mytool.turn(currentPage);
				  	Integer count=ser.studentcount();
				  	Integer total = Mytool.total(count); //总页数
					if(count % Mytool.end != 0){
						total++;
				     }
					try {
						if(pageindex>total){
							pageindex = total;
						}else if(pageindex<=0){
							pageindex=1;
						}
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println(e.getMessage());
						logger.error(e.getMessage());
					}
			        Integer start=Mytool.start(pageindex);
			        List<student> list=new ArrayList<student>();
			        list=ser.studentall(start, Mytool.end);
			        if(list!=null){
				        model.setViewName("Student/select");
				        model.addObject("studentmain",list);
				        model.addObject("total", total);
				        model.addObject("pageindex", pageindex);
				        model.addObject("count", count);
				        return model;
			        }else{
			        	model.setViewName("Student/SelectError");
			        	return model;
			        }
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
				model.setViewName("Student/select");
				model.addObject("number", User);
				return model;
			} else {
				model.setViewName("error");
				return model;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			System.out.println("修改失败");
			return null;
		}

	}
	@RequestMapping(value="/Resetuser")
	public String ResetUser(Integer stuid) throws IOException{
		//ModelAndView model = new ModelAndView();
		student stu =new student();
		stu.setPwd("");
		stu.setStuid(stuid);
		boolean i =Ser.ResetUser(stu);
		if(i){
			 return "redirect:/studentselectall";
		}
		return null;
	}
	@RequestMapping("/Studentselet")
	public ModelAndView Studentselet(HttpSession session,HttpServletResponse response) throws IOException{
		Integer stuid = (Integer) session.getAttribute("name");
		PrintWriter out = response.getWriter();
		ModelAndView model = new ModelAndView();
		List<scope> Sc = ser.SelectStudentScope(stuid);
		if(Sc!=null){
			model.setViewName("Scope/SelectOneScope");
			model.addObject("onescope", Sc);
			return model;
		}else{
			out.print("查询账号不存在");
			out.close();
			return null;
		}
	}
	@RequestMapping(value="/UpdatePhone",method=RequestMethod.POST)
	public String UpdatePhone(String phone,HttpSession session) throws IOException{
		try {
			Long Phone = Long.parseLong(phone);
			Integer stuid=(Integer) session.getAttribute("name");
			student Stu = (student) session.getAttribute("student");
			Stu.setPhone(Phone);
			session.setAttribute("student", Stu);
			boolean i = ser.UpdatePhone(stuid, Phone);
			if(i){
			    return "redirect:/Studentinformation";
			}else{
				return "Student/error";
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	@RequestMapping(value="/updatepwd",method=RequestMethod.POST)
	public String updatepwd(HttpSession session,String pwd,String pwd2){//修改密码
		try {
			Integer stuid = (Integer)session.getAttribute("name");
			String Stuid = ser.CheckPwd(stuid);
			String Pwd = Mytool.GetMd5(pwd);
			String Pwd2 = Mytool.GetMd5(pwd2);
			if(Stuid.equals(Pwd)){
				boolean i =ser.updatepwd(stuid, Pwd, Pwd2);
				if(i){
					return "Student/Success";
				}else{
					return "Student/error";
				}
			}else{
				return "Student/error";
			}
		} catch (NumberFormatException e) {
			System.out.println("请输入中文");
			return null;
		}
	}
	@RequestMapping(value="/UpdateGrade",method=RequestMethod.POST)
	public String UpdateGrade(String grade,String grade1,String major,HttpServletResponse response) throws IOException{//修改年级
		PrintWriter out = response.getWriter();
		try {
			List<Integer> list = SC.SelectCid(major);
			Iterator<Integer> it = list.iterator();
			while(it.hasNext()){
				Integer cid = it.next();
				boolean S = SC.DeleteGrade(cid,"1"); 
				if(S==false){
					out.print("删除失败");
				}
			}
			boolean i = ser.UpdateGrade(grade,grade1,major);
			if(i){
				List<Integer> cid = SC.SelectSc(major,grade1);
				List<Integer> stuid = SC.SelectStuid(major,grade1);
				Iterator<Integer>  listStuid = stuid.iterator();
				while(listStuid.hasNext()){
					Integer Stuid = listStuid.next();
					Iterator<Integer>  listCid = cid.iterator();
					//迭代器需要next完不会重置所以需要重新声明
					while(listCid.hasNext()){
						Integer Cid = listCid.next();
						s.setStuid(Stuid);
						s.setCid(Cid);
						s.setGrade(grade1);
						s.setStatus("1");
						SC.addSc(s);
					}
				}
				out.print("<script>alert('修改成功')</script>");
				out.close();
				return null;
			}else{
				out.print("<script>alert('修改失败')</script>");
				return null;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}finally{
			out.close();
		}
		return null;
	}
	

	@RequestMapping(value="/Selectcondition",method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView Selectcondition(student st) throws IOException{
		ModelAndView model = new ModelAndView();
		List<student> list = ser.Selectcondition(st);
		if(list!=null){
			model.setViewName("Student/Selectcondition");
			model.addObject("list", list);
			return model;
		}else{
			model.setViewName("Student/SelectError");
			return model;
		}
	}
	@RequestMapping("/SelectCourseOne")
	public ModelAndView SelectCourseOne(){
		ModelAndView model = new ModelAndView();
		List<student> list = ser.StudentCourse();
		if(list!=null){
			model.setViewName("course/ComputerTimetable");
			model.addObject("list", list);
			return model;
		}else{
			model.setViewName("Student/SelectError");
			return model;
		}
	}
	@RequestMapping("/addElective")
	public String addElective(HttpServletRequest request,HttpSession session,HttpServletResponse res) throws IOException{//选修
		PrintWriter out = res.getWriter();
		String status = SC.cidstatus();
		if(status.equals("0")){
			out.print("<script> alert('选课尚未开始') </script>");
			out.close();
			return null;
		}else{
			String Cid = request.getParameter("cid");
			Integer cid = Mytool.turn(Cid);
			student stu = (student) session.getAttribute("student");
			s.setCid(cid);
			s.setStatus("0");
			s.setGrade(stu.getGrade());
			s.setStuid(stu.getStuid());
			Integer check = SC.selectcid(cid,stu.getStuid());
			if(check!=null){
				out.print("<script> alert('该课程已被选修') </script>");
				out.close();
				return null;
			}
			boolean i = SC.addSc(s);
			if(i){
				out.print("<script> alert('选修成功') </script>");
			}else{
				out.print("<script> alert('选修失败') </script>");
			}
			out.close();
			return null;
		}
	}
	@RequestMapping("/DeleteElective")
	public String DeleteElective(HttpSession session,HttpServletRequest reuqest,HttpServletResponse res) throws IOException{//退修
		PrintWriter out = res.getWriter();
		String status = SC.cidstatus();
		if(status.equals("0")){
			out.print("<script> alert('选课已经结束') </script>");
			out.close();
			return null;
		}else{
			student stu = (student)session.getAttribute("student");
			Integer stuid = stu.getStuid();
			String Cid = reuqest.getParameter("cid");
			Integer cid = Mytool.turn(Cid);
			Integer check  = SC.selectcid(cid,stu.getStuid());
			if(check==null){
				out.print("<script> alert('您还没选修该课程') </script>");
				return null;
			}
			boolean i = SC.DeleteElective(cid, stuid);
			if(i){
				out.print("<script> alert('退修成功！') </script>");
			}else{
				out.print("<script> alert('退修失败') </script>");
			}
		}
		out.close();
		return null;
	}
	@RequestMapping("/selectclassespeople")
	public ModelAndView selectclassespeople(HttpServletRequest request){//查看班级人数
		String classid =  request.getParameter("classid");
		Integer Classid = Mytool.turn(classid);
		ModelAndView model = new ModelAndView();
		List<student> list = ser.selectclassespeople(Classid);
		if(list!=null){
			model.setViewName("Class/SelectPeople");
			model.addObject("list", list);
			return model;
		}else{
			return null;
		}
	}
	@RequestMapping(value="/selectdata")
	public ModelAndView selectdata(HttpServletRequest request){
			ModelAndView model = new ModelAndView();
		  	String currentPage=request.getParameter("pageindex");
		  	currentPage = Mytool.pageindex(currentPage);
		  	Integer pageindex=Mytool.turn(currentPage);
		  	Integer count=ser.selectdatecount();
		  	Integer total = Mytool.total(count); //总页数
		  	total = Mytool.totalsize(count, total);
			pageindex = Mytool.pagesize(pageindex, total);
	        Integer start=Mytool.start(pageindex);
	        List<Studydata> list=new ArrayList<Studydata>();
	        list=ser.selectdata(start, Mytool.end);
	        if(list!=null){
		        model.setViewName("Student/selectdata");
		        model.addObject("selectdata",list);
		        model.addObject("total", total);
		        model.addObject("pageindex", pageindex);
		        model.addObject("count", count);
		        return model;
	        }else{
	        	model.setViewName("Student/SelectError");
	        	return model;
	        }
	}
	@RequestMapping(value="/download",method=RequestMethod.POST)
	public void download(HttpServletResponse response,HttpServletRequest request,String filename) throws IOException{
        //获取输入流  
        InputStream bis = new BufferedInputStream(new FileInputStream(new File(filename)));
        //转码，免得文件名中文乱码  
        filename = URLEncoder.encode(filename,"UTF-8");  
        //设置文件下载头  
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);    
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型    
        response.setContentType("multipart/form-data");   
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());  
        int len = 0;  
        while((len = bis.read()) != -1){  
            out.write(len);  
            out.flush();  
        }  
        out.close();
	}
	@RequestMapping("/test")
	public ModelAndView test(Integer cid,HttpServletResponse res) throws IOException{
		PrintWriter out = res.getWriter();
		ModelAndView model = new ModelAndView();
		String teststatus = SC.selecttest();
		if(teststatus.equals("0")){
			out.print("<script>alert('考试尚未开始')</script>");
			out.close();
			return null;
		}else{
			List<test> list = ser.test(cid);
			if(list!=null){
				model.addObject("list", list);
				model.addObject("cid", cid);
				model.setViewName("Student/test");
				return model;
			}
			out.close();
			return model;
		}
	}
	@RequestMapping(value="/correction",method =RequestMethod.POST)
	public String correction(int size,int cid,HttpServletRequest request,HttpSession session,HttpServletResponse res) throws IOException{
		PrintWriter out = res.getWriter();
		List<test> list = ser.test(cid);//查询试卷信息
		scope sc =new scope();
		student stu = (student) session.getAttribute("student");
		int scope=0;
		for(Integer i=0;i<size;i++){
			String result = request.getParameter(i.toString());//获得答题者答案
			String answer = list.get(i).getAnswer();//获得正确答案
			if(result==null){
				result="-19999dsad";
			}
			if(result.equals(answer)){//如果一样加20分
				scope+=20;
			}
		}
		sc.setScope(scope);
		sc.setGrade(stu.getGrade());
		sc.setStuid(stu.getStuid());
		sc.setCid(cid);
		sc.setStatus(5);
		boolean issucess= Ser.InsertScope(sc);
			if(issucess){
				out.print("<script>alert('提交成功')</script>");
			}else{
				out.print("<script>alert('提交失败')</script>");
			}
		out.close();
		return null;
	}
}