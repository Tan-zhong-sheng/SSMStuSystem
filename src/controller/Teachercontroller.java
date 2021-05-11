package controller;

import java.io.File;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_imgcodecs;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import pojo.SelectScope;
import pojo.Studydata;
import pojo.TC;
import pojo.scope;
import pojo.teacher;

import Mytool.FaceSearch;
import Mytool.Faceadd;
import Mytool.Facedelete;
import Mytool.Mytool;
import Service.ClassesService;
import Service.Servicetzs;
import Service.TcService;
import Service.TeacherService;
@Controller
@SessionAttributes(value={"Tmain","Terinfo","TeacherCourseInfo","id","teacherid"})
public class Teachercontroller {
	
	@Autowired
	private Servicetzs ser;
	
	@Autowired
	private TeacherService ter;
	
	@Autowired
	private TcService t;
	
	@Autowired
	private TC tg;
	
	@Autowired
	private Studydata sty;
	
	@Autowired
	private ClassesService cs;
	static Logger logger = Logger.getLogger(Teachercontroller.class);
	
	static OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
	
	@RequestMapping(value="/Tinsert",method=RequestMethod.POST)
	public String Tinsert(teacher Ter){
		String Pwd = Mytool.GetMd5(Ter.getTpwd());
		Ter.setTpwd(Pwd);
		boolean i = ter.Tinsert(Ter);
		if(i){
			return "redirect:/Tselectall";
		}else{
			return "Teacher/error";
		}
	}
	@RequestMapping(value="/SelectTeacher",method=RequestMethod.POST)
	public ModelAndView SelectTeacher(Integer teacherid){
		ModelAndView model = new ModelAndView();
		teacher Ter = ter.SelectTeacher(teacherid);
		if(Ter!=null){
			model.setViewName("Teacher/result");
			model.addObject("TeacherResult", Ter);
			return model;
		}else{
			model.setViewName("Teacher/selecterror");
			return model;
		}
	}
	@RequestMapping(value="/updatesal",method=RequestMethod.POST)
	public String updatesal(HttpSession session,Integer sal,HttpServletResponse response){
		try {
			PrintWriter out =response.getWriter();
			Integer teacherid = (Integer) session.getAttribute("teacherid");
			boolean i = ter.updatesal(teacherid, sal);
			if(i){
				return "redirect:/Tselectall";
			}else{
				out.print("查询账号不存在");
				return null;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	@RequestMapping(value="/Tselectall")
	public ModelAndView selectall(HttpServletRequest request){
		try {
			ModelAndView model = new ModelAndView();
		  	String currentPage=request.getParameter("pageindex");
		  	currentPage = Mytool.pageindex(currentPage);
		  	Integer pageindex=Integer.parseInt(currentPage);
		  	Integer count=ter.teachercount();
		  	Integer total = (count - (count%Mytool.end ))/Mytool.end ; //总页数
			total = Mytool.totalsize(count, total);
			pageindex = Mytool.pagesize(pageindex, total);
	        Integer start=(pageindex-1)*Mytool.end ;
	        List<teacher> list=new ArrayList<teacher>();
	        list=ter.teacherall(start, Mytool.end);
	        if(list!=null){
		        model.setViewName("Teacher/selectall");
		        model.addObject("Tmain", list);
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
	@RequestMapping(value="/deleteteacher")
	public String deleteCourse(HttpServletRequest request,HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		String id = request.getParameter("teacher");
		Integer teacherid = Mytool.turn(id);
		boolean i=ter.deleteteacher(teacherid);
		if(i){
			out.print("删除成功");
			return "redirect:/Tselectall";
		}
		else{
			return "error";
		}
	}
	@RequestMapping(value="/TerResetpwd")
	public String TerResetpwd(HttpServletRequest request) throws IOException{//重置教师密码
  		String teacherid = request.getParameter("teacherid");
  		Integer Teahcerid = Mytool.turn(teacherid);
  		teacher Ter = new teacher();
  		Ter.setTeacherid(Teahcerid);
  		Ter.setTpwd("");
  		boolean i = this.ter.Terupdatepwd(Ter);
  		if(i){
  			return "redirect:/Tselectall";
  		}else{
  			return "Teacher/error";
  		}
	}
	@RequestMapping("/TeacherInfo")
	public ModelAndView selectteacher(HttpSession session,HttpServletResponse response) throws IOException{
		ModelAndView model = new ModelAndView();
		teacher Ter = (teacher)session.getAttribute("teacherid");
		Integer teacherid = Ter.getTeacherid();
		PrintWriter out = response.getWriter();
		teacher ter= this.ter.SelectTeacher(teacherid);
		if(ter!=null){
			model.setViewName("Teacher/Information");
			model.addObject("Terinfo", ter);
			return model;
		}else{
			out.print("查询不存在");
			return null;
		}
	}
/*	@RequestMapping(value="/Terchangepwd",method=RequestMethod.POST)
	public String Terchangepwd(HttpSession session,String tpwd,HttpServletResponse response) throws IOException{
		Integer teacherid = (Integer)session.getAttribute("id");
		PrintWriter out = response.getWriter();
		String Tpwd = Mytool.GetMd5(tpwd);
		boolean i = ter.Terchangepwd(teacherid, Tpwd);
		if(i){
			out.print("修改成功");
			return null;
		}else{
			out.print("修改失败");
			return null;
		}
	}*/
	@RequestMapping(value="/Teacherupdate",method=RequestMethod.POST)
	public String Teacherupdate(String pwd,String pwd1,HttpSession session,HttpServletResponse response) throws IOException{
		teacher Ter = (teacher)session.getAttribute("teacherid");
		Integer teacherid = Ter.getTeacherid();
		String tpwd = ter.CheckPwd(teacherid);
		PrintWriter out = response.getWriter();
		String Pwd = Mytool.GetMd5(pwd);
		String Pwd1 = Mytool.GetMd5(pwd1);
		if(tpwd.equals(Pwd)){
			boolean i =ter.Teacherupdate(teacherid, Pwd, Pwd1);
			if(i){
				out.print("修改成功");
			}else{
				out.print("修改失败");
			}
		}else{
			out.print("原密码不对");
		}
		out.close();
		return null;
	}
	@RequestMapping("/SelectCourse")
	public ModelAndView SelectCourse(HttpSession session){
		ModelAndView model = new ModelAndView();
		Integer teacherid = (Integer) session.getAttribute("id");
		List<TC> list = ter.SelectCourse(teacherid);
		if(list!=null){
			model.setViewName("Teacher/SelectCourse");
			model.addObject("TeacherCourseInfo", list);
			return model;
		}else{
			model.setViewName("Student/SelectError");
			return model;
		}
	}
	@RequestMapping(value="/TeacherAddScope",method=RequestMethod.POST)
	public String TercherAddScope(scope sc,HttpSession session,HttpServletResponse res) throws IOException{
		 PrintWriter out = res.getWriter();
		 Integer teacherid = (Integer) session.getAttribute("id");
		 Integer Cid = (Integer) session.getAttribute("Teacid");
		 sc.setCid(Cid);
		 sc.setTeacherid(teacherid);
		 boolean i = ter.TercherAddScope(sc);
		 if(i){
			 out.print("添加成功");
			 out.print("<a href=Tselectall>返回首页</a>");
			 return null;
		 }else{
			 out.print("添加失败");
			 return null;
		 }
	}
	@RequestMapping(value="/TeacherConditional",method=RequestMethod.POST)
	public ModelAndView TeacherConditional(HttpServletRequest request,String major,Integer teacher,String tname,Integer sal){
		try {
			ModelAndView model = new ModelAndView();
		  	String currentPage=request.getParameter("pageindex");
		  	currentPage = Mytool.pageindex(currentPage);
		  	Integer pageindex=Integer.parseInt(currentPage);
		  	teacher Ter = new teacher();
		  	Ter.setTname(tname);
		  	//String major1 = new String(major.getBytes("ISO-8859-1"), "utf-8");
		  	Ter.setMajor(major);
		  	Ter.setSal(sal);
		  	Ter.setTeacherid(teacher);
		  	Integer count=ter.CountConditional(Ter);
		  	Integer total = Mytool.total(count); //总页数
			total = Mytool.totalsize(count, total);
			pageindex = Mytool.pagesize(pageindex, total);
	        Integer start=Mytool.start(pageindex);
	        List<teacher> list=new ArrayList<teacher>();
	        list=ter.TeacherConditional(teacher,tname,sal,major, start, Mytool.end);
	        logger.error("list"+list);
	        if(list!=null){
		        model.setViewName("Teacher/TeacherConditional");
		        model.addObject("TeacherConditional", list);
		        model.addObject("teacher", teacher);
		        model.addObject("tname", tname);
		        model.addObject("sal", sal);
		        model.addObject("major", major);
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
	@RequestMapping(value="Teacherupdatephone",method=RequestMethod.POST)
	public String Teacherupdatephone(HttpSession session,Long phone,HttpServletResponse res) throws IOException{
		PrintWriter out = res.getWriter();
		Integer teacherid = (Integer) session.getAttribute("Teaid");
			boolean i = ter.updatephone(teacherid, phone);
			if(i){
				session.removeAttribute("Teaid");
				return "redirect:/Tselectall";
			}else{
				out.print("<script>alert('修改失败')</script>");
				out.close();
			}
		return null;
	}
	
	@RequestMapping(value="Teacherupdateqq",method=RequestMethod.POST)
	public String Teacherupdateqq(HttpSession session,Long QQ,HttpServletResponse res) throws IOException{
		PrintWriter out = res.getWriter();
		Integer teacherid = (Integer) session.getAttribute("Teacherid");
		boolean i = ter.updateqq(teacherid, QQ);
		if(i){
			session.removeAttribute("Teacherid");
			return "redirect:/Tselectall";
		}else{
			out.print("<script>alert('修改失败')</script>");
			out.close();
		}
		return null;
	}
	
	@RequestMapping(value="Changephone",method=RequestMethod.POST)
	public String Changephone(HttpSession session,Long phone,HttpServletResponse res) throws IOException{
		PrintWriter out = res.getWriter();
		Integer teacherid = (Integer) session.getAttribute("id");
		teacher Ter = (teacher) session.getAttribute("teacherid");
		Ter.setPhone(phone);
		session.setAttribute("teacherid", Ter);
			boolean i = ter.updatephone(teacherid, phone);
			if(i){
				return "redirect:/TeacherInfo";
			}else{
				out.print("<script>alert('修改失败')</script>");
				out.close();
			}
		return null;
	}
	@RequestMapping(value="changeqq",method=RequestMethod.POST)
	public String changeqq(HttpSession session,Long QQ,HttpServletResponse res) throws IOException{
		PrintWriter out = res.getWriter();
		Integer teacherid = (Integer) session.getAttribute("id");
		boolean i = ter.updateqq(teacherid, QQ);
		if(i){
			return "redirect:/TeacherInfo";
		}else{
			out.print("<script> alert('修改失败') </script>");
			out.close();
		}
		return null;
	}
	@RequestMapping(value="/uploadface",method=RequestMethod.POST)
	public String uploadface(@RequestParam(value ="file") MultipartFile file,HttpServletResponse res,HttpSession session) throws IOException{
		PrintWriter out = res.getWriter();
		if (!file.isEmpty()) {
				Integer teacherid = (Integer) session.getAttribute("teacherid");
				teacher Ter = ter.SelectTeacher(teacherid);
				//String path = request.getServletContext().getRealPath("/teacher/");
				String path = "D:\\photo\\";
				String filename = "teacher"+file.getOriginalFilename();//初始名
				File f = new File(path);
				if(!f.exists()){
					f.mkdir();
				}
				try {
					String Filepath = path+filename;
					file.transferTo(new File(Filepath));
					if(Ter.getPhoto()!=null){
						Facedelete.delete(Ter.getTeacherid().toString());
					}
					boolean i =ter.updatephoto(teacherid, filename);
					if(i){
						String result = Faceadd.add(filename,Ter.getTeacherid().toString());//注册人脸
						if(result!=null){
							JSONObject js = new JSONObject(result);
							Iterator<String> it =js.keys();//获取值
							int h = 0;
							String value=null;
							while(it.hasNext()){
								String a = it.next();
								Object value1 = js.get(a);//遍历获取的值
								String value2 = value1.toString();
								/*if(value2.length()>120){
									JSONObject j = new JSONObject(value2);//face数组
									Iterator<String> faceid = j.keys();// 遍历face数组
									while (faceid.hasNext()) {
										String l = faceid.next();//把face数组值给他
										String face = j.get(l).toString();//获得值
										ter.updatefaceid(face, teacherid);//添加faceid
										break;
										//logger.error("face"+face);
									}
								}*/
								/*logger.error("value长度="+value2.length());
								logger.error("value内容="+value2);*/
								h++;
								if(h==5){
									value=value2;
									break;
								}
							}
							/*logger.error(value);*/
							switch (value) {
								case "0":
									out.print("<script> alert('上传成功') </script>");
									out.close();
									break;
								case "222203" :
									out.print("<script> alert('无法识别人脸') </script>");
									out.close();
									break;
								case "223105" :
									out.print("<script> alert('人脸已存在') </script>");
									out.close();
									break;
								case "223114" :
									out.print("<script> alert('人脸模糊') </script>");
									out.close();
									break;
								default:
									out.print("<script> alert('上传失败') </script>");
									out.close();
									break;
								}
							return null;
						}else{
							out.print("<script> alert('上传失败') </script>");
							out.close();
							return null;
						}
					}else{
						out.print("<script> alert('上传失败') </script>");
						out.close();
						return null;
					}
				} catch (Exception e) {
					logger.error(e.getMessage());
					out.close();
				}
				finally{
					out.close();
				}
				return null;
			} else {
				out.print("上传失败");
				out.close();
				return null;
		}
	}
	
	@RequestMapping(value="/faceverify")
	public ModelAndView faceverify(HttpServletResponse res,HttpSession session) throws IOException, InterruptedException{
		try {
			ModelAndView model = new ModelAndView();
			teacher TEA = (teacher) session.getAttribute("teacherid");
			if(TEA!=null){
				model.setViewName("Teacher/main");
				//model.addObject("teacherid", TEA);
				return model;
			}
			PrintWriter out = res.getWriter();
			File f = new File("d:\\img\\");
			if(!f.exists()){
				f.mkdir();
			}
		    OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
		    grabber.setImageHeight(1500);//框架高度
		    grabber.setImageWidth(1500);//框框宽
		    grabber.start();   //开始获取摄像头数据
		    CanvasFrame canvas = new CanvasFrame("摄像头");//新建一个窗口
		    /*canvas.setDefaultCloseOperation();*/
		    canvas.setAlwaysOnTop(true);
		    int ex = 0;
		    while(true)
		    {
		        if(!canvas.isDisplayable())
		        {//窗口是否关闭
		            grabber.stop();//停止抓取
		            System.exit(2);//退出
		            break;
		        }       
		        canvas.showImage(grabber.grab());//获取摄像头图像并放到窗口上显示， 这里的Frame frame=grabber.grab(); frame是一帧视频图像
		        opencv_core.Mat mat = converter.convertToMat(grabber.grabFrame());
		        ex++;
		        opencv_imgcodecs.imwrite("d:\\img\\" + ex + ".png", mat);
		        Thread.sleep(200);//50毫秒刷新一次图像
		        mat.clone();
		        if(canvas.isDisplayable()){
		        	String result = FaceSearch.search("d:\\img\\" + ex + ".png");
		    		if(result!=null){
		    			JSONObject js = new JSONObject(result);//第一次值
		    			Iterator<String> it = js.keys();
		    			int check = 0;//检查数
		    			int check1=0;
		    			while (it.hasNext()) {
		    				String a = it.next();
		    				String value = js.get(a).toString();
		    				/*logger.error("value长度"+value.length());
		    				logger.error("value"+value);*/
		    				if(value.length()>100){//如果长度大于100
		    					JSONObject js1 = new JSONObject(value);//第二次值
		    					Iterator<String> it1 = js1.keys();
		    					while(it1.hasNext()){
		    						String a1 = it1.next();
		    						String value1 = js1.get(a1).toString();
		    						//logger.error(value1);
		    						check++;
		    						if(check==2){
		    							value1 = value1.substring(1,value1.length()-1);//截取字符去掉[]
		    							//logger.error(value1);
		    							JSONObject j = new JSONObject(value1);
		    							Iterator<String> h = j.keys();
		    							while(h.hasNext()){
		    								String a2 = h.next();
		    								String result1 = j.get(a2).toString();
		    								check1++;
		    								if(check1==3){
		    									Integer tid = Mytool.turn(result1);
		    									if(tid!=null){
		    										teacher TER = ter.SelectTeacher(tid);
		    										model.addObject("id", TER.getTeacherid());
		    										model.setViewName("forward:Teacher/main.jsp");
		    										model.addObject("teacherid", TER);
		    										f.delete();
		    										//canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭窗口则退出程序
		    										grabber.stop();
		    										grabber.close();
		    										canvas.dispose();//摧毁window摄像头
		    										return model;
		    									}else{
		    										out.print("<script> alert('登陆失败')</script>");
		    									}
		    								}
		    							}
		    						}
		    					}
		    				}
		    			}
		    		}
		        }
		    }
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	@RequestMapping(value="/updatedata",method=RequestMethod.POST)
	public String updatedata(@RequestParam(value ="file") MultipartFile file,HttpSession session,HttpServletResponse res) throws IOException{
		if (!file.isEmpty()) {
			PrintWriter out = res.getWriter();
			Integer teacherid = (Integer) session.getAttribute("id");
			String path = "D:\\data\\";
			String filename = file.getOriginalFilename();
			File f = new File(path);
			if(!f.exists()){
				f.mkdir();
			}
			try {
				sty.setTeacherid(teacherid);
				String Filepath = path+filename;
				sty.setAddress(Filepath);
				sty.setFilename(filename);
				boolean i =ter.addstudydata(sty);
				if(i){
					out.print("<script>alert('上传成功')</script>");
					file.transferTo(new File(Filepath));
					return null;
				}else{
					out.print("<script>alert('上传失败')</script>");
					return null;
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}finally{
				out.close();
			}
			return "Teacher/main";
		} else {
			return null;
		}
	}
	@RequestMapping("/Exportscope")
	public String Export(HttpServletResponse response,Integer classesid,Integer cid) throws IOException{//导出成绩为Excel表
		List<SelectScope> list = cs.selectClassesnumber(classesid, cid);
		HSSFWorkbook wb = ter.Exportscope(list);
		response.setContentType("application/vnd.ms-excel;charset=utf-8");    
        response.setHeader("Content-disposition", "attachment;filename="+UUID.randomUUID()+"成绩表.xlsx");
		OutputStream out = response.getOutputStream();
		wb.write(out);
		out.flush();
		out.close();
		wb.close();
        return null;
	}
}