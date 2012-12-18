package at.ac.tuwien.sportmate;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityStartFragment extends Fragment implements EventInterface {
	
	TextView lblStartzeitOut; 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.e("Test", "hello");
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}
	
	@Override
	public void onResume() {
		SportMateApplication.getApplication().registerListener(this.getClass().getName(), this);
		super.onResume();
	}
	
	@Override
	public void onStop() {
		SportMateApplication.getApplication().unregisterListener(this.getClass().getName());
		super.onStop();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.activitystart, container, false);


		ImageView imgStart = (ImageView) view.findViewById(R.id.imgStopActivity);
		lblStartzeitOut = (TextView) view.findViewById(R.id.lblStartzeitOut);
		
		/* klick auf stopbutton */
		imgStart.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//zurueck zur start-seite
				lblStartzeitOut.setText("hugo"); 
				MainActivity.showStart(); 
			}
		});
		

		return view;
	}

	@Override
	public void eventA() {
		System.out.println("testEventStartetInClass: "+this.getClass().getName());
	}
}