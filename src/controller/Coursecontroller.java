package controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import pojo.Classes;
import pojo.course;
import pojo.scope;
import pojo.student;
import pojo.teacher;

import Mytool.Mytool;
import Service.CourseService;
import Service.ScService;
import Service.Servicetzs;

@Controller
@SessionAttributes(value={"Scope","Clist","Cmain","Studentinfo"})
public class Coursecontroller {
	@Autowired
	private Servicetzs ser;
	@Autowired
	private CourseService cou;
	@Autowired
	private ScService SC;
	static Logger logger = Logger.getLogger(Coursecontroller.class);
	
	@RequestMapping(value="/Cinsert",method=RequestMethod.POST)
	public ModelAndView Cinsert(course cou,HttpServletResponse response) throws IOException{
		ModelAndView model =new ModelAndView();
		PrintWriter out =response.getWriter();
		boolean i = ser.Cinsert(cou);
		try{
		if(i){
			model.setViewName("course/Csuccess");
			model.addObject("Cresult", cou);
			return model;
		}else{
			out.print("添加失败");
			out.close();
			return model;
		}
		}catch(Exception e){
			logger.error(e.getMessage());
			out.close();
			return null;
		}
	}
	@RequestMapping(value="/Cselect")
	public ModelAndView Cselect(Integer cid,String ClassRoom,String major,HttpServletResponse response) throws IOException{
		ModelAndView model = new ModelAndView();
		PrintWriter out =response.getWriter();
		course cour = ser.Cselect(cid,ClassRoom,major);
		if(cour!=null){
			model.setViewName("course/selectresult");
			model.addObject("Cour", cour);
			out.close();
			return model;
		}else{
			out.print("查询账号不存在");
			out.print("<a href=Administrator.jsp>返回管理页面</a>");
			out.close();
		}
		return model;
	}
	@RequestMapping("/Scopeselect")
	public ModelAndView Scopeselect(Integer stuid,HttpServletResponse response) throws IOException{
		ModelAndView model = new ModelAndView();
		PrintWriter out =response.getWriter();
		scope sc= ser.Scopeselect(stuid);
		if(sc!=null){
			model.setViewName("Scope/result");
			model.addObject("Scope", sc);
			out.close();
			return model;
		}else{
			out.print("查询账号不存在");
			out.print("<a href=Administrator.jsp>返回管理页面</a>");
			out.close();
			return null;
		}
	}
	@RequestMapping(value="/Cselectall")
	public ModelAndView selectall(HttpServletRequest request){
		try {
			ModelAndView model = new ModelAndView();
		  	String currentPage=request.getParameter("pageindex");
		  	currentPage = Mytool.pageindex(currentPage);
		  	Integer pageindex=Integer.parseInt(currentPage);
		  	Integer count=ser.selectcount();
		  	Integer total = (count - (count%Mytool.end))/Mytool.end; //总页数
		  	total = Mytool.totalsize(count, total);
			pageindex = Mytool.pagesize(pageindex, total);
	        Integer start=(pageindex-1)*Mytool.end;
	        List<course> list=new ArrayList<course>();
	        list=ser.Cselectall(start, Mytool.end);
	        if(list!=null){
		        model.setViewName("course/selectall");
		        model.addObject("Cmain", list);
		        model.addObject("total", total);
		        model.addObject("pageindex", pageindex);
		        model.addObject("count", count);
		        return model;
	        }else{
	        	model.setViewName("course/SelectError");
	        	return model;
	        }

		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	@RequestMapping(value="/deleteCourse")
	public String deleteCourse(HttpServletRequest request){
		String id = request.getParameter("cid");
		Integer cid = Mytool.turn(id);
		boolean i=ser.deleteCourse(cid);
		if(i)
			return "redirect:/Cselectall";
		else{
			return "course/error";
		}
	}
	
	@RequestMapping("/Studentinfo")
	public ModelAndView StudentInfo(HttpServletRequest request,String cid){
		ModelAndView model = new ModelAndView();
		//String cname = request.getParameter("cid");
		Integer Cid = Mytool.turn(cid);
		String currentPage=request.getParameter("pageindex");
	  	currentPage = Mytool.pageindex(currentPage);
	  	Integer pageindex=Mytool.turn(currentPage);
        Integer start=Mytool.start(pageindex);
	  	Integer count=cou.ScstudentCount(Cid);
	  	Integer total = Mytool.total(count); //总页数
	  	total = Mytool.totalsize(count, total);
		pageindex = Mytool.pagesize(pageindex, total);
        List<student> list=new ArrayList<student>();
        list=cou.StudentInfo(start, Mytool.end, Cid);
        if(list!=null){
			model.setViewName("course/Studentinfo");
			model.addObject("Studentinfo", list);
	        model.addObject("total", total);
	        model.addObject("pageindex", pageindex);
	        model.addObject("count", count);
	        model.addObject("Cid", Cid);
	        return model;
        }else{
        	model.setViewName("Student/SelectError");
        	return model;
        }
	}
	
/*	@RequestMapping("/Choice")
	public String Choice(HttpSession session){
		try {
			Integer stuid = (Integer)session.getAttribute("name");
			String major=cou.Choice(stuid);
			if(major.equals("Computer")){
				return "course/ComputerTimetable";
			}else if(major.equals("English")){
				return "course/EnglishTimetable";
			}
			return "Student/SelectError";
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}*/
	@RequestMapping("/SelectStudentCourse/{One}")
	public ModelAndView SelectStudentCourse(@PathVariable("One") String grade,HttpSession session){
		ModelAndView model = new ModelAndView();
		Integer stuid = (Integer)session.getAttribute("name");
		String major = cou.Choice(stuid);
		List<String> list = cou.SelectStudentCourse(grade,major);
		if(list!=null){
			model.setViewName("Student/SelectCourse");
			model.addObject("Course", list);
			return model;
		}else{
			model.setViewName("Student/SelectError");
			return model;
		}
	}
	
	@RequestMapping("/Elective")
	public ModelAndView Elective(HttpSession session,HttpServletRequest request) throws IOException{
		ModelAndView model = new ModelAndView();
				student stu = (student)session.getAttribute("student");
				String major = stu.getMajor();
				String currentPage=request.getParameter("pageindex");
			  	currentPage = Mytool.pageindex(currentPage);
			  	Integer pageindex=Mytool.turn(currentPage);
			  	Integer count=cou.Count(major);
			  	Integer total = Mytool.total(count); //总页数
			  	total = Mytool.totalsize(count, total);
				pageindex = Mytool.pagesize(pageindex, total);
		        Integer start=Mytool.start(pageindex);
		        List<course> list=new ArrayList<course>();
		        list=cou.SelectExistingCid(start, Mytool.end, major);//查看可以选修科目
				List<Integer> list1 = cou.Subject(stu.getStuid());//查看是否选修
		        if(list!=null){
					if(list1!=null){
						for(int i=0;i<list1.size()-1;i++){
							for(int j=0;j<list1.size()-1-i;j++){
								Integer temp = list1.get(j);
								Integer temp1 = list1.get(j+1);
								if(temp>temp1){
									list1.set(j, temp1);
									list1.set(j+1, temp);
								}
							}
						}
						model.addObject("list", list1);
					    model.setViewName("Student/Elective");
					    model.addObject("ExistingCid",list);
					    model.addObject("total", total);
					    model.addObject("pageindex", pageindex);
					    model.addObject("count", count);
					    model.addObject("major", major);
						return model;
					}else{
						model.addObject("list", null);
				        model.setViewName("Student/Elective");
				        model.addObject("ExistingCid",list);
				        model.addObject("total", total);
				        model.addObject("pageindex", pageindex);
				        model.addObject("count", count);
				        model.addObject("major", major);
				        return model;
					}
		        }else{
		        	model.setViewName("Student/SelectError");
		        	return model;
		        }
		}
	@RequestMapping("/coursedistribution")
	public ModelAndView coursedistribution(String major,Integer cid,HttpSession session){
		ModelAndView model = new ModelAndView();
		session.setAttribute("CID", cid);
		List<Classes> list = cou.selectclasses();
		List<teacher> list1 = cou.selectteacher(major);
		model.addObject("T", list1);
		model.addObject("C", list);
		model.setViewName("course/distribution");
		return model;
	}
}
