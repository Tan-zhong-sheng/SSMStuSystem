package mapper;

import java.util.List;

import pojo.SchoolRoom;

public interface Schoolroom {
	public void Scinsert(SchoolRoom sc);
	
	public List<SchoolRoom> SelectSchoolRoom(Integer start,Integer end);
	
	public Integer Count();
	
	public void DeleteSchoolRoom(Integer Scid);
	
	public Void UpdateSchoolRoom(Integer Scid,String Management);

}
