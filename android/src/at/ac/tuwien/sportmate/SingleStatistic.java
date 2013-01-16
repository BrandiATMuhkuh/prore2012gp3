package at.ac.tuwien.sportmate;


import java.sql.Date;
import java.sql.Time;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

public class SingleStatistic extends Fragment implements EventInterface {

	int memberId;
	static BoGroupMember member;
	TextView username;

	private final static String TAG = "SingleStatistic";

	AppData data;

	TextView currentMinutes1;
	TextView currentMinutes2;
	TextView currentMinutes3;
	TextView currentMinutes4;
	TextView currentMinutes5;

	TextView targetMinutes1;
	TextView targetMinutes2;
	TextView targetMinutes3;
	TextView targetMinutes4;
	TextView targetMinutes5;

	TextView currentPoints1;
	TextView currentPoints2;
	TextView currentPoints3;
	TextView currentPoints4;
	TextView currentPoints5;

	TextView targetPoints1;
	TextView targetPoints2;
	TextView targetPoints3;
	TextView targetPoints4;
	TextView targetPoints5;

	ProgressBar progressBar1;
	ProgressBar progressBar2;
	ProgressBar progressBar3;
	ProgressBar progressBar4;
	ProgressBar progressBar5;

	private int ausdauer_count;
	private int kraft_count;
	private int ballspiel_count;
	private int gym_count;
	private int leichte_count;
	
	TextView ac;
	TextView kc;
	TextView sc;
	TextView gc;
	TextView lc;
	TextView gesamt;
	LinearLayout selectTargets;
	LinearLayout showStats;
	Button changeTargets;
	Button saveTargets;

	//minute steps to increas or decrease targets
	int minute_steps = 15;

	Button showProfile;

	ScrollView scrollView;

	@Override
	public void eventA() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCreate (Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		data = AppData.getInstance();

		member = data.getCurrentViewedMember();

		if (member == null) {
			member = data.getCurrentMember();
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.singlestat, container, false);

		//Username Header
		username = (TextView) view.findViewById(R.id.userName);
		username.setText(member.getUser_name());
		//AppData.getInstance().setCurrentMember(member);

		scrollView = (ScrollView) view.findViewById(R.id.singleScroll);

		//Current Minutes Views
		currentMinutes1 = (TextView) view.findViewById(R.id.currentMinutes1);
		currentMinutes1.setText(String.valueOf(member.getWeeklyCategoryMinutes(1)));

		currentMinutes2 = (TextView) view.findViewById(R.id.currentMinutes2);
		currentMinutes2.setText(String.valueOf(member.getWeeklyCategoryMinutes(2)));

		currentMinutes3 = (TextView) view.findViewById(R.id.currentMinutes3);
		currentMinutes3.setText(String.valueOf(member.getWeeklyCategoryMinutes(3)));

		currentMinutes4 = (TextView) view.findViewById(R.id.currentMinutes4);
		currentMinutes4.setText(String.valueOf(member.getWeeklyCategoryMinutes(4)));

		currentMinutes5 = (TextView) view.findViewById(R.id.currentMinutes5);
		currentMinutes5.setText(String.valueOf(member.getWeeklyCategoryMinutes(5)));

		//Target Minutes Views
		targetMinutes1 = (TextView) view.findViewById(R.id.targetMinutes1);
		targetMinutes1.setText(String.valueOf(member.getWeeklyTargetCategoryMins(1)));

		targetMinutes2 = (TextView) view.findViewById(R.id.targetMinutes2);
		targetMinutes2.setText(String.valueOf(member.getWeeklyTargetCategoryMins(2)));

		targetMinutes3 = (TextView) view.findViewById(R.id.targetMinutes3);
		targetMinutes3.setText(String.valueOf(member.getWeeklyTargetCategoryMins(3)));

		targetMinutes4 = (TextView) view.findViewById(R.id.targetMinutes4);
		targetMinutes4.setText(String.valueOf(member.getWeeklyTargetCategoryMins(4)));

		targetMinutes5 = (TextView) view.findViewById(R.id.targetMinutes5);
		targetMinutes5.setText(String.valueOf(member.getWeeklyTargetCategoryMins(5)));

		//Set Current Points per Category
		currentPoints1 = (TextView) view.findViewById(R.id.currentPoints1);
		currentPoints1.setText(String.valueOf(member.calculateWeeklyCategoryPoints(1)));

		currentPoints2 = (TextView) view.findViewById(R.id.currentPoints2);
		currentPoints2.setText(String.valueOf(member.calculateWeeklyCategoryPoints(2)));

		currentPoints3 = (TextView) view.findViewById(R.id.currentPoints3);
		currentPoints3.setText(String.valueOf(member.calculateWeeklyCategoryPoints(3)));

		currentPoints4 = (TextView) view.findViewById(R.id.currentPoints4);
		currentPoints4.setText(String.valueOf(member.calculateWeeklyCategoryPoints(4)));

		currentPoints5 = (TextView) view.findViewById(R.id.currentPoints5);
		currentPoints5.setText(String.valueOf(member.calculateWeeklyCategoryPoints(5)));

		//Set TargetPoints per Category
		targetPoints1 = (TextView) view.findViewById(R.id.targetPoints1);
		targetPoints1.setText(String.valueOf(member.calculateWeeklyCategoryTargetPoints(1)));

		targetPoints2 = (TextView) view.findViewById(R.id.targetPoints2);
		targetPoints2.setText(String.valueOf(member.calculateWeeklyCategoryTargetPoints(2)));

		targetPoints3 = (TextView) view.findViewById(R.id.targetPoints3);
		targetPoints3.setText(String.valueOf(member.calculateWeeklyCategoryTargetPoints(3)));

		targetPoints4 = (TextView) view.findViewById(R.id.targetPoints4);
		targetPoints4.setText(String.valueOf(member.calculateWeeklyCategoryTargetPoints(4)));

		targetPoints5 = (TextView) view.findViewById(R.id.targetPoints5);
		targetPoints5.setText(String.valueOf(member.calculateWeeklyCategoryTargetPoints(5)));

		//Progress bars
		progressBar1 = (ProgressBar)view.findViewById(R.id.progressBar1);
		progressBar1.setProgress(member.calculateWeeklyCategoryPercentage(1));
		progressBar1.setProgressDrawable(getResources().getDrawable(R.drawable.progress_horizontal));

		progressBar2 = (ProgressBar)view.findViewById(R.id.progressBar2);
		progressBar2.setProgress(member.calculateWeeklyCategoryPercentage(2));
		progressBar2.setProgressDrawable(getResources().getDrawable(R.drawable.progress_horizontal));

		progressBar3 = (ProgressBar)view.findViewById(R.id.progressBar3);
		progressBar3.setProgress(member.calculateWeeklyCategoryPercentage(3));
		progressBar3.setProgressDrawable(getResources().getDrawable(R.drawable.progress_horizontal));

		progressBar4 = (ProgressBar)view.findViewById(R.id.progressBar4);
		progressBar4.setProgress(member.calculateWeeklyCategoryPercentage(4));
		progressBar4.setProgressDrawable(getResources().getDrawable(R.drawable.progress_horizontal));

		progressBar5 = (ProgressBar)view.findViewById(R.id.progressBar5);
		progressBar5.setProgress(member.calculateWeeklyCategoryPercentage(5));
		progressBar5.setProgressDrawable(getResources().getDrawable(R.drawable.progress_horizontal));

		//----------Init Buttons--------//
		
		showProfile = (Button)view.findViewById(R.id.selectMyUser);
		showProfile.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				data.setCurrentViewedMember(data.getCurrentMember());
				//member = data.getCurrentViewedMember();
				updateView();
			}
		});

		if (member.user_id == AppData.getInstance().getCurrentMember().user_id){
			showProfile.setVisibility(View.GONE);
			Log.d("Debug", "Des bist du selber");
		} else {
			showProfile.setVisibility(View.VISIBLE);
			Log.d("Debug", "Des is wer anders");
		}
		
		changeTargets = (Button) view.findViewById(R.id.changeTargets);
		saveTargets = (Button) view.findViewById(R.id.saveTargets);
		
		if(member.user_id != AppData.getInstance().getCurrentMember().user_id)
		{
			changeTargets.setVisibility(View.GONE);
		}
		changeTargets.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) 
			{
				showStats.setVisibility(View.GONE);
				changeTargets.setVisibility(View.GONE);
				selectTargets.setVisibility(View.VISIBLE);
				saveTargets.setVisibility(View.VISIBLE);
				scrollView.fullScroll(ScrollView.FOCUS_UP);
			}
		});
		
		saveTargets.setVisibility(View.GONE);
		saveTargets.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) 
			{
				DBHandler.updateWeeklyTargets(data.getCurrentMember().user_id, ausdauer_count, 
						kraft_count, ballspiel_count, gym_count, leichte_count,new Date(System.currentTimeMillis()), 
						new Time(System.currentTimeMillis()));
				member.setWeeklyTarget(1, ausdauer_count);
				member.setWeeklyTarget(2, kraft_count);
				member.setWeeklyTarget(3, ballspiel_count);
				member.setWeeklyTarget(4, gym_count);
				member.setWeeklyTarget(5, leichte_count);
				
				MainActivity.updateAllViews();
			}
		});

		//--------------Select Target------------//
		
		//Ausdauer 
		ac = (TextView)view.findViewById(R.id.ausdauer_count);
		((Button) view.findViewById(R.id.ausdauer_down)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (ausdauer_count > 0)	ausdauer_count -= minute_steps;
				ac.setText(String.format("%3d min", ausdauer_count));
				refreshPoints();
			}
		});

		((Button) view.findViewById(R.id.ausdauer_up)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ausdauer_count += minute_steps;
				ac.setText(String.format("%3d min", ausdauer_count));
				refreshPoints();
			}
		});

		//Kraft
		kc = (TextView)view.findViewById(R.id.kraft_count);
		((Button) view.findViewById(R.id.kraft_down)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (kraft_count > 0)	kraft_count -= minute_steps;
				kc.setText(String.format("%3d min", kraft_count));
				refreshPoints();
			}
		});

		((Button) view.findViewById(R.id.kraft_up)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				kraft_count += minute_steps;
				kc.setText(String.format("%3d min", kraft_count));
				refreshPoints();
			}
		});

		//Ballspiel
		sc = (TextView)view.findViewById(R.id.ballspiel_count);
		((Button) view.findViewById(R.id.ballspiel_down)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (ballspiel_count > 0)	ballspiel_count -= minute_steps;
				sc.setText(String.format("%3d min", ballspiel_count));
				refreshPoints();
			}
		});

		((Button) view.findViewById(R.id.ballspiel_up)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ballspiel_count += minute_steps;
				sc.setText(String.format("%3d min", ballspiel_count));
				refreshPoints();
			}
		});

		//Gymnastik
		gc = (TextView)view.findViewById(R.id.gymnastik_count);
		((Button) view.findViewById(R.id.gymnastik_down)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (gym_count > 0)	gym_count -= minute_steps;
				gc.setText(String.format("%3d min", gym_count));
				refreshPoints();
			}
		});

		((Button) view.findViewById(R.id.gymnastik_up)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				gym_count += minute_steps;
				gc.setText(String.format("%3d min", gym_count));
				refreshPoints();
			}
		});

		//Leichte
		lc = (TextView)view.findViewById(R.id.leichte_count);
		((Button) view.findViewById(R.id.leichte_down)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (leichte_count > 14)	leichte_count -= minute_steps;
				lc.setText(String.format("%3d min", leichte_count));
				refreshPoints();
			}
		});

		((Button) view.findViewById(R.id.leichte_up)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				leichte_count += minute_steps;
				lc.setText(String.format("%3d min", leichte_count));
				refreshPoints();
			}
		});
		
		gesamt = (TextView)view.findViewById(R.id.gesamt_count);
		selectTargets = (LinearLayout)view.findViewById(R.id.selectTarget);
		selectTargets.setVisibility(View.GONE);
		
		showStats = (LinearLayout)view.findViewById(R.id.showStats);
		
		initTargets();

		return view;
	}

	@Override
	public void onResume()
	{
		Log.v(TAG, "onResume");
		super.onResume();
		if(data.getCurrentViewedMember() != null 
				&& data.getCurrentViewedMember() != data.getCurrentMember())
		{
			member = data.getCurrentViewedMember();

			username.setText(member.getUser_name());

			currentMinutes1.setText(String.valueOf(member.getWeeklyCategoryMinutes(1)));
			currentMinutes2.setText(String.valueOf(member.getWeeklyCategoryMinutes(2)));
			currentMinutes3.setText(String.valueOf(member.getWeeklyCategoryMinutes(3)));
			currentMinutes4.setText(String.valueOf(member.getWeeklyCategoryMinutes(4)));
			currentMinutes5.setText(String.valueOf(member.getWeeklyCategoryMinutes(5)));

			targetMinutes1.setText(String.valueOf(member.getWeeklyTargetCategoryMins(1)));
			targetMinutes2.setText(String.valueOf(member.getWeeklyTargetCategoryMins(2)));
			targetMinutes3.setText(String.valueOf(member.getWeeklyTargetCategoryMins(3)));
			targetMinutes4.setText(String.valueOf(member.getWeeklyTargetCategoryMins(4)));
			targetMinutes5.setText(String.valueOf(member.getWeeklyTargetCategoryMins(5)));

			currentPoints1.setText(String.valueOf(member.calculateWeeklyCategoryPoints(1)));
			currentPoints2.setText(String.valueOf(member.calculateWeeklyCategoryPoints(2)));
			currentPoints3.setText(String.valueOf(member.calculateWeeklyCategoryPoints(3)));
			currentPoints4.setText(String.valueOf(member.calculateWeeklyCategoryPoints(4)));
			currentPoints5.setText(String.valueOf(member.calculateWeeklyCategoryPoints(5)));

			targetPoints1.setText(String.valueOf(member.calculateWeeklyCategoryTargetPoints(1)));
			targetPoints2.setText(String.valueOf(member.calculateWeeklyCategoryTargetPoints(2)));
			targetPoints3.setText(String.valueOf(member.calculateWeeklyCategoryTargetPoints(3)));
			targetPoints4.setText(String.valueOf(member.calculateWeeklyCategoryTargetPoints(4)));
			targetPoints5.setText(String.valueOf(member.calculateWeeklyCategoryTargetPoints(5)));

			progressBar1.setProgress(member.calculateWeeklyCategoryPercentage(1));
			progressBar2.setProgress(member.calculateWeeklyCategoryPercentage(2));
			progressBar3.setProgress(member.calculateWeeklyCategoryPercentage(3));
			progressBar4.setProgress(member.calculateWeeklyCategoryPercentage(4));
			progressBar5.setProgress(member.calculateWeeklyCategoryPercentage(5));
		}
	}

	@Override
	public void onPause()
	{
		super.onPause();
		member = data.getCurrentMember();
		data.setCurrentViewedMember(data.getCurrentMember());
	}

	public void updateView()
	{

		member = data.getCurrentViewedMember();

		if (member == null) member = data.getCurrentMember();

		username.setText(member.getUser_name());

		currentMinutes1.setText(String.valueOf(member.getWeeklyCategoryMinutes(1)));
		currentMinutes2.setText(String.valueOf(member.getWeeklyCategoryMinutes(2)));
		currentMinutes3.setText(String.valueOf(member.getWeeklyCategoryMinutes(3)));
		currentMinutes4.setText(String.valueOf(member.getWeeklyCategoryMinutes(4)));
		currentMinutes5.setText(String.valueOf(member.getWeeklyCategoryMinutes(5)));

		targetMinutes1.setText(String.valueOf(member.getWeeklyTargetCategoryMins(1)));
		targetMinutes2.setText(String.valueOf(member.getWeeklyTargetCategoryMins(2)));
		targetMinutes3.setText(String.valueOf(member.getWeeklyTargetCategoryMins(3)));
		targetMinutes4.setText(String.valueOf(member.getWeeklyTargetCategoryMins(4)));
		targetMinutes5.setText(String.valueOf(member.getWeeklyTargetCategoryMins(5)));

		currentPoints1.setText(String.valueOf(member.calculateWeeklyCategoryPoints(1)));
		currentPoints2.setText(String.valueOf(member.calculateWeeklyCategoryPoints(2)));
		currentPoints3.setText(String.valueOf(member.calculateWeeklyCategoryPoints(3)));
		currentPoints4.setText(String.valueOf(member.calculateWeeklyCategoryPoints(4)));
		currentPoints5.setText(String.valueOf(member.calculateWeeklyCategoryPoints(5)));

		targetPoints1.setText(String.valueOf(member.calculateWeeklyCategoryTargetPoints(1)));
		targetPoints2.setText(String.valueOf(member.calculateWeeklyCategoryTargetPoints(2)));
		targetPoints3.setText(String.valueOf(member.calculateWeeklyCategoryTargetPoints(3)));
		targetPoints4.setText(String.valueOf(member.calculateWeeklyCategoryTargetPoints(4)));
		targetPoints5.setText(String.valueOf(member.calculateWeeklyCategoryTargetPoints(5)));

		progressBar1.setProgress(member.calculateWeeklyCategoryPercentage(1));
		progressBar2.setProgress(member.calculateWeeklyCategoryPercentage(2));
		progressBar3.setProgress(member.calculateWeeklyCategoryPercentage(3));
		progressBar4.setProgress(member.calculateWeeklyCategoryPercentage(4));
		progressBar5.setProgress(member.calculateWeeklyCategoryPercentage(5));

		if (member.user_id == AppData.getInstance().getCurrentMember().user_id){
			showProfile.setVisibility(View.GONE);
			changeTargets.setVisibility(View.VISIBLE);
			Log.d("Debug", "Des bist du selber");
		} else {
			showProfile.setVisibility(View.VISIBLE);
			changeTargets.setVisibility(View.GONE);
			Log.d("Debug", "Des is wer anders");
		}
		
		selectTargets.setVisibility(View.GONE);
		saveTargets.setVisibility(View.GONE);
		showStats.setVisibility(View.VISIBLE);
		
		scrollView.fullScroll(ScrollView.FOCUS_UP);
	}
	
	private void refreshPoints(){
		//Gesamtcount
		Log.d(this.getClass().getSimpleName(), "refreshing Points");
		double punkte = ausdauer_count*1.2 +
						 kraft_count*1.1 +
						 ballspiel_count*1.0 +
						 gym_count*0.9 +
						 leichte_count*0.8;
		gesamt.setText(" " + String.valueOf((int)punkte));
	}
	
	private void initTargets(){
		
		//daten aus db
		ausdauer_count = member.getWeeklyTargetCategoryMins(1); 
		kraft_count = member.getWeeklyTargetCategoryMins(2);
		ballspiel_count = member.getWeeklyTargetCategoryMins(3);
		gym_count = member.getWeeklyTargetCategoryMins(4);
		leichte_count = member.getWeeklyTargetCategoryMins(5);
		
		ac.setText(String.format("%3d min", ausdauer_count));
		kc.setText(String.format("%3d min", kraft_count));
		sc.setText(String.format("%3d min", ballspiel_count));
		gc.setText(String.format("%3d min", gym_count));
		lc.setText(String.format("%3d min", leichte_count));
		
		refreshPoints();
		
	}
}
