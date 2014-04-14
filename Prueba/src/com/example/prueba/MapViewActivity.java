package com.example.prueba;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import modelo.ApplicationModel;
import modelo.Building;
import modelo.RouteProvider;

import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.overlays.FolderOverlay;
import org.osmdroid.bonuspack.overlays.Marker;
import org.osmdroid.bonuspack.overlays.MarkerInfoWindow;
import org.osmdroid.bonuspack.overlays.Polyline;
import org.osmdroid.bonuspack.routing.GoogleRoadManager;
import org.osmdroid.bonuspack.routing.MapQuestRoadManager;
import org.osmdroid.bonuspack.routing.OSRMRoadManager;
import org.osmdroid.bonuspack.routing.Road;
import org.osmdroid.bonuspack.routing.RoadManager;
import org.osmdroid.bonuspack.routing.RoadNode;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.DirectedLocationOverlay;
import org.osmdroid.views.overlay.Overlay;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class MapViewActivity extends Activity{
	
	final Context context = this;
	ApplicationModel model = ApplicationModel.getInstance();
	DirectedLocationOverlay myLocationOverlay;
	IMapController mapController;
	MapView map;
	Spinner routeProviderSpinner;
	LocationManager locationManager;
	protected Polyline roadOverlay;
	protected FolderOverlay roadNodeMarkers;
	MyLocationListener locationListener = new MyLocationListener();
	
	GeoPoint destinationPoint;
	GeoPoint startPoint;
	
	static final int OSRM=0, MAPQUEST_FASTEST=1, MAPQUEST_BICYCLE=2, MAPQUEST_PEDESTRIAN=3, GOOGLE_FASTEST=4;
	int whichRouteProvider;
	private Road mRoad;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_view);
		// Show the Up button in the action bar.
		setupActionBar();
		this.whichRouteProvider = OSRM;
		routeProviderSpinner = (Spinner) findViewById(R.id.routeProviderList);
		populateProviderSpinner();
		map = (MapView) findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPQUESTOSM);
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);

        destinationPoint = model.getSelectedBuilding().getContextFeature().getValue();
        Marker startMarker = new Marker(map);
        startMarker.setPosition(destinationPoint);
        startMarker.setTitle(model.getSelectedBuilding().getNameBuilding());
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        map.getOverlays().add(startMarker);
       
		mapController = map.getController();
        mapController.setZoom(18);
        mapController.setCenter(destinationPoint);
        
        roadNodeMarkers = new FolderOverlay(this);
		map.getOverlays().add(roadNodeMarkers);
        
        //my location
        myLocationOverlay =  new DirectedLocationOverlay(this);
		map.getOverlays().add(myLocationOverlay);
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		if (location == null)
			location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		if (location != null) {
			//location known:
			locationListener.onLocationChanged(location);
//			onLocationChanged(location);
			startPoint = new GeoPoint(location); 
		} else {
			//no location known: hide myLocationOverlay
			myLocationOverlay.setEnabled(false);
		}
        
        
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}
	
	protected void onResume(){
		super.onResume();
		boolean isOneProviderEnabled = startLocationUpdates();
		myLocationOverlay.setEnabled(isOneProviderEnabled);
		
	}
	protected void onPause(){
		super.onPause();
		myLocationOverlay.setEnabled(Boolean.FALSE);
		locationManager.removeUpdates(locationListener);
	}
//	@Override
//	public void onLocationChanged(Location location){
//		GeoPoint newLocation = new GeoPoint(location);
//		startPoint = newLocation;
//		mapController.animateTo(newLocation);
//		myLocationOverlay.setLocation(newLocation);
//		myLocationOverlay.setAccuracy((int)location.getAccuracy());
//		getRoadAsync();
//		map.invalidate();
//		
//	}

	boolean startLocationUpdates(){
		boolean result = false;
		for (final String provider : locationManager.getProviders(true)) {
			locationManager.requestLocationUpdates(provider, 0, 0, locationListener);
			result = true;
		}
		return result;
	}
	
	 private void putRoadNodes(Road road){
			roadNodeMarkers.getItems().clear();
			Drawable icon = getResources().getDrawable(R.drawable.marker_node);
			int n = road.mNodes.size();
			MarkerInfoWindow infoWindow = new MarkerInfoWindow(R.layout.bonuspack_bubble, map);
			TypedArray iconIds = getResources().obtainTypedArray(R.array.direction_icons);
	    	for (int i=0; i<n; i++){
	    		RoadNode node = road.mNodes.get(i);
	    		String instructions = (node.mInstructions==null ? "" : node.mInstructions);
	    		Marker nodeMarker = new Marker(map);
	    		nodeMarker.setTitle("Step " + (i+1));
	    		nodeMarker.setSnippet(instructions);
	    		nodeMarker.setSubDescription(Road.getLengthDurationText(node.mLength, node.mDuration));
	    		nodeMarker.setPosition(node.mLocation);
	    		nodeMarker.setIcon(icon);
	    		nodeMarker.setInfoWindow(infoWindow); //use a shared infowindow. 
	    		int iconId = iconIds.getResourceId(node.mManeuverType, R.drawable.ic_empty);
	    		if (iconId != R.drawable.ic_empty){
		    		Drawable image = getResources().getDrawable(iconId);
		    		nodeMarker.setImage(image);
	    		}
	    		roadNodeMarkers.add(nodeMarker);
	    	}
	    	iconIds.recycle();
	    }
	
	void updateUIWithRoad(Road road){
		roadNodeMarkers.getItems().clear();
		TextView textView = (TextView)findViewById(R.id.routeInfo);
		textView.setText("");
		List<Overlay> mapOverlays = map.getOverlays();
		if (roadOverlay != null){
			mapOverlays.remove(roadOverlay);
		}
		if (road == null)
			return;
		if (road.mStatus == Road.STATUS_DEFAULT)
			Toast.makeText(map.getContext(), "We have a problem to get the route", Toast.LENGTH_SHORT).show();
		roadOverlay = RoadManager.buildRoadOverlay(road, map.getContext());
		Overlay removedOverlay = mapOverlays.set(1, roadOverlay);
			//we set the road overlay at the "bottom", just above the MapEventsOverlay,
			//to avoid covering the other overlays. 
		mapOverlays.add(removedOverlay);
		putRoadNodes(road);
		map.invalidate();
		//Set route info in the text view:
		textView.setText(road.getLengthDurationText(-1));
    }
    
	
	public void getRoadAsync(){
		mRoad = null;
		GeoPoint roadStartPoint = null;
		if (startPoint != null){
			roadStartPoint = startPoint;
		} else if (myLocationOverlay.isEnabled() && myLocationOverlay.getLocation() != null){
			//use my current location as itinerary start point:
			roadStartPoint = myLocationOverlay.getLocation();
		}
		if (roadStartPoint == null || destinationPoint == null){
			updateUIWithRoad(mRoad);
			return;
		}
		ArrayList<GeoPoint> waypoints = new ArrayList<GeoPoint>(2);
		waypoints.add(roadStartPoint);
//		//add intermediate via points:
//		for (GeoPoint p:viaPoints){
//			waypoints.add(p); 
//		}
		waypoints.add(destinationPoint);
		new UpdateRoadTask().execute(waypoints);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map_view, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private class UpdateRoadTask extends AsyncTask<Object, Void, Road> {
		protected Road doInBackground(Object... params) {
			@SuppressWarnings("unchecked")
			ArrayList<GeoPoint> waypoints = (ArrayList<GeoPoint>)params[0];
			RoadManager roadManager = null;
			Locale locale = Locale.getDefault();
			switch (whichRouteProvider){
			case OSRM:
				roadManager = new OSRMRoadManager();
				break;
			case MAPQUEST_FASTEST:
				roadManager = new MapQuestRoadManager("Fmjtd%7Cluur2qubnq%2Cb0%3Do5-9aand0");
				roadManager.addRequestOption("locale="+locale.getLanguage()+"_"+locale.getCountry());
				break;
			case MAPQUEST_BICYCLE:
				roadManager = new MapQuestRoadManager("Fmjtd%7Cluur2qubnq%2Cb0%3Do5-9aand0");
				roadManager.addRequestOption("locale="+locale.getLanguage()+"_"+locale.getCountry());
				roadManager.addRequestOption("routeType=bicycle");
				break;
			case MAPQUEST_PEDESTRIAN:
				roadManager = new MapQuestRoadManager("Fmjtd%7Cluur2qubnq%2Cb0%3Do5-9aand0");
				roadManager.addRequestOption("locale="+locale.getLanguage()+"_"+locale.getCountry());
				roadManager.addRequestOption("routeType=pedestrian");
				break;
			case GOOGLE_FASTEST:
				roadManager = new GoogleRoadManager();
				//roadManager.addRequestOption("mode=driving"); //default
				break;
			default:
				return null;
			}
			return roadManager.getRoad(waypoints);
		}
		
		protected void onPostExecute(Road result) {
			mRoad = result;
			updateUIWithRoad(result);
		}
		
		
	}
	
	private void populateProviderSpinner() {
		// Create an ArrayAdapter using the string array and a default spinner layout
				ArrayAdapter<RouteProvider> adapter = new ArrayAdapter<RouteProvider>(context,
			              android.R.layout.simple_spinner_item,model.getRouteProviderList() );
			        
				// Apply the adapter to the spinner
				routeProviderSpinner.setAdapter(adapter);
				sertRouteProviderSelectionChange(routeProviderSpinner);
				// Show the Up button in the action bar.
				setupActionBar();
		
	}

	private void sertRouteProviderSelectionChange(
			Spinner routeProviderSpinner) {
		routeProviderSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> parent, View selectedItemView, int pos, long id) {
		    	whichRouteProvider = ((RouteProvider)parent.getItemAtPosition(pos)).getProviderValue();
		    	getRoadAsync();
		    	map.invalidate();
		    }

		    @Override
		    public void onNothingSelected(AdapterView<?> parentView) {
		       // your code here	
		    }
		});
		
	}
	
	private final class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
        	GeoPoint newLocation = new GeoPoint(location);
    		startPoint = newLocation;
    		mapController.animateTo(newLocation);
    		myLocationOverlay.setLocation(newLocation);
    		myLocationOverlay.setAccuracy((int)location.getAccuracy());
    		getRoadAsync();
    		map.invalidate();
        }

        @Override
        public void onProviderDisabled(String provider) {
        	String prov = provider;
        }

        @Override
        public void onProviderEnabled(String provider) {
        	String prov = provider;
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
           int stat = status;
        }
}
}
