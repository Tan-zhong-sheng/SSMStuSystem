package mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.Classes;
import pojo.course;
import pojo.student;
import pojo.teacher;

public interface Course {
	public void Cinsert(course cou);
	
	public course Cselect(@Param("cid")Integer cid,@Param("CourseRoom")String CourseRoom,@Param("major")String major);
	
	public List<course> selectall(Integer startindex,Integer pagesize);
	
	public Integer selectcount();
	
	public void deleteCourse(Integer cid);
	
	public List<student> SelectStudent(Integer startindex,Integer pagesize,Integer cname);
	
	public Integer ScstudentCount(Integer Cid);
	
	public String Choice(Integer stuid);
	
	public List<String> SelectStudentCourse(String grade,String major);
	
	public List<course> SelectCid();
	
	public List<course> SelectExistingCid(Integer startindex,Integer pagesize,String major);
	
	public Integer Count(String major);
	
	public List<Integer> Subject(Integer stuid);
	
	public List<teacher> selectteacher(String major);
	
	public List<Classes> selectclass();
}
