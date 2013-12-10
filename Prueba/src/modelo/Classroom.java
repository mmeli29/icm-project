package modelo;

public class Classroom {

	private String nameClassroom;
	private Building building;
	public Classroom(String nameClassroom, Building building){
		this.setNameClassroom(nameClassroom);
		this.setBuilding(building);
	}
	public String getNameClassroom() {
		return nameClassroom;
	}
	public void setNameClassroom(String nameClassroom) {
		this.nameClassroom = nameClassroom;
	}
	public Building getBuilding() {
		return building;
	}
	public void setBuilding(Building building) {
		this.building = building;
	}
		
}
