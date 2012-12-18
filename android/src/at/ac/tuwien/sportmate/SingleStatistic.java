package at.ac.tuwien.sportmate;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SingleStatistic extends Fragment implements EventInterface {

	int memberId;
	BoGroupMember member;
	
	@Override
	public void eventA() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onCreate (Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		this.loadDataFromDB(1); //TODO
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.singlestat, container, false);
		
		
		//Username Header
		EditText username = (EditText) view.findViewById(R.id.userName);
		username.setText(member.getUser_name());
		
		//Current Minutes Views
		TextView currentMinutes1 = (TextView) view.findViewById(R.id.currentMinutes1);
		currentMinutes1.setText(String.valueOf(member.getCategoryMinutes(1)));
		
		TextView currentMinutes2 = (TextView) view.findViewById(R.id.currentMinutes2);
		currentMinutes2.setText(String.valueOf(member.getCategoryMinutes(2)));
		
		TextView currentMinutes3 = (TextView) view.findViewById(R.id.currentMinutes3);
		currentMinutes3.setText(String.valueOf(member.getCategoryMinutes(3)));
		
		TextView currentMinutes4 = (TextView) view.findViewById(R.id.currentMinutes4);
		currentMinutes4.setText(String.valueOf(member.getCategoryMinutes(4)));
		
		TextView currentMinutes5 = (TextView) view.findViewById(R.id.currentMinutes5);
		currentMinutes5.setText(String.valueOf(member.getCategoryMinutes(5)));
		
		//Target Minutes Views
		TextView targetMinutes1 = (TextView) view.findViewById(R.id.targetMinutes1);
		targetMinutes1.setText(String.valueOf(member.getWeeklyTargetCategoryMins(1)));
		
		TextView targetMinutes2 = (TextView) view.findViewById(R.id.targetMinutes2);
		targetMinutes2.setText(String.valueOf(member.getWeeklyTargetCategoryMins(2)));
		
		TextView targetMinutes3 = (TextView) view.findViewById(R.id.targetMinutes3);
		targetMinutes3.setText(String.valueOf(member.getWeeklyTargetCategoryMins(3)));
		
		TextView targetMinutes4 = (TextView) view.findViewById(R.id.targetMinutes4);
		targetMinutes4.setText(String.valueOf(member.getWeeklyTargetCategoryMins(4)));
		
		TextView targetMinutes5 = (TextView) view.findViewById(R.id.targetMinutes5);
		targetMinutes5.setText(String.valueOf(member.getWeeklyTargetCategoryMins(5)));
		
		//Progress bars
		ProgressBar progressBar1 = (ProgressBar)view.findViewById(R.id.progressBar1);
		progressBar1.setProgress(member.calculateWeeklyCategoryPercentage(1));
		
		ProgressBar progressBar2 = (ProgressBar)view.findViewById(R.id.progressBar2);
		progressBar2.setProgress(member.calculateWeeklyCategoryPercentage(2));
		
		ProgressBar progressBar3 = (ProgressBar)view.findViewById(R.id.progressBar3);
		progressBar3.setProgress(member.calculateWeeklyCategoryPercentage(3));
		
		ProgressBar progressBar4 = (ProgressBar)view.findViewById(R.id.progressBar4);
		progressBar4.setProgress(member.calculateWeeklyCategoryPercentage(4));
		
		ProgressBar progressBar5 = (ProgressBar)view.findViewById(R.id.progressBar5);
		progressBar5.setProgress(member.calculateWeeklyCategoryPercentage(5));
		
		return view;
	}
	
	private void loadDataFromDB(int user_id){
		memberId = user_id; 
		member = DBHandler.getGroupMember(memberId); 
		member.activities = DBHandler.getWeeklyActivitiesFromUser(memberId);
		member.weeklyTargets = DBHandler.getWeeklyTargetsFromUser(memberId);
	}
}
