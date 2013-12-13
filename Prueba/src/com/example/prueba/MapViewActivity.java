package com.example.prueba;

import microsoft.mappoint.TileSystem;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.MapView.Projection;
import org.osmdroid.views.overlay.MyLocationOverlay;
import org.osmdroid.views.overlay.OverlayItem;

import android.app.Activity;
import android.app.Notification.Style;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class MapViewActivity extends Activity implements LocationListener {
	private MapView mapView;
	private MapController mapController;
	private MyLocationOverlay locationOverlay;
	private LocationManager locationManager;
	private OverlayItem overlay;

	public MapView getMapView() {
		return mapView;
	}
	public void setMapView(MapView mapView) {
		this.mapView = mapView;
	}
	public MapController getMapController() {
		return mapController;
	}
	public void setMapController(MapController mapController) {
		this.mapController = mapController;
	}
	public MyLocationOverlay getLocationOverlay() {
		return locationOverlay;
	}
	public void setLocationOverlay(MyLocationOverlay locationOverlay) {
		this.locationOverlay = locationOverlay;
	}
	public LocationManager getLocationManager() {
		return locationManager;
	}
	public void setLocationManager(LocationManager locationManager) {
		this.locationManager = locationManager;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		 // Get the message from the intent
	    Intent intent = getIntent();
	    double buildingLat = intent.getDoubleExtra("Lat", 0);
	    double buildingLong = intent.getDoubleExtra("Long", 0);
	    //GeoPoint point = new GeoPoint(buildingLat, buildingLong);
	    float lat = -34.906909f;
	    float lng = -57.944568f;
	    GeoPoint point = new GeoPoint((int)(lat * 1E6), (int)(lng * 1E6));
	    super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);
        
        setMapView((MapView) findViewById(R.id.mapview));
        getMapView().setBuiltInZoomControls(true);
        setMapController(getMapView().getController());
        getMapController().setZoom(18);
        setLocationOverlay (new MyLocationOverlay (this, getMapView()));
        getMapView().getOverlays().add(getLocationOverlay());
        setOverlay(new OverlayItem("Edificio1","Edificio1",point));
        getMapController().setCenter(point);
        getLocationOverlay().enableMyLocation();
        
        setLocationManager((LocationManager)getSystemService(Context.LOCATION_SERVICE));
  }
	protected void onResume(){
		super.onResume();
		getLocationOverlay().enableMyLocation();
		getLocationManager().requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);
		
	}
	protected void onPause(){
		super.onPause();
		getLocationOverlay().disableMyLocation();
		getLocationManager().removeUpdates(this);
	}

	public void onLocationChanged(Location location){
		if (location != null){
			GeoPoint geoPoint = new GeoPoint(location.getLatitude(), location.getLongitude());
			getMapController().setCenter(geoPoint);
		}
	}
	
	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}
	public OverlayItem getOverlay() {
		return overlay;
	}
	public void setOverlay(OverlayItem overlay) {
		this.overlay = overlay;
	}

	
}
