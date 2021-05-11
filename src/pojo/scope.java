package pojo;


public class scope {
	private Integer scope;
	private course course;
	private student student;
	private Integer stuid;
	private Integer cid;
	private Integer teacherid;
	private String grade;
	private Integer status;
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
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
	public Integer getStuid() {
		return stuid;
	}
	public void setStuid(Integer stuid) {
		this.stuid = stuid;
	}
	public Integer getScope() {
		return scope;
	}
	public course getCourse() {
		return course;
	}
	public void setCourse(course course) {
		this.course = course;
	}
	public scope(Integer scope,
			Integer stuid, Integer cid, Integer teacherid, String grade) {
		this.scope = scope;
		this.stuid = stuid;
		this.cid = cid;
		this.teacherid = teacherid;
		this.grade = grade;
	}
	public scope() {
		super();
		// TODO Auto-generated constructor stub
	}
	public student getStudent() {
		return student;
	}
	public void setStudent(student student) {
		this.student = student;
	}
	public void setScope(Integer scope) {
		this.scope = scope;
	}
	@Override
	public String toString() {
		return "scope [scope=" + scope + ", course=" + course + ", student="
				+ student + ", stuid=" + stuid + ", cid=" + cid
				+ ", teacherid=" + teacherid + ", grade=" + grade + ", status="
				+ status + "]";
	}
}
