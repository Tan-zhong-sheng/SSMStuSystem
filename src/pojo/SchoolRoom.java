package pojo;
/**
		 * create table SchoolRoom(
		Scid int auto_increment primary key,
		RoomAddress varchar(30) not null,
		Management varchar(20) not null
		)default charset=utf8;
 *
 */
public class SchoolRoom {
	private Integer Scid;
	private String RoomAddress;
	private String Management;
	public Integer getScid() {
		return Scid;
	}
	public void setScid(Integer scid) {
		Scid = scid;
	}
	public String getRoomAddress() {
		return RoomAddress;
	}
	public void setRoomAddress(String roomAddress) {
		RoomAddress = roomAddress;
	}
	public String getManagement() {
		return Management;
	}
	public void setManagement(String management) {
		Management = management;
	}
	@Override
	public String toString() {
		return "SchoolRoom [Scid=" + Scid + ", RoomAddress=" + RoomAddress
				+ ", Management=" + Management + "]";
	}

}
