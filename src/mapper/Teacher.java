package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.Studydata;
import pojo.TC;
import pojo.scope;
import pojo.teacher;

public interface Teacher {
	public void Tinsert(teacher ter);

	public teacher SelectTeacher(Integer teacherid);
	
	public teacher selectlogin(Integer teacherid,String pwd);
	
	public void updatesal(Integer teacherid,Integer sal);
	
	public List<teacher> selectall(Integer startindex,Integer pagesize);
	
	public Integer selectcount();
	
	public void Delete(Integer teacherid);
	
	public void updatepwd(teacher ter);
	
	//public void Terchangepwd(Integer teacherid,String pwd);
	
	public void Teacherupdate(@Param("teacherid") Integer teacherid,@Param("pwd") String pwd,@Param("pwd1") String pwd1);
	
	public List<TC> SelectCourse(Integer teacherid);
	
	public void TercherAddScope(scope sc);
	
	public List<teacher> SelectTid();
	
	public List<teacher> TeacherConditional(@Param("teacherid")Integer teacherid,@Param("tname")String tname,@Param("sal")Integer sal,@Param("major")String major,@Param("start")Integer start,@Param("end")Integer end);//条件查询
	
	public Integer CountConditional(teacher te);
	
	public String CheckPwd(Integer teacherid);
	
	public void updatephone(@Param("teacherid")Integer teacherid,@Param("phone")Long phone);
	
	public void updateqq(@Param("teacherid")Integer teacherid,@Param("QQ")Long QQ);
	
	public void updatephoto(Integer teacherid,String photo);
	
	public String selectphoto(Integer teacherid);
	
/*	public void updatefaceid(String faceid,Integer teacherid);*/
	
	public void addstudydata(Studydata sty);
}
