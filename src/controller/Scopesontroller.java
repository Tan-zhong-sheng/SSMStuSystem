package controller;



import java.io.IOException;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import pojo.course;
import pojo.scope;
import pojo.student;
import pojo.teacher;

import Mytool.Mytool;
import Service.CourseService;
import Service.Servicetzs;
import Service.StudentService;
import Service.TeacherService;

@Controller
@SessionAttributes(value={"Scmain","Scopecid"})
public class Scopesontroller {
	@Autowired
	private Servicetzs ser;
	@Autowired
	private TeacherService ter;
	@Autowired
	private StudentService stu;
	@Autowired
	private CourseService cou;
	static Logger logger = Logger.getLogger(Scopesontroller.class);
	@RequestMapping("/Scopesontroller")
	public ModelAndView Scselect(HttpServletRequest request){//查询成绩
			try {
				ModelAndView model = new ModelAndView();
			  	String currentPage=request.getParameter("pageindex");
			  	currentPage = Mytool.pageindex(currentPage);
			  	Integer pageindex=Integer.parseInt(currentPage);
			  	Integer count=ser.scopecount();
			  	Integer total = Mytool.total(count);  //总页数
			  	total = Mytool.totalsize(count, total);
				pageindex = Mytool.pagesize(pageindex, total);
		        Integer start= Mytool.start(pageindex);
		        List<scope> list=new ArrayList<scope>();
		        list=ser.scopeall(start, Mytool.end);
		        if(list!=null){
			        model.setViewName("Scope/Scopeselectall");
			        model.addObject("Scmain", list);
			        model.addObject("total", total);
			        model.addObject("pageindex", pageindex);
			        model.addObject("count", count);
			        return model;
		        }
			   else
				   model.setViewName("Scope/SelectError");
				   return model;
			} catch (Exception e) {
				logger.error(e.getMessage());
				return null;
			}
		}
	@RequestMapping(value="/deletescope")
	public String deletescope(HttpServletRequest request) throws IOException{//删除成绩
		String id = request.getParameter("stuid");
		String cid = request.getParameter("cid");
		Integer stuid = Mytool.turn(id);
		Integer Cid = Mytool.turn(cid);
		boolean i=ser.deletescope(stuid,Cid);
		if(i){
			return "redirect:/Scopesontroller";
		}
		else{
			return "Scope/error";
		}
	}
	@RequestMapping(value="/UpdateSC",method=RequestMethod.POST)
	public String UpdateSC(HttpSession session,Integer scope) throws IOException{//修改成绩
		Integer stuid = (Integer)session.getAttribute("ScStuid");
		Integer cid = (Integer)session.getAttribute("Sccid");
		boolean i =ser.updateSc(stuid, scope,cid);
		if(i){
			return "redirect:/Scopesontroller";
		}else{
			return "error";
		}
	}
	@RequestMapping("/Export")
	public String Export(HttpServletResponse response) throws IOException{//导出成绩为Excel表
		List<scope> list = ser.ExportScope();
		HSSFWorkbook wb = ser.Export(list);
		response.setContentType("application/vnd.ms-excel;charset=utf-8");    
        response.setHeader("Content-disposition", "attachment;filename="+UUID.randomUUID()+"成绩表.xlsx");
		OutputStream out = response.getOutputStream();
		wb.write(out);
		out.flush();
		out.close();
		wb.close();
        return null;
	}
	@RequestMapping("/AddRecord")
	public ModelAndView AddRecord(){
		ModelAndView model = new ModelAndView();
		List<course> Cid = cou.SelectCid();
		List<student> Stuid = stu.SelcetStuid();
		List<teacher> Tid =  ter.SelectTid();
		model.setViewName("Scope/InsertScope");
		if(Cid!=null){
			model.addObject("Cid", Cid);
		}else{
			model.addObject("Cid", "课程信息为空");
		}
		if(Stuid!=null){
			model.addObject("Stuid", Stuid);
		}else{
			model.addObject("Stuid", "学号为空");
		}
		if(Tid!=null){
			model.addObject("Tid", Tid);
		}else{
			model.addObject("Tid", "教师为空");
		}
		return model;
		
	}
	@RequestMapping("/selecgrade")
	@ResponseBody
	public String selecgrade(String stuid){
		try {
			Integer Stuid = Mytool.turn(stuid);
			student Stu = ser.Ajax(Stuid);
			return Stu.getGrade();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	@RequestMapping(value="/choiseselect",method=RequestMethod.GET)
	public ModelAndView choiseselect(Integer stuid,Integer teacherid,Integer cid,Integer scope,Integer scope1,String grade,HttpServletRequest request){
		ModelAndView model = new ModelAndView();
		try {
		  	String currentPage=request.getParameter("pageindex");
		  	currentPage = Mytool.pageindex(currentPage);
		  	Integer pageindex=Integer.parseInt(currentPage);
		  	Integer count=ser.choisecount(stuid, teacherid, cid, scope, scope1, grade);
		  	Integer total = Mytool.total(count);  //总页数
		  	total = Mytool.totalsize(count, total);
			pageindex = Mytool.pagesize(pageindex, total);
	        Integer start= Mytool.start(pageindex);
	        List<scope> list=new ArrayList<scope>();
	        list=ser.choiseselect(stuid, teacherid, cid, scope, scope1, grade, start, Mytool.end);
	        if(list!=null){
		        model.setViewName("Scope/Choiceselectresult");
		        model.addObject("stuid", stuid);
		        model.addObject("teacherid", teacherid);
		        model.addObject("scope1", scope1);
		        model.addObject("scope", scope);
		        model.addObject("grade", grade);
		        model.addObject("cid", cid);
		        model.addObject("list", list);
		        model.addObject("total", total);
		        model.addObject("pageindex", pageindex);
		        model.addObject("count", count);
		        return model;
	        }
		   else
			   model.setViewName("Scope/SelectError");
			   return model;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	@RequestMapping(value="/checkstuid",method=RequestMethod.POST)
	@ResponseBody
	public boolean checkstuid(String stuid){
		Integer Stuid = Mytool.turn(stuid);
		scope s = ser.Scopeselect(Stuid);
		if(s!=null){
			return true;
		}else{
			return false;
		}
	}
	@RequestMapping("/selectclass")
	public ModelAndView selectclass(HttpServletRequest request){
		ModelAndView model = new ModelAndView();
		String id = request.getParameter("classid");
		String cid = request.getParameter("cid");
		Integer Scopecid = Mytool.turn(cid);
		Integer classid = Mytool.turn(id);
		List<student> list = stu.selectclass(classid);
		if(list!=null){
			model.setViewName("Teacher/addscope");
			model.addObject("list", list);
			model.addObject("Scopecid", Scopecid);
			return model;
		}else{
			model.setViewName("Teacher/selecterror.jsp");
			return model;
		}
	}
	@RequestMapping("/addteracherscope")
	@ResponseBody
	public boolean addteracherscope(Integer[] sc,Integer[] st,HttpSession session,Integer[] status){
		try {
			//0为舞弊，2为缺考，5为教师提交不得在更改
			int s;//成绩
			int s1;//学号
			int s2;//状态
			String grade;//成绩
			scope ss = new scope();
			Integer teacherid = (Integer) session.getAttribute("id");//教师号
			Integer Cid = (Integer) session.getAttribute("Scopecid");
			for(int i=0;i<sc.length; i++){
				s=sc[i];
				s1=st[i];
				s2=status[i];
				scope Sc = ser.selectscope(Cid, s1);//查询是否有数据
				if(Sc==null){//如果为空则插入
					student Stu = ser.Ajax(s1);
					grade = Stu.getGrade();
					ss.setCid(Cid);
					ss.setGrade(grade);
					ss.setScope(s);
					ss.setStuid(s1);
					ss.setTeacherid(teacherid);
					ss.setStatus(s2);
					if(status[i].equals(0)||status[i].equals(2)){//如果为舞弊或者缺考则为0
						ss.setScope(0);
					}
					boolean o = ser.InsertScope(ss);
					if(o==false){
						return false;
					}
				}else{
					if(Sc.getStatus().equals("5")){//如果以提交则不能修改
						return false;
					}else{//如果不是这可以修改
						if(status[i].equals(0)||status[i].equals(2)){//如果为舞弊或者缺考则为0
							s=0;
						}
						boolean f = ser.updateteacherscope(Cid, s1, s);
						if(f==false){
							return false;
						}
					}
				}
			}
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return false;
	}
	@RequestMapping("/updatescopestatus")
	@ResponseBody
	public boolean updatescopestatus(Integer[] sc,HttpSession session){
		try {
			int s;
			System.out.println("sc"+sc);
			Integer Cid = (Integer) session.getAttribute("Scopecid");
			for(int i =0;i<sc.length;i++){
				s=sc[i];
				boolean j = ser.updatescopestatus(s, Cid);
				if(j==false){
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}

	}
	
	@RequestMapping(value="/InsertScope",method=RequestMethod.POST)
	public String InsertScope(scope sc){//添加成绩
		student Stu = ser.Ajax(sc.getStuid());
		sc.setGrade(Stu.getGrade());
		if(sc.getStatus().equals(0)||sc.getStatus().equals(2)){
			sc.setScope(0);
		}
		boolean i =ser.InsertScope(sc);
		try {
			if(i)
			  return "redirect:/Scopesontroller";
			else
				return "Scope/error";
		} catch (Exception e) {
			logger.error(e.getMessage());
			return "Scope/error";
		}
	}
	@RequestMapping(value="/scopechart",method=RequestMethod.POST)
	@ResponseBody
	public String scopechart(Integer classid,Integer cid){
		Integer passnum = ser.passnum(classid, cid);
		Integer nopassnum = ser.nopassnum(classid, cid);
		Map<String, Integer> map = new HashMap<String,Integer>();
		map.put("passnum", passnum);
		map.put("nopassnum", nopassnum);
		JSONObject js = new JSONObject(map);
		String result = js.toString();
		//logger.error(result);
		return result;
	}
	@RequestMapping(value="/selectstatus",method=RequestMethod.POST)
	@ResponseBody
	public boolean selectstatus(int cid,HttpSession session){
		Integer stuid = (Integer) session.getAttribute("name");
		scope issucess = ser.selectscope(cid, stuid);
		if(issucess!=null){
			if(issucess.getStatus()==5){
				return false;
			}else{
				return true;
			}
		}
		return true;
	}
}
