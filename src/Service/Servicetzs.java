package Service;

import java.util.Iterator;

import java.util.List;







import mapper.*;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import pojo.*;

@Service
public class Servicetzs {
	@Autowired
	private mapper mp;
	@Autowired
	private Student stu;
	@Autowired
	private Course cou;
	@Autowired
	private Scope sc;
	static Logger logger = Logger.getLogger(Servicetzs.class);
	public student login(Integer id,String pwd){//学生登入
		student User=stu.login(id,pwd);
		if(User!=null){
			System.out.println(User);
			return User;
		}else{
			return null;
		}
	}

	public student Ajax(Integer name){
		student User = stu.Ajax(name);
		if(User!=null){
			return User;
		}else{
			return null;
		}
	}
	public boolean governorLogin(Integer id,String pwd){//管理员登陆
		governor go=mp.governorLogin(id, pwd);
		if(go!=null){
			System.out.println(go);
			return true;
		}else{
			return false;
		}
	} 

	public boolean Delete(Integer stuid){//删除学生
		try {
			stu.Delete(stuid);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}
	public Integer level(Integer governorid){//查看等级
		Integer i= mp.selectleave(governorid);
			return i;
	}
	public boolean governolupdate(Integer governorid,String pwd){//管理修改密码
		try {
			mp.governolupdate(governorid, pwd);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}
	public boolean Cinsert(course cour){//课程添加
		try {
			cou.Cinsert(cour);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}
	public course Cselect(Integer cid,String ClassRoom,String major){//课程查询
		try {
			return cou.Cselect(cid,ClassRoom,major);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	public scope Scopeselect(Integer stuid){//成绩查询
		try {
			return sc.select(stuid);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	public boolean InsertScope(scope SC){//添加成绩
		try {
			sc.InsertScope(SC);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
		
	}

	public List<course> Cselectall(Integer startindex,Integer pagesize ){//查询所有课程
		try {
			return cou.selectall(startindex,pagesize);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	public Integer selectcount(){//查询课程数量
		try {
			return cou.selectcount();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	public boolean  deleteCourse(Integer cid){//删除课程
		try {
			cou.deleteCourse(cid);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}

	public List<scope> scopeall(Integer startindex,Integer pagesize ){//分页学生
		try {
			return sc.selectall(startindex, pagesize);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	public Integer scopecount(){//查询学生总数
		try {
			return sc.selectcount();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	public boolean deletescope(Integer stuid,Integer cid ){//删除成绩
		try {
			sc.Delete(stuid,cid);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}
	public boolean updateSc(Integer stuid,Integer scope,Integer cid){
		try {
			sc.updateSc(stuid, scope,cid);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}
	public boolean ResetUser(student User){//重置学生用户
		try {
			stu.ResetUser(User);
			logger.debug(User);
			logger.debug("修改成功");
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.debug("修改失败");
			return false;
		}
	}
	
	public HSSFWorkbook Export(List<scope> list){//导出成绩为excel文件
		String[] name = {"学号 ","课程名", "负责老师" ," 成绩 "};
		//String[] name1 = {"1 ","C", "Tzs" ,"One"," 90 "};
		HSSFWorkbook ha = new HSSFWorkbook();
		HSSFSheet sheet = ha.createSheet("学生表");
		HSSFRow row = sheet.createRow(0);
		HSSFCellStyle style = ha.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		for(int i = 0;i<name.length; i++){
			row.createCell(i).setCellStyle(style);
			row.createCell(i).setCellValue(name[i]);
			row.setRowStyle(style);
		}
		Iterator<scope> list1 =list.iterator();
		while(list1.hasNext()){
			scope sc = list1.next();
			row = sheet.createRow(row.getRowNum()+1);
			row.createCell(0).setCellValue(sc.getStudent().getStuid());
			row.createCell(1).setCellValue(sc.getCourse().getCname());
			row.createCell(2).setCellValue(sc.getTeacherid());
			row.createCell(3).setCellValue(sc.getScope());
		}
			return ha;
	}
	public List<scope> ExportScope(){//查询所有成绩
		try {
			return sc.ExportScope();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	public List<scope> choiseselect(Integer stuid,Integer teacherid,Integer cid,Integer scope,Integer scope1,String grade,Integer start,Integer end){
		try {
			return sc.choiseselect(stuid, teacherid, cid, scope, scope1, grade,start,end);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	public Integer choisecount(Integer stuid,Integer teacherid,Integer cid,Integer scope,Integer scope1,String grade){
		try {
			return sc.choisecount(stuid, teacherid, cid, scope, scope1, grade);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	public scope selectscope(Integer cid,Integer stuid){//查询是否有数据
		try {
			return sc.selectscope(cid, stuid);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	public boolean updateteacherscope(Integer cid,Integer stuid,Integer scope){
		try {
			sc.updateteacherscope(cid, stuid, scope);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}
	public boolean updatescopestatus(Integer stuid,Integer cid){
		try {
			sc.updatescopestatus(stuid, cid);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}
	public photoverification Code(Integer pid){
		try {
			return mp.Code(pid);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	public Integer passnum(Integer classid,Integer cid){
		try {
			return sc.passnum(classid, cid);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	public Integer nopassnum(Integer classid,Integer cid){
		try {
			return sc.nopassnum(classid, cid);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
}