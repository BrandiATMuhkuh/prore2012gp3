package at.ac.tuwien.sportmate;

import java.util.ArrayList;

import org.w3c.dom.Text;

import android.net.ParseException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.QuickContactBadge;
import android.widget.TextView;

public class GroupFragment extends Fragment implements EventInterface, OnClickListener
{

	BoGroup group = new BoGroup();

	AppData data;
	
	private final static String TAG = "GroupFragment";

	BoGroupMember member1;
	BoGroupMember member2;
	BoGroupMember member3;
	BoGroupMember member4;
	BoGroupMember member5;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		data = AppData.getInstance();
		
		group = data.getCurrentGroup();

		member1 = data.getCurrentGroup().groupMembers.get(0);
		member2 = data.getCurrentGroup().groupMembers.get(1);
		member3 = data.getCurrentGroup().groupMembers.get(2);
		member4 = data.getCurrentGroup().groupMembers.get(3);
		member5 = data.getCurrentGroup().groupMembers.get(4);
		
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
		ImageView badge1 = (ImageView) view.findViewById(R.id.quickContactBadge1);
		badge1.setBackgroundResource(R.drawable.default_user_icon);
		badge1.setOnClickListener(this);

		ImageView badge2 = (ImageView) view.findViewById(R.id.quickContactBadge2);
		badge2.setBackgroundResource(R.drawable.default_user_icon);
		badge2.setOnClickListener(this);

		ImageView badge3 = (ImageView) view.findViewById(R.id.quickContactBadge3);
		badge3.setBackgroundResource(R.drawable.default_user_icon);
		badge3.setOnClickListener(this);

		ImageView badge4 = (ImageView) view.findViewById(R.id.quickContactBadge4);
		badge4.setBackgroundResource(R.drawable.default_user_icon);
		badge4.setOnClickListener(this);

		ImageView badge5 = (ImageView) view.findViewById(R.id.quickContactBadge5);
		badge5.setBackgroundResource(R.drawable.default_user_icon);
		badge5.setOnClickListener(this);
		
		
		LinearLayout linearLayout1 = (LinearLayout) view.findViewById(R.id.groupmember1);
		linearLayout1.setOnClickListener(this);
		linearLayout1.setBackgroundResource(R.drawable.groupmember_selector);
		
		LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.groupmember2);
		linearLayout2.setOnClickListener(this);
		linearLayout2.setBackgroundResource(R.drawable.groupmember_selector);
		
		LinearLayout linearLayout3 = (LinearLayout) view.findViewById(R.id.groupmember3);
		linearLayout3.setOnClickListener(this);
		linearLayout3.setBackgroundResource(R.drawable.groupmember_selector);
		
		LinearLayout linearLayout4 = (LinearLayout) view.findViewById(R.id.groupmember4);
		linearLayout4.setOnClickListener(this);
		linearLayout4.setBackgroundResource(R.drawable.groupmember_selector);
		
		LinearLayout linearLayout5 = (LinearLayout) view.findViewById(R.id.groupmember5);
		linearLayout5.setOnClickListener(this);
		linearLayout5.setBackgroundResource(R.drawable.groupmember_selector);

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
		progressBar1.setProgressDrawable(getResources().getDrawable(R.drawable.progress_horizontal));

		ProgressBar progressBar2 = (ProgressBar) view.findViewById(R.id.progressBar2);
		progressBar2.setProgress(member2.calculateWeeklyPercentage());
		progressBar2.setProgressDrawable(getResources().getDrawable(R.drawable.progress_horizontal));

		ProgressBar progressBar3 = (ProgressBar) view.findViewById(R.id.progressBar3);
		progressBar3.setProgress(member3.calculateWeeklyPercentage());
		progressBar3.setProgressDrawable(getResources().getDrawable(R.drawable.progress_horizontal));

		ProgressBar progressBar4 = (ProgressBar) view.findViewById(R.id.progressBar4);
		progressBar4.setProgress(member4.calculateWeeklyPercentage());
		progressBar4.setProgressDrawable(getResources().getDrawable(R.drawable.progress_horizontal));

		ProgressBar progressBar5 = (ProgressBar) view.findViewById(R.id.progressBar5);
		progressBar5.setProgress(member5.calculateWeeklyPercentage());
		progressBar5.setProgressDrawable(getResources().getDrawable(R.drawable.progress_horizontal));

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

	public void saveData()
	{
		Log.d(TAG, "I am in GroupFragment");
	}

	private void onShowContact(View v)
	{
		switch (v.getId()) 
		{
		case R.id.quickContactBadge1:
			data.setCurrentViewedMember(member1);
			MainActivity.selectUser();
			break;
		case R.id.quickContactBadge2:
			data.setCurrentViewedMember(member2);
			MainActivity.selectUser();
			break;
		case R.id.quickContactBadge3:
			data.setCurrentViewedMember(member3);
			MainActivity.selectUser();
			break;
		case R.id.quickContactBadge4:
			data.setCurrentViewedMember(member4);
			MainActivity.selectUser();
			break;
		case R.id.quickContactBadge5:
			data.setCurrentViewedMember(member5);
			MainActivity.selectUser();
			break;
		case R.id.groupmember1:
			data.setCurrentViewedMember(member1);
			MainActivity.selectUser();
			break;
		case R.id.groupmember2:
			data.setCurrentViewedMember(member2);
			MainActivity.selectUser();
			break;
		case R.id.groupmember3:
			data.setCurrentViewedMember(member3);
			MainActivity.selectUser();
			break;
		case R.id.groupmember4:
			data.setCurrentViewedMember(member4);
			MainActivity.selectUser();
			break;
		case R.id.groupmember5:
			data.setCurrentViewedMember(member5);
			MainActivity.selectUser();
			break;
		}
	}
	
	public void onClick(View v)
	{
		onShowContact(v);
	}
}	