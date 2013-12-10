package modelo;

import java.util.List;

public class Building {

	private String nameBuilding;
	private List<Classroom> classroom; 
	private ContextFeature contextFeature;
	
	public Building(String nameB){
		this.setNameBuilding(nameB);
		contextFeature=new ContextFeature("position", null);
		
	}
	public List<Classroom> getClassroom() {
		return classroom;
	}

	public void setClassroom(List<Classroom> classroom) {
		this.classroom = classroom;
	}

	

	public String getNameBuilding() {
		return nameBuilding;
	}

	public void setNameBuilding(String nameBuilding) {
		this.nameBuilding = nameBuilding;
		
	}
	
}
