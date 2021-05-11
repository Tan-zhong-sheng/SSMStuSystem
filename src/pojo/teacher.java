package pojo;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import Mytool.Mytool;
public class teacher {
	private Integer teacherid;
	private String tname;
	private String tpwd;
	private Integer sal;
	private Integer cid;
	private Date Entrytime;
	private List<course> cou;
	private String major; 
	private Long phone;
	private String photo;
	private Long QQ;
	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Long getQQ() {
		return QQ;
	}
	public void setQQ(Long qQ) {
		QQ = qQ;
	}
	public teacher() {
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public List<course> getCou() {
		return cou;
	}
	public void setCou(List<course> cou) {
		this.cou = cou;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Integer getTeacherid() {
		return teacherid;
	}
	public Date getEntrytime() {
		return Entrytime;
	}
	public void setEntrytime(Date entrytime) {
		Entrytime = entrytime;
	}
	public void setTeacherid(Integer teacherid) {
		this.teacherid = teacherid;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getTpwd() {
		return tpwd;
	}
	public void setTpwd(String tpwd) {
		if(tpwd.equals("")){
			String Tpwd = Mytool.GetMd5("abc");
			this.tpwd=Tpwd;
		}else{
			this.tpwd = tpwd;
		}
	}
	public Integer getSal() {
		return sal;
	}
	public void setSal(Integer sal) {
		this.sal = sal;
	}
	@Override
	public String toString() {
		return "teacher [teacherid=" + teacherid + ", tname=" + tname
				+ ", tpwd=" + tpwd + ", sal=" + sal + ", cid=" + cid
				+ ", Entrytime=" + Entrytime + ", cou=" + cou + ", major="
				+ major + ", phone=" + phone + ", photo=" + photo + ", QQ="
				+ QQ + "]";
	}

}
