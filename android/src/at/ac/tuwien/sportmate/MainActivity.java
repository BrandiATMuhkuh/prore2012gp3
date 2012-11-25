package at.ac.tuwien.sportmate;

import android.app.Activity;
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

public class MainActivity extends Activity{
	
	private static final String TAG = "MultiColorLamp";
	
	/* TODO: change the address to the address of your Bluetooth module
	 * and ensure your device is added to Amarino
	 */
	private static final String DEVICE_ADDRESS = "00:06:66:49:30:1E";
	
	final int DELAY = 150;
	Button mybutton;
	View colorIndicator;
	
	int nine = 0;
	long lastChange;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		Amarino.connect(this, DEVICE_ADDRESS);
        
        // get references to views defined in our main.xml layout file
		mybutton = (Button) findViewById(R.id.button1);
		
		mybutton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.d("p", "press button");
				
				 updateLed();
				
			}
		});
		
        colorIndicator = findViewById(R.id.ColorIndicator);

    }
    
	@Override
	protected void onStart() {
		super.onStart();

        
        // set seekbars and feedback color according to last state
        
	}

	@Override
	protected void onStop() {
		super.onStop();
		// save state
		
		// stop Amarino's background service, we don't need it any more 
		Amarino.disconnect(this, DEVICE_ADDRESS);
	}


	private void updateLed(){
		Amarino.sendDataToArduino(this, DEVICE_ADDRESS, 'A', 0);
	}

	
}