package pojo;

public class SelectScope {
	private String name;
	private Integer stuid;
	private String cname;
	private Integer scope;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public void setScope(Integer scope) {
		this.scope = scope;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	@Override
	public String toString() {
		return "SelectScope [name=" + name + ", stuid=" + stuid + ", cname="
				+ cname + ", scope=" + scope + "]";
	}

}
