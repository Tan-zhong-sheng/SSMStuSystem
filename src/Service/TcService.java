package Service;


import java.util.List;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pojo.TC;

import mapper.*;

@Service
public class TcService {
	@Autowired
	private Tc tc;
	
	static Logger logger = Logger.getLogger(TcService.class);
	
	public void addTc(TC c){
		try {
			 tc.addTc(c);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	public boolean distribution(TC c){
		try {
			tc.distribution(c);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}
	public List<TC> distributioncourse(Integer start,Integer end){
		try {
			return tc.distributioncourse(start,end);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	public Integer tccount(){
		try {
			return tc.tccount();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	public boolean closedistributioncourse(Integer cid,Integer classid,Integer teacherid){
		try {
			tc.closedistributioncourse(cid, classid, teacherid);
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}
}
