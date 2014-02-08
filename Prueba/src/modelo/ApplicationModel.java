package modelo;

import java.util.ArrayList;
import java.util.List;

import org.osmdroid.util.GeoPoint;

public class ApplicationModel {
	private static ApplicationModel _instance;
	
	//Context Features
	
	 ContextFeature coord1 = new ContextFeature("location",coordinatesToGeoPoint(-34.906909, -57.944568));
	 
	//buildings
		
	Building building1 = new Building("Edificio1",coord1);
	Building building2 = new Building("Edificio2",coord1);
	Building building3 = new Building("Edificio3",coord1);
	Building building4 = new Building("Edificio4",coord1);
	
	//Classrooms
	Classroom classroom1 = new Classroom("aula1",building1,"aula1");
	Classroom classroom2 = new Classroom("aula2",building2,"aula2");
	Classroom classroom3 = new Classroom("aula3",building2,"aula3");
	
	//inscriptions
	
	Inscription inscription1 = new Inscription("materia 1","abril","julio",classroom1);
	Inscription inscription2 = new Inscription("materia 2","abril","julio",classroom2);
	Inscription inscription3 = new Inscription("materia 3","abril","julio",classroom3);
	Inscription inscription4 = new Inscription("materia 4","abril","julio",classroom3);
	
	
	
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
			assignClassrooms();
			buildingList = new ArrayList<Building>();
			pupulateBuildingList(buildingList);
		}
		return buildingList;
	}

	private void assignClassrooms() {
		building1.getClassroom().add(classroom1);
		building1.getClassroom().add(classroom2);
		building1.getClassroom().add(classroom3);	
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
		classRoomList.add(classroom1);
		classRoomList.add(classroom2);
		classRoomList.add(classroom3);
		
		
	}
	
	private void populateInscriptionList(List<Inscription> list) {
		list.add(inscription1);
		list.add(inscription2);
		list.add(inscription3);
		list.add(inscription4);
		
	}
	
	private void pupulateBuildingList(List<Building> buildingList) {
		buildingList.add(building1);
		buildingList.add(building2);
		buildingList.add(building3);
		buildingList.add(building4);
		
		
	}
	/**
	 * Converts a pair of coordinates to a GeoPoint
	 * 
	 * @param lat double containing latitude
	 * @param lng double containing longitude
	 *            
	 * @return GeoPoint for the same coords
	 */
	public static GeoPoint coordinatesToGeoPoint(double lat, double lgn) {
	    return new GeoPoint((int) (lat * 1E6), (int) (lgn * 1E6));
	}
}
