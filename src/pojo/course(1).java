package pojo;

public class course {
	private Integer cid;
	private String cname;
	private String major;
	private String grade;
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	@Override
	public String toString() {
		return "course [cid=" + cid + ", cname=" + cname + ", major=" + major
				+ ", grade=" + grade + "]";
	}

	
}
