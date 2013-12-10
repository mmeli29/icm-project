package modelo;

import java.sql.Date;

public class HourHand {
	private int hour;
	private Date day;
	
	public HourHand(int hour, Date day){
		this.setDay(day);
		this.setHour(hour);
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public Date getDay() {
		return day;
	}
	public void setDay(Date day) {
		this.day = day;
	}
}
