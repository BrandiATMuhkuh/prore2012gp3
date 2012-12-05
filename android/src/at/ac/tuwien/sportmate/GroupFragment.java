package at.ac.tuwien.sportmate;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.QuickContactBadge;
import android.widget.TextView;

public class GroupFragment extends Fragment implements EventInterface {
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
		
		QuickContactBadge badge6 = (QuickContactBadge) view.findViewById(R.id.quickContactBadge6);
		badge6.setBackgroundResource(R.drawable.default_user_icon);
		
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
		
		ProgressBar progressBar6 = (ProgressBar) view.findViewById(R.id.progressBar6);
		progressBar6.setProgress(50);
		
		return view;
	}

	@Override
	public void eventA() {
		System.out.println("testEventStartetInClass: "+this.getClass().getName());
	}
}