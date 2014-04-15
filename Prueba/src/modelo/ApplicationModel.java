package modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.osmdroid.util.GeoPoint;

public class ApplicationModel {
	private static ApplicationModel _instance;
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm",Locale.US);
	
	//Context Features
	
	 ContextFeature coord1 = new ContextFeature("location",-34.906909, -57.944568);
	 ContextFeature coord2 = new ContextFeature("location",-34.907596, -57.944964);
	 ContextFeature coord3 = new ContextFeature("location",-34.9070677, -57.9442227);
	 ContextFeature coord4 = new ContextFeature("location",-34.906007, -57.94378800000001);
		
	 //buildings
		
	Building building1 = new Building("Edificio1",coord1);
	Building building2 = new Building("Facultad de Ingenieria",coord2);
	Building building3 = new Building("Facultad de Ciencias Exactas",coord3);
	Building building4 = new Building("Departamento de Aeronautica",coord4);
	
	//Classrooms
	Classroom classroom1 = new Classroom("aula1",building1,"aula1");
	Classroom classroom2 = new Classroom("aula2",building2,"aula2");
	Classroom classroom3 = new Classroom("aula3",building2,"aula3");
	
	private User user;
	private List<Inscription> inscriptionList;
	private List<Building> buildingList;
	private List<Classroom> classRoomList;
	private List<RouteProvider> routeProviderList;
	
	//app context
	
	private Inscription selectedInscription = null;
	
	private Building selectedBuilding = null;
	
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
		
		//inscriptions
		
		Date hora1 = new Date();
		Date horaf1 = new Date();
		Date hora2 = new Date();
		Date horaf2 = new Date();
		try {
			hora1 = sdf.parse("2014-03-08 08:30");
			horaf1 = sdf.parse("2014-03-08 11:30");
			hora2 = sdf.parse("2014-04-02 09:00");
			horaf2 = sdf.parse("2014-04-02 12:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		String repExpr1 = "FREQ=WEEKLY;COUNT=10;WKST=SU;BYDAY=TU,TH";
		String repExpr2 = "FREQ=WEEKLY;COUNT=14;WKST=SU;BYDAY=MO,FR";
		
		Inscription inscription1 = new Inscription("materia 1",hora1,horaf1,classroom1,repExpr1);
		Inscription inscription2 = new Inscription("materia 2",hora2,horaf2,classroom2,repExpr2);
		Inscription inscription3 = new Inscription("materia 3",hora1,horaf1,classroom3,repExpr1);
		Inscription inscription4 = new Inscription("materia 4",hora2,horaf2,classroom3,repExpr2);
		
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
	

	public Inscription getSelectedInscription() {
		return selectedInscription;
	}

	public void setSelectedInscription(Inscription selectedInscription) {
		this.selectedInscription = selectedInscription;
	}

	public Building getSelectedBuilding() {
		return selectedBuilding;
	}

	public void setSelectedBuilding(Building selectedBuilding) {
		this.selectedBuilding = selectedBuilding;
	}

	public List<RouteProvider> getRouteProviderList() {
		if (routeProviderList == null){
			routeProviderList = new ArrayList<RouteProvider>();
			populateRouteProviderList(routeProviderList);
		}
		return routeProviderList;
	}

	private void populateRouteProviderList(
			List<RouteProvider> routeProviderList) {
		routeProviderList.add(new RouteProvider("OSRM", 0));
		routeProviderList.add(new RouteProvider("MAPQUEST_FASTEST", 1));
		routeProviderList.add(new RouteProvider("MAPQUEST_BICYCLE", 2));
		routeProviderList.add(new RouteProvider("MAPQUEST_PEDESTRIAN", 3));
		routeProviderList.add(new RouteProvider("GOOGLE_FASTEST", 4));
		}

	public void setRouteProviderList(List<RouteProvider> routeProviderList) {
		this.routeProviderList = routeProviderList;
	}
	
}
