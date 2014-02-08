package com.example.prueba;

import modelo.ApplicationModel;
import modelo.Building;
import modelo.Inscription;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;

public class DisplayInscripcionesActivity extends Activity {
	ApplicationModel model = ApplicationModel.getInstance();
	final Context context = this;
	Spinner inscripcionesList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_inscripciones);
		inscripcionesList = (Spinner) findViewById(R.id.listaMaterias);
		populateInscripcionesList();
		// Show the Up button in the action bar.
		setupActionBar();
	}
	
	private void populateInscripcionesList() {
		// Create an ArrayAdapter using the string array and a default spinner layout
				ArrayAdapter<Inscription> adapter = new ArrayAdapter<Inscription>(this,
			              android.R.layout.simple_spinner_item,model.getInscriptionList());
			        
				// Apply the adapter to the spinner
				inscripcionesList.setAdapter(adapter);
				setSelectionChange(inscripcionesList);
				// Show the Up button in the action bar.
				setupActionBar();
		
	}
	
	private void setSelectionChange(Spinner inscripcionesList) {
		inscripcionesList.setOnItemSelectedListener(new OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> parent, View selectedItemView, int pos, long id) {
		    	displayInfoMateria( ((Inscription) parent.getItemAtPosition(pos)));
		    }

		    @Override
		    public void onNothingSelected(AdapterView<?> parentView) {
		       // your code here	
		    }
		});
	}

	protected void displayInfoMateria(Inscription inscription) {
		EditText fieldNombre = (EditText)findViewById(R.id.name_subject);
		EditText fieldEdificio = (EditText)findViewById(R.id.name_edificio);
		fieldNombre.setText(inscription.getNameSubject());
		fieldEdificio.setText(inscription.getAula().getBuilding().getNameBuilding());
		
		
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
		getMenuInflater().inflate(R.menu.display_inscripciones, menu);
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

}
