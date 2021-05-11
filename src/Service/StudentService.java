package Service;

import java.util.List;

import mapper.Scope;
import mapper.Student;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pojo.*;

@Service
public class StudentService {
	@Autowired
	private Student stu;
	@Autowired
	private Scope sc;
	static Logger logger = Logger.getLogger(StudentService.class);

	public student Studentinformation(Integer stuid) {
		try {
			return stu.Studentinformation(stuid);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}

	}

	public List<student> studentall(Integer startindex, Integer pagesize) {// 分页学生
		try {
			return stu.selectall(startindex, pagesize);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	public Integer studentcount() {// 查询学生总数
		try {
			return stu.selectcount();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	public boolean register(student user){//添加学生
		try {
			stu.insert(user);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}
	public List<scope> SelectStudentScope(Integer stuid){
		try {
			return sc.SelectStudentScope(stuid);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	public boolean UpdatePhone(Integer stuid,Long phone){
		try {
			stu.UpdatePhone(stuid, phone);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}
	public boolean updatepwd(Integer stuid,String pwd,String pwd1){//学生修改密码
		try {
			stu.updatepwd(stuid, pwd, pwd1);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}
	public boolean UpdateGrade(String grade,String grade1,String major){
		try {
			stu.UpdateGrade(grade,grade1,major);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}
	
	public boolean updatephoto(String photo, Integer stuid){
		try {
			stu.updatephoto(photo, stuid);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}
	
	public String SelectPhoto(Integer stuid){
		try {
			return stu.SelectPhoto(stuid);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	public List<student> Selectcondition(student st){
		try {
			return stu.Selectcondition(st);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	public List<student> StudentCourse(){
		try {
			return stu.SelectCourse();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	public List<student> SelcetStuid(){
		try {
			return stu.SelectStuid();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	public String CheckPwd(Integer stuid){
		try {
			return stu.CheckPwd(stuid);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	public List<student> selectclassespeople(Integer Classid){
		try {
			return stu.selectclassespeople(Classid);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	public List<student> selectclass(Integer classid){
		try {
			return stu.selectclass(classid);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	public List<Studydata> selectdata(Integer start,Integer end){
		try {
			return stu.selectdata(start,end);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	public Integer selectdatecount(){
		try {
			return stu.selectdatecount();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	public List<test> test(int cid){
		try {
			return stu.test(cid);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
}
