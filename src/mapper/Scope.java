package mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.scope;

public interface Scope {
	public scope select(Integer stuid);
	
	public void InsertScope(scope sc);
	
	public List<scope> selectall(Integer startindex,Integer pagesize);
	
	public Integer selectcount();
	
	public void Delete(@Param("stuid")Integer stuid,@Param("cid")Integer cid);
	
	public void updateSc(Integer stuid,Integer scope,Integer cid);
	
	public List<scope> SelectStudentScope(Integer stuid);
	
	public List<scope> ExportScope();
	
	public List<scope> choiseselect(@Param("stuid")Integer stuid
			,@Param("teacherid")Integer teacherid,@Param("cid")Integer cid,@Param("scope")Integer scope,@Param("scope1")Integer scope1,
			@Param("grade")String grade,@Param("start")Integer start,@Param("end")Integer end);
	
	public Integer choisecount(@Param("stuid")Integer stuid
			,@Param("teacherid")Integer teacherid,@Param("cid")Integer cid,@Param("scope")Integer scope,@Param("scope1")Integer scope1,
			@Param("grade")String grade);
	
	public scope selectscope(Integer cid,Integer stuid);//查询成绩表里是否有一样的
	
	public void updateteacherscope(Integer cid,Integer stuid,Integer scope);
	
	public void updatescopestatus(Integer stuid,Integer cid);
	
	public Integer passnum(Integer classid,Integer cid);//及格的
	
	public Integer nopassnum(Integer classid,Integer cid);//没及格的
		
}
