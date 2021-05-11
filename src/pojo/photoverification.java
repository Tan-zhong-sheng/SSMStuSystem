package pojo;

public class photoverification {
	private Integer pid;
	private String describes;
	private String address;
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getDescribes() {
		return describes;
	}
	public void setDescribes(String describes) {
		this.describes = describes;
	}
	public String getAddress() {
		return address;
	}
	@Override
	public String toString() {
		return "photoVerification [pid=" + pid + ", describes=" + describes
				+ ", address=" + address + "]";
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
