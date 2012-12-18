package at.ac.tuwien.sportmate;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class StartFragment extends Fragment implements EventInterface {
	 private View selected_item = null;
     private int offset_x = 0;
     private int offset_y = 0;
     
	@Override
	public void eventA() {
		// TODO Auto-generated method stub

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.start, container, false);


		ViewGroup vg = (ViewGroup) view.findViewById(R.id.vg);
		

		
		ImageView imgStart = (ImageView) view.findViewById(R.id.imgStart);
	
		
		/* klick auf startbutton */
		imgStart.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				MainActivity.showActivityStart(); 
			}
		});

		return view;
	}
}
