package modelo;

public class Classroom {

	private String nameClassroom;
	private Building building;
	private String map;
	public Classroom(String nameClassroom, Building building, String map){
		this.setNameClassroom(nameClassroom);
		this.setBuilding(building);
		this.setMap(map);
	}
	public Classroom(String nameClassroom){
		this.setNameClassroom(nameClassroom);
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
	@Override
	public String toString() {
		return  getNameClassroom();
	}
	public String getMap() {
		return map;
	}
	public void setMap(String map) {
		this.map = map;
	}
}
