package at.ac.tuwien.sportmate;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import at.abraxas.amarino.Amarino;
import at.abraxas.amarino.AmarinoIntent;

public class SportMateApplication extends Application {

	private static SportMateApplication s_instance;
	//private static final String DEVICE_ADDRESS = "00:06:66:49:30:1E"; //firefly
	private static final String DEVICE_ADDRESS = "00:06:66:43:40:68"; //old
	private ArduinoReceiver ArduinoReceiver = new ArduinoReceiver();
	private HashMap<String, EventInterface> eventMap = new HashMap<String, EventInterface>();
	
    public SportMateApplication()
    {
        s_instance = this;
    }

    public static SportMateApplication getApplication()
    {
        return s_instance;
    }
    
    @Override
    public void onCreate() {
    	// TODO Auto-generated method stub
    	super.onCreate();
    	
    	registerReceiver(ArduinoReceiver, new IntentFilter(AmarinoIntent.ACTION_RECEIVED));
		Amarino.connect(this, DEVICE_ADDRESS);
    }
    
    public void registerListener(String className, EventInterface ef){
    	eventMap.put(className, ef);
    }
    
    public void unregisterListener(String className){
    	eventMap.remove(className);
    }
    
    public void sendTest(){
		Amarino.sendDataToArduino(this, DEVICE_ADDRESS, 'A', 1);
	}
	
    /**
     * Send my and the group pgrogress in percent to the arduino
     * @param myProgress progress in percent
     * @param groupProgress progress in percent
     */
    public void setProgresses(int myProgress, int groupProgress){
    	int a[]={myProgress,groupProgress};
    	Amarino.sendDataToArduino(this, DEVICE_ADDRESS, 'B', a);
    }
    

	
	/**
	 * Send group notification light. 0=off 1=on
	 * @param onOff 0=off 1=on
	 */
    public void setGroupNotificatinoLight(int onOff){
		Amarino.sendDataToArduino(this, DEVICE_ADDRESS, 'D', onOff);
	}
	
	/**
	 * Send my notification light. 0=off 1=on
	 * @param onOff 0=off 1=on
	 */
    public void setMyNotificatinoLight(int onOff){
		Amarino.sendDataToArduino(this, DEVICE_ADDRESS, 'E', onOff);
	}
	
	/**
	 * Start the sport process
	 * Is called via user interaction (arduino or phone)
	 */
    public void sendStart() {
		
		//sent notification led
    	testSendEventA();
		setMyNotificatinoLight(1);
	}
	
	/**
	 * Show progress on the armband
	 * Is called via user interaction (arduino or phone)
	 */
    public void sendShowProgress(){
    	setProgresses(49,80);
	}
    
    public void testSendEventA(){
    	Iterator<String> i = eventMap.keySet().iterator();
    	while(i.hasNext()){
    		eventMap.get(i.next()).eventA();
    		
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
				
				if(data.equals("sendstart")){
					sendStart();
				}
				
				if(data.equals("sendshowprogress")){
					sendShowProgress();
				}
				System.out.println(data);
			}
		}
	}
}
