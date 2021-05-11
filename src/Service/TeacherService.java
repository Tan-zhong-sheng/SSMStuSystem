package Service;

import java.util.Iterator;
import java.util.List;

import mapper.Scope;
import mapper.Teacher;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pojo.SelectScope;
import pojo.Studydata;
import pojo.TC;
import pojo.scope;
import pojo.teacher;


@Service
public class TeacherService {
	@Autowired
	private Teacher ter;
	static Logger logger = Logger.getLogger(TeacherService.class);
	
/*	public boolean Terchangepwd(Integer teacherid,String pwd){
		try {
			ter.Terchangepwd(teacherid, pwd);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}*/
	public boolean Tinsert(teacher Ter){
		try {
			ter.Tinsert(Ter);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}
	public teacher SelectTeacher(Integer id){
		try {
			teacher Ter = ter.SelectTeacher(id);
			return Ter;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	public teacher Selectlogin(Integer teacherid,String pwd){
		try {
			teacher Ter = ter.selectlogin(teacherid, pwd);
			if(Ter!=null){
				return Ter;
			}else{
				return null;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
			
		}
	}
	public List<teacher> teacherall(Integer startindex,Integer pagesize ){//分页学生
		try {
			return ter.selectall(startindex, pagesize);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	public Integer teachercount(){//查询教师总数
		try {
			return ter.selectcount();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	public boolean deleteteacher(Integer teacherid){
		try {
			ter.Delete(teacherid);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}
	public boolean Terupdatepwd(teacher Ter){
		try {
			ter.updatepwd(Ter);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}
	public boolean updatesal(Integer teacherid,Integer sal){
		try {
			ter.updatesal(teacherid, sal);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}
	public boolean Teacherupdate(Integer teacherid ,String pwd,String pwd1){
		try {
			ter.Teacherupdate(teacherid, pwd, pwd1);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}
	public List<TC> SelectCourse(Integer teacherid){
		try {
			return ter.SelectCourse(teacherid);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	public boolean TercherAddScope(scope sc){
		try {
			 ter.TercherAddScope(sc);
			 return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}
	public List<teacher> SelectTid(){
		try {
			return ter.SelectTid();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	public List<teacher> TeacherConditional(Integer teacherid,String tname,Integer sal,String major,Integer start,Integer end){
		try {
			return ter.TeacherConditional(teacherid,tname,sal,major,start,end);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	public Integer CountConditional(teacher te){
		try {
			return ter.CountConditional(te);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	public String CheckPwd(Integer teacherid){
		try {
			return ter.CheckPwd(teacherid);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	public boolean updatephone(Integer teacherid,Long phone){
		try {
			 ter.updatephone(teacherid, phone);
			 return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}
	public boolean updateqq(Integer teacherid,Long QQ){
		try {
			 ter.updateqq(teacherid, QQ);
			 return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}
	public boolean updatephoto(Integer teacherid,String photo){
		try {
			ter.updatephoto(teacherid, photo);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}
	public String selectphoto(Integer teacherid){
		try {
			return ter.selectphoto(teacherid);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
/*	public void updatefaceid(String faceid,Integer teacherid){
		try {
			ter.updatefaceid(faceid, teacherid);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}*/
	
	public boolean addstudydata(Studydata sty){
		try {
			ter.addstudydata(sty);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}
	public HSSFWorkbook Exportscope(List<SelectScope> list){//导出成绩为excel文件
		String[] name = {"学号 ","姓名", "课程名" ," 成绩 "};
		HSSFWorkbook ha = new HSSFWorkbook();
		HSSFSheet sheet = ha.createSheet("成绩表");//标题名
		HSSFRow row = sheet.createRow(0);//表行
		HSSFCellStyle style = ha.createCellStyle();//样试
		style.setAlignment(HorizontalAlignment.CENTER);
		for(int i = 0;i<name.length; i++){
			row.createCell(i).setCellStyle(style);
			row.createCell(i).setCellValue(name[i]);
			row.setRowStyle(style);
		}
		Iterator<SelectScope> list1 =list.iterator();
		while(list1.hasNext()){
			SelectScope sc = list1.next();
			row = sheet.createRow(row.getRowNum()+1);
			row.createCell(0).setCellValue(sc.getStuid());
			row.createCell(1).setCellValue(sc.getName());
			row.createCell(2).setCellValue(sc.getCname());
			row.createCell(3).setCellValue(sc.getScope());
		}
			return ha;
	}
}

