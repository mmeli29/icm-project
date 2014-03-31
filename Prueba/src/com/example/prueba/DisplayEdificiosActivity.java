package com.example.prueba;

import java.util.List;
import java.util.Locale;

import modelo.ApplicationModel;
import modelo.Building;
import modelo.Classroom;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

public class DisplayEdificiosActivity extends Activity {
	ApplicationModel model = ApplicationModel.getInstance();
	final Context context = this;
	ListView classRoomList;
	Spinner buildingList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_edificios);
		buildingList = (Spinner) findViewById(R.id.listaEdificios);
		classRoomList = (ListView) findViewById(R.id.classroomList);
		populateBuildingList();
		setClassroomClick(classRoomList);
	}

	
	
	private void populateBuildingList() {
		// Create an ArrayAdapter using the string array and a default spinner layout
				ArrayAdapter<Building> adapter = new ArrayAdapter<Building>(this,
			              android.R.layout.simple_spinner_item,model.getBuildingList() );
			        
				// Apply the adapter to the spinner
				buildingList.setAdapter(adapter);
				setSelectionChange(buildingList);
				// Show the Up button in the action bar.
				setupActionBar();
		
	}

	private void setSelectionChange(Spinner buildingList) {
		buildingList.setOnItemSelectedListener(new OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> parent, View selectedItemView, int pos, long id) {
		    	populateClassroomList( ((Building) parent.getItemAtPosition(pos)).getClassroom());
		    }

		    @Override
		    public void onNothingSelected(AdapterView<?> parentView) {
		       // your code here	
		    }
		});
	}
	
	private void setClassroomClick(ListView classroomList) {
		final ListView cList = (ListView) findViewById(R.id.classroomList);
		classroomList.setOnItemClickListener(new OnItemClickListener() {
		      public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
		    	  Classroom selectedFromList =(Classroom)(cList.getItemAtPosition(myItemInt));
		          loadImage(selectedFromList.getNameClassroom());
		        }                 
		  });
	}
	
	private void populateClassroomList(List<Classroom> clList) {
		ArrayAdapter<Classroom> adapter = new ArrayAdapter<Classroom>(context,
				android.R.layout.simple_list_item_1,clList );   
		// Apply the adapter to the spinner
		classRoomList.setAdapter(adapter);
		
	}
	
	public void loadImage(String aula) {
        AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);

        View layout = inflater.inflate(R.layout.building_image_dialog,
                (ViewGroup) findViewById(R.id.layout_root));
        ImageView imageView = (ImageView) layout.findViewById(R.id.fullimage);
        int imgId = getResources().getIdentifier(aula, "drawable", context.getPackageName());
        Drawable image  = getResources().getDrawable(imgId);
        imageView.setImageDrawable(image);
        imageDialog.setView(layout);
        imageDialog.setPositiveButton(getResources().getString(R.string.ok_button), new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }

        });


        imageDialog.create();
        imageDialog.show();     
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

	public void openMapaEdificio(View view){
		Spinner spinner = (Spinner) findViewById(R.id.listaEdificios);
		Building selectedBuilding = (Building)spinner.getSelectedItem();
		model.setSelectedBuilding(selectedBuilding);
		Intent intent = new Intent(this, MapViewActivity.class);
		startActivity(intent);
	}

}
