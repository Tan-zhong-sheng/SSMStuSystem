package pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import Mytool.Mytool;

public class student implements Serializable {
	private Integer stuid;
	private String name;
	private String pwd;
	private Long phone;
	private String sex;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date age;
	private String Nation;
	private String education;
	private List<course> cou;
	private String major;
	private Date Enrolmenttime;
	private String grade;
	private Integer Classid;
	private String photo;
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public void setClassid(Integer classid) {
		Classid = classid;
	}

	public int getClassid() {
		return Classid;
	}

	public void setClassid(int classid) {
		Classid = classid;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public List<course> getCou() {
		return cou;
	}

	public void setCou(List<course> cou) {
		this.cou = cou;
	}
	
	public Date getEnrolmenttime() {
		return Enrolmenttime;
	}

	public void setEnrolmenttime(Date enrolmenttime) {
		Enrolmenttime = enrolmenttime;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getAge() {
		return age;
	}

	public void setAge(Date age) {
		this.age = age;
	}

	public String getNation() {
		return Nation;
	}

	public void setNation(String nation) {
		this.Nation = nation;
	}

	public Integer getStuid() {
		return stuid;
	}

	public void setStuid(Integer stuid) {
		this.stuid = stuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		if (pwd.equals("")) {
			String Pwd = Mytool.GetMd5("abc");
			this.pwd = Pwd;
		} else {
			this.pwd = pwd;
		}
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "student [stuid=" + stuid + ", name=" + name + ", pwd=" + pwd
				+ ", phone=" + phone + ", sex=" + sex + ", age=" + age
				+ ", Nation=" + Nation + ", education=" + education + ", cou="
				+ cou + ", major=" + major + ", Enrolmenttime=" + Enrolmenttime
				+ ", grade=" + grade + ", Classid=" + Classid + ", photo="
				+ photo + "]";
	}

}
