package Service;

import java.util.List;

import mapper.Schoolroom;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pojo.SchoolRoom;

@Service
public class SchoolRoomService {
	@Autowired
	private Schoolroom sc;
	
	static Logger logger = Logger.getLogger(SchoolRoomService.class);
	
	public boolean Scinsert(SchoolRoom room){
		try {
			sc.Scinsert(room);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}
	
	public List<SchoolRoom> SelectSchoolRoom(Integer start,Integer end){
		try {
			return sc.SelectSchoolRoom(start, end);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	public Integer Count(){
		try {
			return sc.Count();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	public boolean DeleteSchoolRoom(Integer Scid){
		try {
			sc.DeleteSchoolRoom(Scid);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}
	public void UpdateSchoolRoom(Integer Scid,String Management){
		try {
			sc.UpdateSchoolRoom(Scid, Management);
		} catch (Exception e) {
			logger.error(e.getMessage());

		}
	}
}
