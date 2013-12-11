package modelo;

import java.util.ArrayList;
import java.util.List;

public class ApplicationModel {
	private static ApplicationModel _instance;
	
	private User user;
	private List<Inscription> inscriptionList;
	private List<Building> buildingList;
	private List<Classroom> classRoomList;
	private ApplicationModel(){
		
	}

	public static ApplicationModel getInstance(){
		if (_instance == null)
	        {
	            _instance = new ApplicationModel();
	        }
	        return _instance;
	}

	public User getUser() {
		if (user == null){
			user = new User();
			user.setAccountGmail("die.fernandez@gmail.com");
			user.setCareer("Informatica");
			user.setInscription(getInscriptionList().subList(0,2));
			
		}
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Inscription> getInscriptionList() {
		if(inscriptionList == null){
			inscriptionList = new ArrayList<Inscription>();
			populateInscriptionList(inscriptionList);
		}
		return inscriptionList;
	}

	

	public void setInscriptionList(List<Inscription> inscriptionList) {
		this.inscriptionList = inscriptionList;
	}

	public List<Building> getBuildingList() {
		if(buildingList == null){
			buildingList = new ArrayList<Building>();
			pupulateBuildingList(buildingList);
		}
		return buildingList;
	}

	public void setBuildingList(List<Building> buildingList) {
		this.buildingList = buildingList;
	}

	public List<Classroom> getClassRoomList() {
		if(classRoomList == null){
			classRoomList = new ArrayList<Classroom>();
			populateClassroomList(classRoomList);
		}
		return classRoomList;
	}

	

	public void setClassRoomList(List<Classroom> classRoomList) {
		this.classRoomList = classRoomList;
	}
	
	private void populateClassroomList(List<Classroom> classRoomList) {
		Classroom classroom1 = new Classroom("aula1",getBuildingList().get(0));
		Classroom classroom2 = new Classroom("aula2",getBuildingList().get(1));
		Classroom classroom3 = new Classroom("aula3",getBuildingList().get(1));
		classRoomList.add(classroom1);
		classRoomList.add(classroom2);
		classRoomList.add(classroom3);
		
		
	}
	
	private void populateInscriptionList(List<Inscription> list) {
		Inscription inscription1 = new Inscription("materia 1","abril","julio");
		Inscription inscription2 = new Inscription("materia 2","abril","julio");
		Inscription inscription3 = new Inscription("materia 3","abril","julio");
		Inscription inscription4 = new Inscription("materia 4","abril","julio");
		list.add(inscription1);
		list.add(inscription2);
		list.add(inscription3);
		list.add(inscription4);
		
	}
	
	private void pupulateBuildingList(List<Building> buildingList) {
		Building building1 = new Building("Edificio1");
		Building building2 = new Building("Edificio2");
		Building building3 = new Building("Edificio3");
		Building building4 = new Building("Edificio4");
		buildingList.add(building1);
		buildingList.add(building2);
		buildingList.add(building3);
		buildingList.add(building4);
		
		
	}
}
