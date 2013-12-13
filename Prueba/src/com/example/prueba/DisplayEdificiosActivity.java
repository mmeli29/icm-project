package com.example.prueba;

import java.util.Locale;

import modelo.ApplicationModel;
import modelo.Building;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class DisplayEdificiosActivity extends Activity {
	ApplicationModel model = ApplicationModel.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_edificios);
		Spinner spinner = (Spinner) findViewById(R.id.listaEdificios);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<Building> adapter = new ArrayAdapter<Building>(this,
	              android.R.layout.simple_spinner_item,model.getBuildingList() );
	        
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
		// Show the Up button in the action bar.
		setupActionBar();
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_edificios, menu);
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

	public void openMapaEdificioGoogleMaps(View view){
		Spinner spinner = (Spinner) findViewById(R.id.listaEdificios);
		Building selectedBuilding = (Building)spinner.getSelectedItem();
		double buildingLat = selectedBuilding.getContextFeature().getValue().getLatitudeE6();
		double buildingLong =selectedBuilding.getContextFeature().getValue().getLongitudeE6();
		String uri = String.format(Locale.ENGLISH, "geo:%f,%f", buildingLat, buildingLong);
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
		startActivity(intent);
	}
	public void openMapaEdificio(View view){
		Spinner spinner = (Spinner) findViewById(R.id.listaEdificios);
		Building selectedBuilding = (Building)spinner.getSelectedItem();
		double buildingLat = selectedBuilding.getContextFeature().getValue().getLatitudeE6();
		double buildingLong =selectedBuilding.getContextFeature().getValue().getLongitudeE6();
		Intent intent = new Intent(this, MapViewActivity.class);
		intent.putExtra("Lat", buildingLat);
		intent.putExtra("Long", buildingLong);
		startActivity(intent);
	}
}
