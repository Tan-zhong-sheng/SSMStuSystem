package Service;

import java.util.List;

import mapper.Sc;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pojo.SC;

@Service
public class ScService {
	@Autowired
	private Sc sc;
	static Logger logger = Logger.getLogger(ScService.class);
	public boolean addSc(SC s){
		try {
			sc.addSc(s);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}
	public List<Integer> SelectSc(String major,String grade){
		try {
			return sc.SelectSc(major,grade);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	public boolean DeleteGrade(Integer Cid,String status){
		try {
			sc.DeleteGrade(Cid,status);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}
	public List<Integer> SelectStuid(String major,String grade){
		try {
			return sc.SelectStuid(major,grade);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	public List<Integer> SelectCid(String major){
		try {
			return sc.SelectCid(major);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	public boolean DeleteElective(Integer cid,Integer stuid){
		try {
			sc.DeleteElective(cid, stuid);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}
	public Integer selectcid(Integer cid,Integer stuid){
		try {
			return sc.selectcid(cid,stuid);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	public String cidstatus(){
		try {
			return sc.selectcidstatus();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	public boolean updatecidstatus(){
		try {
			sc.updatecidstatus();
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}
	public boolean closecidstatus(){
		try {
			sc.closecidstatus();
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}
	public String selecttest(){
		try {
			return sc.selecttest();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	public boolean opentest(){
		try {
			sc.opentest();
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}
	public boolean closetest(){
		try {
			sc.closetest();
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}
}
