package pojo;

import java.util.List;

public class Classes {
	private Integer Classid; 
	private String Classname;
	private Integer Classnumber;
	private List<student> stu;
	public Integer getClassnumber() {
		return Classnumber;
	}
	public List<student> getStu() {
		return stu;
	}
	public void setStu(List<student> stu) {
		this.stu = stu;
	}
	public void setClassnumber(Integer classnumber) {
		Classnumber = classnumber;
	}
	public Integer getClassid() {
		return Classid;
	}
	public void setClassid(Integer classid) {
		Classid = classid;
	}
	public String getClassname() {
		return Classname;
	}
	public void setClassname(String classname) {
		Classname = classname;
	}
	@Override
	public String toString() {
		return "ClassRoom [Classid=" + Classid + ", Classname=" + Classname
				+ ", Classnumber=" + Classnumber + "]";
	}
}
