package modelo;

import org.osmdroid.util.GeoPoint;


public class ContextFeature {

		private GeoPoint value;
		private String name;
		
		/**
		 * Converts a pair of coordinates to a GeoPoint
		 * 
		 * @param lat double containing latitude
		 * @param lng double containing longitude
		 *            
		 * @return GeoPoint for the same coords
		 */
		public static GeoPoint coordinatesToGeoPoint(double lat, double lgn) {
			GeoPoint point =  new GeoPoint((int) (lat * 1E6), (int) (lgn * 1E6));
		    return point;
		}
		
		public ContextFeature(){
			this("default");
		}
		
		public ContextFeature(String string){
			this(string, -34.920740, -57.955059);
		}
		
		public ContextFeature(String string, double lat, double lng) {
			super();
			setValue(coordinatesToGeoPoint(lat,lng));
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
