package at.ac.tuwien.sportmate;

import java.sql.Date;
import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ActivityStartFragment extends Fragment implements EventInterface {

	TextView lblStarttimeOut, lblTimeOut, lblPointsOut, lblGroupmembersOut;
	View view;
	Button btnStop;
	long start_time, stop_time, duration;
	int count_groupMembersDuringActivity = -1; 
	boolean STOP;
	Handler handler;
	Timer timerActivityStart;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		handler = new Handler();

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}

	@Override
	public void onResume() {
		SportMateApplication.getApplication().registerListener(
				this.getClass().getName(), this);
		super.onResume();
	}

	@Override
	public void onStop() {
		SportMateApplication.getApplication().unregisterListener(
				this.getClass().getName());
		super.onStop();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// ............ view finden................
		view = inflater.inflate(R.layout.activitystart, container, false);
		STOP = false;
		// ..................... Controls finden ................

		btnStop = (Button) view.findViewById(R.id.stopActivity);
		lblStarttimeOut = (TextView) view.findViewById(R.id.lblStartzeitOut);
		lblTimeOut = (TextView) view.findViewById(R.id.lblSportzeitOut);
		lblGroupmembersOut = (TextView) view.findViewById(R.id.lblGroupMembersOut);

		// aktuelle startzeit bestimmen
		start_time = System.currentTimeMillis();
		lblStarttimeOut.setText(DateFormat.format("dd.MM.yyyy hh:mm", new Date(
				start_time)));

		// / ..............listeners....................
		/* ................. stop button klick auf stopbutton................ */
		btnStop.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				STOP = true;

				// berechnungen durchführen.
				stop_time = System.currentTimeMillis();

				duration = stop_time - start_time;

				// zurueck zur start-seite
				MainActivity.showStart();
			}
		});

		setTimerTicker(view);
		setGroupMembersDuringActivity(view); 
		
		return view;
	}

	
	public void setGroupMembersDuringActivity(View view) {
		// Do something long

		Runnable runnable = new Runnable() {
			String sOut = ""; 
			@Override
			public void run() {
				while (!STOP) {
					
					// da DB-fkt aufrufen
					BoGroupMember member = AppData.getInstance().getCurrentMember(); 
					if (member != null) {
						System.out.println(member.toString()); 
						count_groupMembersDuringActivity = DBHandler.getActiveGroupMemberCount(member.user_id, member.group_id); 
					
					}
					handler.post(new Runnable() {
						@Override
						public void run() {
							// ticker setzen
							if (count_groupMembersDuringActivity == 1)
								sOut = String.format("Aktuell macht 1 weiteres Gruppenmitglied Sport", count_groupMembersDuringActivity); 
							else 
								sOut = String.format("Aktuell machen %d weitere Gruppenmitglieder Sport", count_groupMembersDuringActivity); 
							
							lblGroupmembersOut.setText(sOut);
						}
					});
					try {
						// nicht vergessen.. jetzte sekunde erst setzen. 
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		};
		new Thread(runnable).start();
	}
	
	public void setTimerTicker(View view) {
		// Do something long

		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				while (!STOP) {
					
					stop_time = System.currentTimeMillis();
					duration = stop_time - start_time;

					handler.post(new Runnable() {
						@Override
						public void run() {
							// ticker setzen
							lblTimeOut.setText(DateFormat.format("mm:ss",
									new Date(duration)));
						}
					});
					try {
						// nicht vergessen.. jetzte sekunde erst setzen. 
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		};
		new Thread(runnable).start();
	}


	@Override
	public void eventA() {
		System.out.println("testEventStartetInClass: "
				+ this.getClass().getName());
	}
}