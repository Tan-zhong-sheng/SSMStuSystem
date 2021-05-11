package controller;

import java.io.IOException;





import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import pojo.student;
import Mytool.Mytool;
import Service.Servicetzs;

@Controller
@SessionAttributes(value={"studentmain"})
public class AdministratorController {
	@Autowired
	private Servicetzs ser;
	static Logger logger = Logger.getLogger(AdministratorController.class);
	@RequestMapping(value="/ResetUser" ,method=RequestMethod.POST)
	public String ResetUser(student User,HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		boolean i =ser.ResetUser(User);
		if(i){
			out.print("修改成功");
			return null;
		}
		return null;
	}

	@RequestMapping(value="Delete")
	public String Delete(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException{
		PrintWriter out =response.getWriter();
		Integer governor = (Integer) session.getAttribute("adminname");
		int level = ser.level(governor);
		String stuid =request.getParameter("stuid");
		Integer Stuid = Mytool.turn(stuid);
		try {
			if(level>=2){
				boolean i =ser.Delete(Stuid);
				if(i){
					 return "redirect:/studentselectall";
				}else{
					out.print("账号错误");
					return null;
				}
		}else{
				out.print("权限不足");
				logger.error("权限不足");
				return null;
		}
		} catch (Exception e) {
				logger.error(e.getMessage());
				return null;
		}
	}
	@RequestMapping(value="/Adminlevel")
	public ModelAndView leave(HttpSession session,HttpServletResponse response) throws IOException{
		ModelAndView model = new ModelAndView();
		PrintWriter out =response.getWriter();
		Integer id= (Integer) session.getAttribute("adminname");
		Integer i =ser.level(id);
		System.out.println("等级为"+i);
		if(i>0){
			model.setViewName("Admin/Adminlevel");
			model.addObject("level",i);
			return model;
		}else{
			out.print("查询失败");
			return null;
		}
	}
	@RequestMapping(value="/governolupdate",method=RequestMethod.POST)
	public String governolupdate(Integer id,String pwd,HttpServletResponse response,HttpSession session) throws IOException{
		PrintWriter out =response.getWriter();
		Integer governor = (Integer) session.getAttribute("adminname");
		int leave = ser.level(governor);
		if(leave>=4){
			boolean i=ser.governolupdate(id, pwd);
			if(i){
				return "Admin/Ascuccess";
			}else{
				out.print("账号不存在");
				return null;
			}
		}else{
			out.print("权限不够");
			logger.error("权限不够");
			return null;
		}
	}
}
