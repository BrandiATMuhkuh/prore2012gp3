package at.ac.tuwien.sportmate;

import org.w3c.dom.Text;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.QuickContactBadge;
import android.widget.TextView;

public class GroupFragment extends Fragment implements EventInterface {
	
	BoGroup group = new BoGroup();
	
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
		View view = inflater.inflate(R.layout.group, container, false);
		
		
		
		//load group
		
		group = DBHandler.getGroupFromUser(1); //Flo
		group.groupMembers = DBHandler.getUsers(group.group_id);
		
		BoGroupMember member1 = group.groupMembers.get(0);
		member1.weeklyTargets = DBHandler.getWeeklyTargetsFromUser(member1.user_id);
		BoGroupMember member2 = group.groupMembers.get(1);
		member2.weeklyTargets = DBHandler.getWeeklyTargetsFromUser(member2.user_id);
		BoGroupMember member3 = group.groupMembers.get(2);
		member3.weeklyTargets = DBHandler.getWeeklyTargetsFromUser(member3.user_id);
		BoGroupMember member4 = group.groupMembers.get(3);
		member4.weeklyTargets = DBHandler.getWeeklyTargetsFromUser(member4.user_id);
		BoGroupMember member5 = group.groupMembers.get(4);
		member5.weeklyTargets = DBHandler.getWeeklyTargetsFromUser(member5.user_id);
		
		
		
		
		//Contact Badges Instanciate
		QuickContactBadge badge1 = (QuickContactBadge) view.findViewById(R.id.quickContactBadge1);
		badge1.setBackgroundResource(R.drawable.default_user_icon);
		
		QuickContactBadge badge2 = (QuickContactBadge) view.findViewById(R.id.quickContactBadge2);
		badge2.setBackgroundResource(R.drawable.default_user_icon);
		
		QuickContactBadge badge3 = (QuickContactBadge) view.findViewById(R.id.quickContactBadge3);
		badge3.setBackgroundResource(R.drawable.default_user_icon);
		
		QuickContactBadge badge4 = (QuickContactBadge) view.findViewById(R.id.quickContactBadge4);
		badge4.setBackgroundResource(R.drawable.default_user_icon);
		
		QuickContactBadge badge5 = (QuickContactBadge) view.findViewById(R.id.quickContactBadge5);
		badge5.setBackgroundResource(R.drawable.default_user_icon);
		
		//Points
		TextView targetPoints1 = (TextView) view.findViewById(R.id.targetPoints1);
		targetPoints1.setText(String.valueOf(member1.getWeeklyTargetPoints()));
		
		TextView targetPoints2 = (TextView) view.findViewById(R.id.targetPoints2);
		targetPoints2.setText(String.valueOf(member2.getWeeklyTargetPoints()));
		
		TextView targetPoints3 = (TextView) view.findViewById(R.id.targetPoints3);
		targetPoints3.setText(String.valueOf(member3.getWeeklyTargetPoints()));
		
		TextView targetPoints4 = (TextView) view.findViewById(R.id.targetPoints4);
		targetPoints4.setText(String.valueOf(member4.getWeeklyTargetPoints()));
		
		TextView targetPoints5 = (TextView) view.findViewById(R.id.targetPoints5);
		targetPoints5.setText(String.valueOf(member5.getWeeklyTargetPoints()));
		
		//Progressbars Instantiate
		ProgressBar progressBar1 = (ProgressBar) view.findViewById(R.id.progressBar1);
		progressBar1.setProgress(50);
		
		ProgressBar progressBar2 = (ProgressBar) view.findViewById(R.id.progressBar2);
		progressBar2.setProgress(50);
		
		ProgressBar progressBar3 = (ProgressBar) view.findViewById(R.id.progressBar3);
		progressBar3.setProgress(50);
		
		ProgressBar progressBar4 = (ProgressBar) view.findViewById(R.id.progressBar4);
		progressBar4.setProgress(50);
		
		ProgressBar progressBar5 = (ProgressBar) view.findViewById(R.id.progressBar5);
		progressBar5.setProgress(50);
		
		//EditText load names
		EditText editText1 = (EditText) view.findViewById(R.id.editText1);
		editText1.setText(group.groupMembers.get(0).user_name);
		
		EditText editText2 = (EditText) view.findViewById(R.id.editText2);
		editText2.setText(group.groupMembers.get(1).user_name);
		
		EditText editText3 = (EditText) view.findViewById(R.id.editText3);
		editText3.setText(group.groupMembers.get(2).user_name);
		
		EditText editText4 = (EditText) view.findViewById(R.id.editText4);
		editText4.setText(group.groupMembers.get(3).user_name);
		
		EditText editText5 = (EditText) view.findViewById(R.id.editText5);
		editText5.setText(group.groupMembers.get(4).user_name);
		
		EditText groupName = (EditText) view.findViewById(R.id.groupName);
		groupName.setText(group.group_name);
		
		
		
		return view;
	}

	@Override
	public void eventA() {
		System.out.println("testEventStartetInClass: "+this.getClass().getName());
	}
}