package pojo;
/**
 * create table SCRoom(
Classid int,
Scid int
)default charset=utf8;
 * @author Machenike
 *
 */
public class SCRoom {
	private Integer Classid;
	private Integer Scid;
	@Override
	public String toString() {
		return "SCRoom [Classid=" + Classid + ", Scid=" + Scid + "]";
	}
	public Integer getClassid() {
		return Classid;
	}
	public void setClassid(Integer classid) {
		Classid = classid;
	}
	public Integer getScid() {
		return Scid;
	}
	public void setScid(Integer scid) {
		Scid = scid;
	}

}
