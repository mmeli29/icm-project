package modelo;

import java.util.Date;
import java.util.List;

public class Inscription {

	
	private String nameSubject;
	private Date startDate;
	private Date endDate;
	private String repetitionExpr;
	private Integer durationInWeeks;
	private List<HourHand> hourHand;
	private Classroom aula;
	
	public Inscription(String nameS, Date lapseI, Date lapseF, Classroom aula, String expr){
		this.setNameSubject(nameS);
		this.setStartDate(lapseI);
		this.setEndDate(lapseF);
		this.setAula(aula);
		this.setRepetitionExpr(expr);	
	}
	public List<HourHand> getHourHand() {
		return hourHand;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
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
	
	@Override
	public String toString() {
		return nameSubject;
	}
	public Classroom getAula() {
		return aula;
	}
	public void setAula(Classroom aula) {
		this.aula = aula;
	}
	public String getBuildingName(){
		return this.getAula().getBuilding().getNameBuilding();
	}
	public String getClassroomName(){
		return this.getAula().getNameClassroom();
	}
	public Integer getDurationInWeeks() {
		return durationInWeeks;
	}
	public void setDurationInWeeks(Integer durationInWeeks) {
		this.durationInWeeks = durationInWeeks;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getRepetitionExpr() {
		return repetitionExpr;
	}
	public void setRepetitionExpr(String repetitionExpr) {
		this.repetitionExpr = repetitionExpr;
	}
	

}
