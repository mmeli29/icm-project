package modelo;

import java.util.List;

public class Building {

	private String nameBuilding;
	private List<Classroom> classroom; 
	private ContextFeature contextFeature;
	
	public Building(String nameB){
		this.setNameBuilding(nameB);
		setContextFeature(new ContextFeature("position", null));
		
	}
	public List<Classroom> getClassroom() {
		return classroom;
	}

	public void setClassroom(List<Classroom> classroom) {
		this.classroom = classroom;
	}
	
	public String toString(){
		return getNameBuilding();
	}

	

	public String getNameBuilding() {
		return nameBuilding;
	}

	public void setNameBuilding(String nameBuilding) {
		this.nameBuilding = nameBuilding;
		
	}
	public ContextFeature getContextFeature() {
		return contextFeature;
	}
	public void setContextFeature(ContextFeature contextFeature) {
		this.contextFeature = contextFeature;
	}
	
}
