package mapper;



import java.util.List;

import pojo.TC;

public interface Tc {
	
	public void addTc(TC tc);
	
	public void distribution(TC tc);
	
	public List<TC> distributioncourse(Integer start,Integer end);
	
	public Integer tccount();
	
	public void closedistributioncourse(Integer cid,Integer classid,Integer teacherid);
}
