package pojo;

import java.io.Serializable;

public class governor implements Serializable {
	private Integer governorid;
	private String pwd;
	private String truename;
	private Integer grade;


	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Integer getGovernorid() {
		return governorid;
	}

	public void setGovernorid(Integer governorid) {
		this.governorid = governorid;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getTruename() {
		return truename;
	}

	public void setTruename(String truename) {
		this.truename = truename;
	}

	@Override
	public String toString() {
		return "governor [governorid=" + governorid + ", pwd=" + pwd
				+ ", truename=" + truename + ", leave=" + grade + "]";
	}

}
