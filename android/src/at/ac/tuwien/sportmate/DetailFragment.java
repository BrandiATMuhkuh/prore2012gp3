package at.ac.tuwien.sportmate;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class DetailFragment extends Fragment implements EventInterface {
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
		View view = inflater.inflate(R.layout.details, container, false);

		
		// get references to views defined in our main.xml layout file		
		((Button) view.findViewById(R.id.button1)).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.d("p", "press button");
				//SportMateApplication.getApplication().testSendEventA();
				 //updateLed();
				 SportMateApplication.getApplication().sendTest();
				
			}
		});
		
		((Button) view.findViewById(R.id.buttonc)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				SportMateApplication.getApplication().setGroupProgress(49);
			}
		});
		
		((Button) view.findViewById(R.id.buttond)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				SportMateApplication.getApplication().setMyProgress(12);
			}
		});
		
		((Button) view.findViewById(R.id.buttone)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				SportMateApplication.getApplication().setGroupNotificatinoLight(1);
			}
		});
		
		((Button) view.findViewById(R.id.buttonf)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				SportMateApplication.getApplication().setMyNotificatinoLight(0);
			}
		});
		return view;
	}

	@Override
	public void eventA() {
		System.out.println("testEventStartetInClass: "+this.getClass().getName());
		
	}
}