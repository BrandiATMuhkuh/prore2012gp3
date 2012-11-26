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

public class MainActivity extends Activity{
	
	private static final String TAG = "MultiColorLamp";
	
	/* TODO: change the address to the address of your Bluetooth module
	 * and ensure your device is added to Amarino
	 */
	//private static final String DEVICE_ADDRESS = "00:06:66:49:30:1E"; //firefly
	private static final String DEVICE_ADDRESS = "00:06:66:43:40:68"; //old
	private SetupReceiver arduinoReceiver3 = new SetupReceiver();
	private StatusStartReceiver arduinoReceiver2 = new StatusStartReceiver();
	private ArduinoReceiver arduinoReceiver = new ArduinoReceiver();
	private String address;

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

		
        
        // get references to views defined in our main.xml layout file		
		((Button) findViewById(R.id.button1)).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.d("p", "press button");
				
				 //updateLed();
				 sendTest();
				
			}
		});
		
		((Button) findViewById(R.id.buttonc)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setGroupProgress(49);
			}
		});
		
		((Button) findViewById(R.id.buttond)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setMyProgress(12);
			}
		});
		
		((Button) findViewById(R.id.buttone)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setGroupNotificatinoLight(1);
			}
		});
		
		((Button) findViewById(R.id.buttonf)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setMyNotificatinoLight(0);
			}
		});
		
		
		
        colorIndicator = findViewById(R.id.ColorIndicator);
        
    }
    
	@Override
	protected void onStart() {
		super.onStart();
		registerReceiver(arduinoReceiver, new IntentFilter(AmarinoIntent.ACTION_RECEIVED));
		registerReceiver(arduinoReceiver3, new IntentFilter(AmarinoIntent.ACTION_RECEIVED));
		registerReceiver(arduinoReceiver2, new IntentFilter(AmarinoIntent.ACTION_CONNECTED));
		Amarino.connect(this, DEVICE_ADDRESS);
        // set seekbars and feedback color according to last state
        
	}

	@Override
	protected void onStop() {
		super.onStop();
		// save state
		
		// stop Amarino's background service, we don't need it any more 
		// if you connect in onStart() you must not forget to disconnect when your app is closed
		Amarino.disconnect(this, DEVICE_ADDRESS);
		
		// do never forget to unregister a registered receiver
		unregisterReceiver(arduinoReceiver);
	}

	private void sendTest(){
		Amarino.sendDataToArduino(this, DEVICE_ADDRESS, 'A', 1);
	}
	
	/**
	 * Send the group progress to the arduino in percent
	 * @param progress progress in percent
	 */
	private void setGroupProgress(int progress){
		Amarino.sendDataToArduino(this, DEVICE_ADDRESS, 'B', progress);
	}
	
	/**
	 * Send my progress to the arduino in percent
	 * @param progress progress in percent
	 */
	private void setMyProgress(int progress){
		Amarino.sendDataToArduino(this, DEVICE_ADDRESS, 'C', progress);
	}
	
	/**
	 * Send group notification light. 0=off 1=on
	 * @param onOff 0=off 1=on
	 */
	private void setGroupNotificatinoLight(int onOff){
		Amarino.sendDataToArduino(this, DEVICE_ADDRESS, 'D', onOff);
	}
	
	/**
	 * Send my notification light. 0=off 1=on
	 * @param onOff 0=off 1=on
	 */
	private void setMyNotificatinoLight(int onOff){
		Amarino.sendDataToArduino(this, DEVICE_ADDRESS, 'E', onOff);
	}
	
	public class SetupReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			address = intent.getStringExtra(AmarinoIntent.EXTRA_DEVICE_ADDRESS);
			
			System.out.println(intent);
			System.out.print("Arduino connected");
		}
		
	}
	
	public class StatusStartReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			
			System.out.println(intent);
			System.out.print("start buttond");
		}
		
	}

	/**
	 * ArduinoReceiver is responsible for catching broadcasted Amarino
	 * events.
	 * 
	 * It extracts data from the intent and updates the graph accordingly.
	 */
	public class ArduinoReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			String data = null;
			
			System.out.println("receive");
			
			// the device address from which the data was sent, we don't need it here but to demonstrate how you retrieve it
			final String address = intent.getStringExtra(AmarinoIntent.EXTRA_DEVICE_ADDRESS);
			
			// the type of data which is added to the intent
			final int dataType = intent.getIntExtra(AmarinoIntent.EXTRA_DATA_TYPE, -1);
			
			// we only expect String data though, but it is better to check if really string was sent
			// later Amarino will support differnt data types, so far data comes always as string and
			// you have to parse the data to the type you have sent from Arduino, like it is shown below
			if (dataType == AmarinoIntent.STRING_EXTRA){
				data = intent.getStringExtra(AmarinoIntent.EXTRA_DATA);
				
				System.out.println(data);
			}
		}
	}
}