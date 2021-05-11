package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import pojo.Classes;
import pojo.SelectScope;
import pojo.student;

import Mytool.Mytool;
import Service.ClassesService;

@Controller
@SessionAttributes(value={"ClassInfo","selectClassesnumber"})
public class ClassessController {
	@Autowired
	private ClassesService cr;
	static Logger logger = Logger.getLogger(ClassessController.class);
	@RequestMapping(value="/Classinsert",method=RequestMethod.POST)
	public String Classinsert(Classes cl,HttpServletResponse response) throws IOException{
		boolean i =cr.Classinsert(cl);
		PrintWriter out = response.getWriter();
		try {
			if(i){
				out.print("<script>alert('添加成功')</script>");
				out.close();
				return "redirect:/ClassInfo";
			}else{
				out.print("添加失败");
				out.close();
				return null;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			out.close();
			return null;
		}
	}
	@RequestMapping("/SelectClassid")
	public ModelAndView SelectClassid(){
		List<Integer> list = cr.SelectClassid();
		ModelAndView model = new ModelAndView();
		try {
			model.setViewName("register");
			model.addObject("list",list );
			return model;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	@RequestMapping("/ClassInfo")
	public ModelAndView ClassInfo(HttpServletRequest request){
		ModelAndView model = new ModelAndView();
		String currentPage=request.getParameter("pageindex");
	  	currentPage = Mytool.pageindex(currentPage);
	  	Integer pagesize=5;
	  	Integer pageindex=Integer.parseInt(currentPage);
	  	Integer count=cr.Count();
	  	Integer total = (count - (count%pagesize))/pagesize; //总页数
	  	total = Mytool.totalsize(count, total);
		pageindex = Mytool.pagesize(pageindex, total);
        Integer start=(pageindex-1)*pagesize;
        List<Classes> list=new ArrayList<Classes>();
        list=cr.ClassesInfo(start, Mytool.end);
        if(list!=null){
			model.setViewName("Class/ClassInfo");
			model.addObject("ClassInfo", list);
	        model.addObject("total", total);
	        model.addObject("pageindex", pageindex);
	        model.addObject("count", count);
	        return model;
        }else{
        	model.setViewName("Student/SelectError");
        	return model;
        }
	}
	@RequestMapping("/DeleteClasses")
	public String DeleteClasses(HttpServletRequest request){
		try {
			String name =request.getParameter("classesid");
			Integer classesid = Mytool.turn(name);
			cr.delete(classesid);
			return "redirect:/ClassInfo";
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	@RequestMapping("/SelectClassesnumber")
	public ModelAndView selectClassesnumber(HttpServletRequest request){
		try {
			ModelAndView model = new ModelAndView();
			String name = request.getParameter("classesid");
			Integer classesid = Mytool.turn(name);
			String name1 = request.getParameter("cid");
			Integer cid = Mytool.turn(name1);
			List<SelectScope> list=cr.selectClassesnumber(classesid,cid);
			model.setViewName("Teacher/SelectClassnumber");
			model.addObject("selectClassesnumber", list);
			model.addObject("classesid", classesid);
			model.addObject("cid", cid);
			return model;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	@RequestMapping("/SelectStudentNum")
	@ResponseBody
	public boolean SelectStudentNum(String classid){
		Integer Classid = Mytool.turn(classid);
		Integer num = cr.SelectStudentNum(Classid);
		Integer Maxnum =cr.SelectStudentMaxNum(Classid);
		if(num>Maxnum){
			return false;
		}else{
			return true;
		}
	}
	
}
