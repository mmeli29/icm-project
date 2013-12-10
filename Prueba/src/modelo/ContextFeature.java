package modelo;

import org.osmdroid.util.GeoPoint;


public class ContextFeature {

		private GeoPoint value;
		private String name;
		
		public ContextFeature(String string, GeoPoint geoPoint) {
			setValue(geoPoint);
			setName(string);
		}

		public GeoPoint getValue() {
			return value;
		}
		public void setValue(GeoPoint value) {
			this.value= value;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
}
