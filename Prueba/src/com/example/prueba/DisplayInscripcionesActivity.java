package com.example.prueba;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import modelo.ApplicationModel;
import modelo.Inscription;
import utils.InscriptionListAdapter;
import utils.NoticeDialogFragment;
import utils.NoticeDialogFragment.NoticeDialogListener;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class DisplayInscripcionesActivity extends Activity implements NoticeDialogListener {
	ApplicationModel model = ApplicationModel.getInstance();
	final Context context = this;
	ListView inscripcionesList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_inscripciones);
		inscripcionesList = (ListView) findViewById(R.id.listaMaterias);
		populateInscripcionesList();
		setInscripcionClick(inscripcionesList);
		// Show the Up button in the action bar.
		setupActionBar();
	}
	
	private void setInscripcionClick(ListView inscripcionesList) {
		final ListView IList = inscripcionesList;
		inscripcionesList.setOnItemClickListener(new OnItemClickListener() {
		      public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
		    	  Inscription selectedFromList =(Inscription)(IList.getItemAtPosition(myItemInt));
		    	  model.setSelectedInscription(selectedFromList);
		          confirmInscription();
		        }                 
		  });
	}
	
	private void populateInscripcionesList() {
				InscriptionListAdapter adapter = new InscriptionListAdapter(this,(ArrayList<Inscription>)model.getInscriptionList());        
				// Apply the adapter to the list
				inscripcionesList.setAdapter(adapter);
				// Show the Up button in the action bar.
				setupActionBar();
		
	}
	
	public void confirmInscription(){
		DialogFragment newFragment = new NoticeDialogFragment();
		newFragment.show(getFragmentManager(), "confirmDialog");
	}
	
	public void inscribirse(){
		 saveInscriptionOnCalendar();
	}
	
	private void saveInscriptionOnCalendar() {
			Inscription inscription = model.getSelectedInscription();
			if (inscription != null){
			Calendar start = Calendar.getInstance();
			Calendar end = Calendar.getInstance();
			start.setTime(inscription.getStartDate());
			end.setTime(inscription.getEndDate());
			Intent intent = new Intent(Intent.ACTION_INSERT).setData(Events.CONTENT_URI);
			intent.putExtra(Events.TITLE, inscription.getNameSubject());
	        intent.putExtra(Events.DESCRIPTION, "inscripción a la materia: " + inscription.getNameSubject());
	        intent.putExtra(Events.EVENT_LOCATION, inscription.getBuildingName() +", "+ inscription.getClassroomName());
	        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, start.getTimeInMillis());
	        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, end.getTimeInMillis());
	        intent.putExtra(Events.ALL_DAY, false);
	        intent.putExtra(Events.STATUS, 1);
	        intent.putExtra(Events.VISIBLE, 1);
	        intent.putExtra(Events.HAS_ALARM, 1);
	        intent.putExtra(Events.RRULE,inscription.getRepetitionExpr());
			startActivity(intent);
		}
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

	@Override
	public void onDialogPositiveClick(DialogFragment dialog) {
		inscribirse();
		
	}

	@Override
	public void onDialogNegativeClick(DialogFragment dialog) {
		
	}

}
