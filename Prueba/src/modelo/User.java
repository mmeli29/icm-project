package modelo;

import java.util.List;

import org.osmdroid.util.GeoPoint;

import modelo.ContextFeature;
import modelo.Inscription;
public class User {
	
	private ContextFeature contextFeature;
	private String career;
	private String accountGmail;
	private List<Inscription> inscription;

	public User(){
		contextFeature=new ContextFeature("position", null);
		
	}

	public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public String getAccountGmail() {
		return accountGmail;
	}

	public void setAccountGmail(String accountGmail) {
		this.accountGmail = accountGmail;
	}

	public List<Inscription> getInscription() {
		return inscription;
	}

	public void setInscription(List<Inscription> inscription) {
		this.inscription = inscription;
	}

	public void updateLocation(GeoPoint location){
		contextFeature.setValue(location);
	}
	
}
