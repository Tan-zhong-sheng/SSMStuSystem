package mapper;

import java.util.List;

import pojo.Classes;
import pojo.SelectScope;
import pojo.student;

public interface Classess {
	
	public void insertClass(Classes cl);
	
	public List<Integer> SelectClassid();
	
	public List<Classes> ClassInfo(Integer start,Integer end);
	
	public Integer Count();
	
	public void Delete(Integer Classesid);
	
	public List<SelectScope> selectClassesnumber(Integer Classesid,Integer cid);
	
	public Integer SelectStudentMaxNum(Integer Classid);
	
	public Integer SelectStudentNum(Integer Clasid);
	
}
