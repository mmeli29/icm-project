package modelo;

import java.util.List;

public class Inscription {

	
	private String nameSubject;
	private String lapseInitial;
	private String lapseFinal;
	private List<HourHand> hourHand;
	
	public Inscription(String nameS, String lapseI, String lapseF){
		this.setNameSubject(nameS);
		this.setLapseFinal(lapseF);
		this.setLapseInitial(lapseI);
	
	}
	public List<HourHand> getHourHand() {
		return hourHand;
	}
	public void setHourHand(List<HourHand> hourHand) {
		this.hourHand = hourHand;
	}
	public String getNameSubject() {
		return nameSubject;
	}
	public void setNameSubject(String nameSubject) {
		this.nameSubject = nameSubject;
	}
	public String getLapseInitial() {
		return lapseInitial;
	}
	public void setLapseInitial(String lapseInitial) {
		this.lapseInitial = lapseInitial;
	}
	public String getLapseFinal() {
		return lapseFinal;
	}
	public void setLapseFinal(String lapseFinal) {
		this.lapseFinal = lapseFinal;
	}
	

}
