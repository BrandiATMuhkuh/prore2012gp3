package at.ac.tuwien.sportmate;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ImageFragment extends Fragment implements EventInterface {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		System.out.println("onCreate");
		super.onCreate(savedInstanceState);
		
		Log.e("Test", "hello");
	}
	
	@Override
	public void onResume() {
		System.out.println("onResume");
		//SportMateApplication.getApplication().registerListener(this.getClass().getName(), this);
		super.onResume();
	}
	
	@Override
	public void onStop() {
		System.out.println("onStop");
		//SportMateApplication.getApplication().unregisterListener(this.getClass().getName());
		super.onStop();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		System.out.println("onActivityCreated");
		super.onActivityCreated(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		System.out.println("onCreateViews");
		View view = inflater.inflate(R.layout.image, container, false);
		return view;
	}

	@Override
	public void eventA() {
		System.out.println("testEventStartetInClass: "+this.getClass().getName());
		
	}
}