package mapper;




import pojo.governor;
import pojo.photoverification;

public interface mapper {
	
	public governor governorLogin(Integer id,String pwd);
	
	public Integer selectleave(Integer governorid);
	
	public void governolupdate(Integer governorid,String pwd);
	
	public photoverification Code (Integer pid);
	
	public photoverification checkcode(Integer pid);
}
