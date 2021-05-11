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
import org.springframework.web.servlet.ModelAndView;

import pojo.TC;
import pojo.teacher;

import Mytool.Mytool;
import Service.TcService;

@Controller
public class TcController {
	@Autowired
	private TcService tc;
	static Logger logger = Logger.getLogger(TcController.class);
	
	@RequestMapping(value="/distribution",method=RequestMethod.POST)
	public String distribution(TC c,HttpServletResponse response,HttpSession session) throws IOException{
		PrintWriter out = response.getWriter();
		try {
			Integer Cid = (Integer) session.getAttribute("CID");
			c.setCid(Cid);
			boolean i = tc.distribution(c);
			if(i){
				return "redirect:/Cselectall";
			}else{
				out.print("<script>alert('分配失败')<script>");
				return null;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}finally{
			out.close();
		}
	}
	@RequestMapping("/distributioncourse")
	public ModelAndView distributioncourse(HttpServletRequest request){
		try {
			ModelAndView model = new ModelAndView();
		  	String currentPage=request.getParameter("pageindex");
		  	currentPage = Mytool.pageindex(currentPage);
		  	Integer pageindex=Integer.parseInt(currentPage);
		  	Integer count=tc.tccount();
		  	logger.error(count);
		  	Integer total = (count - (count%Mytool.end ))/Mytool.end ; //总页数
			total = Mytool.totalsize(count, total);
			pageindex = Mytool.pagesize(pageindex, total);
	        Integer start= Mytool.start(pageindex);
	        List<TC> list=new ArrayList<TC>();
	        list=tc.distributioncourse(start, Mytool.end);
	        logger.error(list);
	        if(list!=null){
		        model.setViewName("Teacher/distributioncourse");
		        model.addObject("distribution", list);
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
	@RequestMapping("/closedistributioncourse")
	public String closedistributioncourse(Integer cid,Integer Classid,Integer teacherid,HttpServletResponse res) throws IOException{
		PrintWriter out = res.getWriter();
		boolean success = tc.closedistributioncourse(cid, Classid, teacherid);
		if(success){
			out.print("<script>alert('取消成功')</script>");
		}else{
			out.print("<script>alert('取消失败')</script>");
		}
		out.close();
		return null;
	}
}
