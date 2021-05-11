package Service;

import java.util.List;

import mapper.Course;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pojo.Classes;
import pojo.course;
import pojo.student;
import pojo.teacher;
@Service
public class CourseService {
	@Autowired
	private Course cou;
	static Logger logger = Logger.getLogger(CourseService.class);
	
	public List<student> StudentInfo(Integer startindex,Integer pagesize,Integer cname){
		try {
			return cou.SelectStudent(startindex,pagesize,cname);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	public Integer ScstudentCount(Integer Cid){
		try {
			return cou.ScstudentCount(Cid);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	public String Choice(Integer stuid){
		try {
			return cou.Choice(stuid);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	public List<String> SelectStudentCourse(String grade,String major){
		try {
			return cou.SelectStudentCourse(grade,major);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	public List<course> SelectCid(){
		try {
			return cou.SelectCid();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	public List<course> SelectExistingCid(Integer startindex,Integer pagesize,String major){
		try {
			return cou.SelectExistingCid(startindex,pagesize,major);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	public Integer Count(String major){
		try {
			return cou.Count(major);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	public List<Integer> Subject(Integer stuid){
		try {
			return cou.Subject(stuid);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	public List<teacher> selectteacher(String major){//查找专业相同得教师号
		try {
			return cou.selectteacher(major);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	public List<Classes> selectclasses(){//查找专业相同得班级号
		try {
			return cou.selectclass();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
}
