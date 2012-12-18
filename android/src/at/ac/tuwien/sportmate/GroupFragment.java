package at.ac.tuwien.sportmate;

import java.util.ArrayList;

import org.w3c.dom.Text;

import android.app.Fragment;
import android.net.ParseException;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.QuickContactBadge;
import android.widget.TextView;

public class GroupFragment extends Fragment implements EventInterface {

	BoGroup group = new BoGroup();

	private final static String TAG = "GroupFragment";

	BoGroupMember member1;
	BoGroupMember member2;
	BoGroupMember member3;
	BoGroupMember member4;
	BoGroupMember member5;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.loadDataFromDB(1); //TODO
	}

	@Override
	public void onResume() {
		SportMateApplication.getApplication().registerListener(this.getClass().getName(), this);
		super.onResume();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.group, container, false);

		//Contact Badges Instanciate
		QuickContactBadge badge1 = (QuickContactBadge) view.findViewById(R.id.quickContactBadge1);
		badge1.setBackgroundResource(R.drawable.default_user_icon);
		badge1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) 
			{
				onShowContact(v);
			}
		});

		QuickContactBadge badge2 = (QuickContactBadge) view.findViewById(R.id.quickContactBadge2);
		badge2.setBackgroundResource(R.drawable.default_user_icon);
		badge2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) 
			{
				onShowContact(v);
			}
		});

		QuickContactBadge badge3 = (QuickContactBadge) view.findViewById(R.id.quickContactBadge3);
		badge3.setBackgroundResource(R.drawable.default_user_icon);
		badge3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) 
			{
				onShowContact(v);
			}
		});

		QuickContactBadge badge4 = (QuickContactBadge) view.findViewById(R.id.quickContactBadge4);
		badge4.setBackgroundResource(R.drawable.default_user_icon);
		badge4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) 
			{
				onShowContact(v);
			}
		});

		QuickContactBadge badge5 = (QuickContactBadge) view.findViewById(R.id.quickContactBadge5);
		badge5.setBackgroundResource(R.drawable.default_user_icon);
		badge5.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) 
			{
				onShowContact(v);
			}
		});

		//Set Current Points
		TextView currentPoints1 = (TextView) view.findViewById(R.id.currentPoints1);
		currentPoints1.setText(String.valueOf(member1.calculateAllPoints()));

		TextView currentPoints2 = (TextView) view.findViewById(R.id.currentPoints2);
		currentPoints2.setText(String.valueOf(member2.calculateAllPoints()));

		TextView currentPoints3 = (TextView) view.findViewById(R.id.currentPoints3);
		currentPoints3.setText(String.valueOf(member3.calculateAllPoints()));

		TextView currentPoints4 = (TextView) view.findViewById(R.id.currentPoints4);
		currentPoints4.setText(String.valueOf(member4.calculateAllPoints()));

		TextView currentPoints5 = (TextView) view.findViewById(R.id.currentPoints5);
		currentPoints5.setText(String.valueOf(member5.calculateAllPoints()));

		//Set TargetPoints
		TextView targetPoints1 = (TextView) view.findViewById(R.id.targetPoints1);
		targetPoints1.setText(String.valueOf(member1.calculateWeeklyTargetPoints()));

		TextView targetPoints2 = (TextView) view.findViewById(R.id.targetPoints2);
		targetPoints2.setText(String.valueOf(member2.calculateWeeklyTargetPoints()));

		TextView targetPoints3 = (TextView) view.findViewById(R.id.targetPoints3);
		targetPoints3.setText(String.valueOf(member3.calculateWeeklyTargetPoints()));

		TextView targetPoints4 = (TextView) view.findViewById(R.id.targetPoints4);
		targetPoints4.setText(String.valueOf(member4.calculateWeeklyTargetPoints()));

		TextView targetPoints5 = (TextView) view.findViewById(R.id.targetPoints5);
		targetPoints5.setText(String.valueOf(member5.calculateWeeklyTargetPoints()));

		//Progressbars Instantiate
		ProgressBar progressBar1 = (ProgressBar) view.findViewById(R.id.progressBar1);
		progressBar1.setProgress(member1.calculateWeeklyPercentage());

		ProgressBar progressBar2 = (ProgressBar) view.findViewById(R.id.progressBar2);
		progressBar2.setProgress(member2.calculateWeeklyPercentage());

		ProgressBar progressBar3 = (ProgressBar) view.findViewById(R.id.progressBar3);
		progressBar3.setProgress(member3.calculateWeeklyPercentage());

		ProgressBar progressBar4 = (ProgressBar) view.findViewById(R.id.progressBar4);
		progressBar4.setProgress(member4.calculateWeeklyPercentage());

		ProgressBar progressBar5 = (ProgressBar) view.findViewById(R.id.progressBar5);
		progressBar5.setProgress(member5.calculateWeeklyPercentage());

		//EditText load names
		TextView editText1 = (TextView) view.findViewById(R.id.username1);
		editText1.setText(group.groupMembers.get(0).user_name);

		TextView editText2 = (TextView) view.findViewById(R.id.username2);
		editText2.setText(group.groupMembers.get(1).user_name);

		TextView editText3 = (TextView) view.findViewById(R.id.username3);
		editText3.setText(group.groupMembers.get(2).user_name);

		TextView editText4 = (TextView) view.findViewById(R.id.username4);
		editText4.setText(group.groupMembers.get(3).user_name);

		TextView editText5 = (TextView) view.findViewById(R.id.username5);
		editText5.setText(group.groupMembers.get(4).user_name);

		TextView groupName = (TextView) view.findViewById(R.id.groupName);
		groupName.setText(group.group_name);

		return view;
	}

	@Override
	public void eventA() {
		System.out.println("testEventStartetInClass: "+this.getClass().getName());
	}

	@Override
	public void onStop() {
		SportMateApplication.getApplication().unregisterListener(this.getClass().getName());
		super.onStop();
	}

	private void loadDataFromDB(int user_id){
		group = DBHandler.getGroupFromUser(user_id); 
		group.groupMembers = DBHandler.getUsersFromGroup(group.group_id);

		member1 = group.groupMembers.get(0);
		member1.weeklyTargets = DBHandler.getWeeklyTargetsFromUser(member1.user_id);
		member1.activities = DBHandler.getWeeklyActivitiesFromUser(member1.user_id);

		member2 = group.groupMembers.get(1);
		member2.weeklyTargets = DBHandler.getWeeklyTargetsFromUser(member2.user_id);
		member2.activities = DBHandler.getWeeklyActivitiesFromUser(member2.user_id);

		member3 = group.groupMembers.get(2);
		member3.weeklyTargets = DBHandler.getWeeklyTargetsFromUser(member3.user_id);
		member3.activities = DBHandler.getWeeklyActivitiesFromUser(member3.user_id);

		member4 = group.groupMembers.get(3);
		member4.weeklyTargets = DBHandler.getWeeklyTargetsFromUser(member4.user_id);
		member4.activities = DBHandler.getWeeklyActivitiesFromUser(member4.user_id);

		member5 = group.groupMembers.get(4);
		member5.weeklyTargets = DBHandler.getWeeklyTargetsFromUser(member5.user_id);
		member5.activities = DBHandler.getWeeklyActivitiesFromUser(member5.user_id);
	}

	public void saveDate()
	{
		Log.d(TAG, "I am in GroupFragment");
	}

	private void onShowContact(View v)
	{
		switch (v.getId()) 
		{
		case R.id.quickContactBadge1:
			AppData.getInstance().setCurrentViewedMember(member1);
			MainActivity.selectUser();
			break;
		case R.id.quickContactBadge2:
			AppData.getInstance().setCurrentViewedMember(member2);
			MainActivity.selectUser();
			break;
		case R.id.quickContactBadge3:
			AppData.getInstance().setCurrentViewedMember(member3);
			MainActivity.selectUser();
			break;
		case R.id.quickContactBadge4:
			AppData.getInstance().setCurrentViewedMember(member4);
			MainActivity.selectUser();
			break;
		case R.id.quickContactBadge5:
			AppData.getInstance().setCurrentViewedMember(member5);
			MainActivity.selectUser();
			break;
		}
	}
}