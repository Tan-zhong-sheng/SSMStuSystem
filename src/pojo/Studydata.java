package pojo;

import org.springframework.stereotype.Component;

@Component
public class Studydata {
	private Integer id;
	private Integer teacherid;
	private String filename;
	private String address;
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTeacherid() {
		return teacherid;
	}
	public void setTeacherid(Integer teacherid) {
		this.teacherid = teacherid;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Studydata [id=" + id + ", teacherid=" + teacherid
				+ ", filename=" + filename + ", address=" + address + "]";
	}
	
	
}
