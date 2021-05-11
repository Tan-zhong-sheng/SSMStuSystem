package Service;

import java.util.List;

import mapper.Classess;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pojo.Classes;
import pojo.SelectScope;
import pojo.student;
@Service
public class ClassesService {
	@Autowired
	private Classess cr;
	static Logger logger = Logger.getLogger(ClassesService.class);
	public boolean Classinsert(Classes cl){
		try {
			cr.insertClass(cl);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}
	public List<Integer> SelectClassid(){
		try {
			return cr.SelectClassid();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;

		}
	}
	public List<Classes> ClassesInfo(Integer start,Integer end){
		try {
			return cr.ClassInfo(start, end);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	public Integer Count(){
		try {
			return cr.Count();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	public void delete(Integer Classesid){
		try {
			cr.Delete(Classesid);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	public List<SelectScope> selectClassesnumber(Integer Classesid,Integer cid){
		try {
			return cr.selectClassesnumber(Classesid,cid);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	public Integer SelectStudentMaxNum(Integer Classid){
		try {
			return cr.SelectStudentMaxNum(Classid);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	public Integer SelectStudentNum(Integer Clasid){
		try {
			return cr.SelectStudentNum(Clasid);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
}
