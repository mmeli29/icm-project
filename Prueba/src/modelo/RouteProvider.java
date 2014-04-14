package modelo;

public class RouteProvider {
	
	private String providerName;
	private int providerValue;
	
	public RouteProvider(String providerName, int providerValue) {
		super();
		this.providerName = providerName;
		this.providerValue = providerValue;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public int getProviderValue() {
		return providerValue;
	}
	public void setProviderValue(int providerValue) {
		this.providerValue = providerValue;
	}
	@Override
	public String toString() {
		return getProviderName();
	}
	
	

}
