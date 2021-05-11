package pojo;

import org.springframework.stereotype.Component;

@Component
public class TC {
	private Integer teacherid;
	private Integer cid;
	private Integer Classid;
	private String Classname;
	private String cname;
	private String tname;
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getClassname() {
		return Classname;
	}
	public void setClassname(String classname) {
		Classname = classname;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Integer getClassid() {
		return Classid;
	}
	public void setClassid(Integer classid) {
		Classid = classid;
	}
	@Override
	public String toString() {
		return "TC [teacherid=" + teacherid + ", cid=" + cid + ", Classid="
				+ Classid + ", Classname=" + Classname + ", cname=" + cname
				+ ", tname=" + tname + "]";
	}
	public Integer getTeacherid() {
		return teacherid;
	}
	public void setTeacherid(Integer teacherid) {
		this.teacherid = teacherid;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	
}
