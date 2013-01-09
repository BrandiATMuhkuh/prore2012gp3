package at.ac.tuwien.sportmate;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

		showProfile = (Button)view.findViewById(R.id.selectMyUser);
		showProfile.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				data.setCurrentViewedMember(data.getCurrentMember());
				updateView();
			}
		});
		
		
		if (member.user_id == AppData.getInstance().getCurrentMember().user_id){
			//showProfile.setVisibility(View.GONE);
			Log.d("Debug", "Des bist du selber");
		} else {
			showProfile.setVisibility(View.VISIBLE);
			Log.d("Debug", "Des is wer anders");
		}
		
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
			//showProfile.setVisibility(View.GONE);
			Log.d("Debug", "Des bist du selber");
		} else {
			showProfile.setVisibility(View.VISIBLE);
			Log.d("Debug", "Des is wer anders");
		}
		
		scrollView.fullScroll(ScrollView.FOCUS_UP);
	}
}
