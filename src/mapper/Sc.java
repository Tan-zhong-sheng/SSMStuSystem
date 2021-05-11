package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.SC;

public interface Sc {
	public void addSc(SC sc);
	
	public List<Integer> SelectSc(String major,String grade);
	
	public void DeleteGrade(@Param("cid")Integer Cid,@Param("status")String status);
	
	public List<Integer> SelectStuid(String major,String grade);
	
	public List<Integer> SelectCid(String major);
	
	public void DeleteElective(@Param("cid")Integer cid,@Param("stuid")Integer stuid);
	
	public Integer selectcid(Integer cid,Integer stuid);
	
	public String selectcidstatus();
	
	public void updatecidstatus();
	
	public void closecidstatus();
	
	public String selecttest();
	
	public void opentest();
	
	public void closetest();
	
}
