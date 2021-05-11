package pojo;

import org.springframework.stereotype.Component;
@Component
public class SC {
	private Integer stuid;
	private Integer cid;
	private String grade;
	private String status;
	@Override
	public String toString() {
		return "SC [stuid=" + stuid + ", cid=" + cid + ", grade=" + grade
				+ ", status=" + status + "]";
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public Integer getStuid() {
		return stuid;
	}
	public void setStuid(Integer stuid) {
		this.stuid = stuid;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
}
