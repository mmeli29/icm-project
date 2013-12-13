package com.example.prueba;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    /** Called when the user clicks the inscripciones button */
    public void openInscripciones(View view) {
    	Intent intent = new Intent(this, DisplayInscripcionesActivity.class);
        startActivity(intent);
    }
    /** Called when the user clicks the indicaciones button */
    public void openIndicaciones(View view) {
    	Intent intent = new Intent(this, DisplayEdificiosActivity.class);
        startActivity(intent);
    }
    
}
