package modelo;

import java.util.ArrayList;
import java.util.List;

public class Building {

	private String nameBuilding;
	private List<Classroom> classroom; 
	private ContextFeature contextFeature;
	
	public Building(){
		this(null);
	}
	public Building(String nameB){
		this(nameB,null);
		
	}
	public Building(String nameB, ContextFeature contextFeature){
		this.setNameBuilding(nameB);
		setContextFeature(new ContextFeature("position", null));
		setClassroom(new ArrayList<Classroom>());
		
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
