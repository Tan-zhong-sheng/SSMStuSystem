package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;


import pojo.Studydata;
import pojo.course;
import pojo.student;
import pojo.test;


public interface Student {
	
	public void insert(student user);
	
	public student login(Integer id,String pwd);
	
	public student Ajax(Integer stuid);
	
	public void ResetUser(student stu);
	
	public void Delete(Integer stuid);
	
	public void updatepwd(@Param("stuid")Integer stuid,@Param("pwd")String pwd,@Param("pwd1")String pwd1);
	
	public List<student> selectall(Integer startindex,Integer pagesize);
	
	public Integer selectcount();
	
	public student Studentinformation(Integer stuid);
	
	public void UpdatePhone(Integer stuid,Long phone);
	
	public void updatephoto(@Param("photo")String photo,@Param("stuid") Integer stuid);
	
	public void UpdateGrade(@Param("grade") String grade,@Param("grade1")String grade1,@Param("major")String major);
	
	public String SelectPhoto(Integer stuid);
	
	public List<student> Selectcondition(student stu);
	
	public List<student> SelectCourse();
	
	public List<student> SelectStuid();
	
	public String CheckPwd(Integer stuid);
	
	public List<student> selectclassespeople(Integer Claasid);
	
	public List<student> selectclass(Integer Claasid);
	
	public List<Studydata> selectdata(Integer start,Integer end);
	
	public Integer selectdatecount();
	
	public List<test> test(int cid);
	
}
