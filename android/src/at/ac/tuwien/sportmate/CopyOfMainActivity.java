package at.ac.tuwien.sportmate;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import at.abraxas.amarino.Amarino;
import at.abraxas.amarino.AmarinoIntent;

public class CopyOfMainActivity extends Activity{
	
	private static final String TAG = "MultiColorLamp";
	
	
	int nine = 0;
	long lastChange;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		
        
        // get references to views defined in our main.xml layout file		
		((Button) findViewById(R.id.button1)).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.d("p", "press button");
				
				 //updateLed();
				// SportMateApplication.getApplication().sendTest();
				/*
				Intent sendIntent = new Intent();
				sendIntent.setAction(Intent.ACTION_SEND);
				sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
				sendIntent.setType("text/plain");
				startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to));
				startActivity(intent);
				*/
			}
		});
		
		((Button) findViewById(R.id.buttonc)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				SportMateApplication.getApplication().setGroupProgress(49);
			}
		});
		
		((Button) findViewById(R.id.buttond)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				SportMateApplication.getApplication().setMyProgress(12);
			}
		});
		
		((Button) findViewById(R.id.buttone)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				SportMateApplication.getApplication().setGroupNotificatinoLight(1);
			}
		});
		
		((Button) findViewById(R.id.buttonf)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				SportMateApplication.getApplication().setMyNotificatinoLight(0);
			}
		});
		
		
		
        
    }
    
	@Override
	protected void onStart() {
		super.onStart();
        
	}

	@Override
	protected void onStop() {
		super.onStop();
		// save state

	}


}