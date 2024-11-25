package mbTools;

import java.sql.Date;

public class ReservationBean{
	
	private String roomType;
	private String checkInDate;
	private String checkOutDate;
	private int numGuests;
	private int resNumber;
	
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public String getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}
	public String getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	public int getNumGuests() {
		return numGuests;
	}
	public void setNumGuests(int numGuests) {
		this.numGuests = numGuests;
	}
	public int getResNumber() {
		return resNumber;
	}
	public void setResNumber(int resNum) {
		this.resNumber= resNum;
	}
	
	
	
	
	
	
	
}