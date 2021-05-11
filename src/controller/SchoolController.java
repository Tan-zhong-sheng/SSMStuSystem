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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import pojo.SchoolRoom;
import Mytool.Mytool;
import Service.SchoolRoomService;

@Controller
@SessionAttributes(value={"SchoolRoom"})
public class SchoolController {
	@Autowired
	private SchoolRoomService sc;
	
	static Logger logger = Logger.getLogger(SchoolController.class);
	@RequestMapping(value="/Scinsert",method=RequestMethod.POST)
	public String Scinsert(SchoolRoom room,HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		boolean i = sc.Scinsert(room);
		if(i){
			return "redirect:/SelectSchoolRoom";
		}else{
			out.print("添加失败");
		}
		return null;
	}
	
	@RequestMapping(value="/SelectSchoolRoom")
	public ModelAndView selectall(HttpServletRequest request){
		try {
			ModelAndView model = new ModelAndView();
		  	String currentPage=request.getParameter("pageindex");
		  	if(currentPage==null){
		  		currentPage="1";
		  	} 
		  	Integer pagesize=5;
		  	Integer pageindex=Integer.parseInt(currentPage);
		  	Integer count=sc.Count();
		  	Integer total = (count - (count%pagesize))/pagesize; //总页数
			if(count % pagesize != 0){
				total++;
		     }
			try {
				if(pageindex>total){
					pageindex = total;
				}else if(pageindex<=0){
					pageindex=1;
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
	        Integer start=(pageindex-1)*pagesize;
	        List<SchoolRoom> list=new ArrayList<SchoolRoom>();
	        list=sc.SelectSchoolRoom(start, Mytool.end);
	        if(list!=null){
		        model.setViewName("SchoolRoom/SelectRoom");
		        model.addObject("SchoolRoom", list);
		        model.addObject("total", total);
		        model.addObject("pageindex", pageindex);
		        model.addObject("count", count);
		        return model;
	        }else{
	        	model.setViewName("Teacher/selecterror");
	        	return model;
	        }
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	@RequestMapping("/DeleteSchoolRoom")
	public String DeleteSchoolRoom(HttpServletRequest request,HttpServletResponse response){
		try {
			PrintWriter out = response.getWriter();
			String name = request.getParameter("scid");
			Integer Scid = Mytool.turn(name);
			boolean i=sc.DeleteSchoolRoom(Scid);
			if(i){
				return "redirect:/SelectSchoolRoom";
			}else{
				out.print("删除失败");
				return null;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	@RequestMapping("/UpdateSchoolRoom")
	public String UpdateSchoolRoom(HttpSession session,String Management){
		try {
			Integer Scid = (Integer) session.getAttribute("Scid");
			String Management1 = new String(Management.getBytes("ISO-8859-1"), "utf-8");
			sc.UpdateSchoolRoom(Scid, Management1);
			return "redirect:/SelectSchoolRoom";
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
}
