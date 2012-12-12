package at.ac.tuwien.sportmate;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
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
		

		/***
		 * OnTouchListener for Drag and Drop
		 */
		vg.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getActionMasked()) {
				case MotionEvent.ACTION_MOVE:
					int x = (int) event.getX() - offset_x;
					int y = (int) event.getY() - offset_y;

					int w = getActivity().getWindowManager().getDefaultDisplay().getWidth() - 100;
					int h = getActivity().getWindowManager().getDefaultDisplay().getHeight() - 100;
					if (x > w)
						x = w;
					if (y > h)
						y = h;
					LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
							new ViewGroup.MarginLayoutParams(
									LinearLayout.LayoutParams.WRAP_CONTENT,
									LinearLayout.LayoutParams.WRAP_CONTENT));
					lp.setMargins(x, y, 0, 0);

					selected_item.setLayoutParams(lp);
					break;
				default:
					break;
				}
				return true;
			}
		});
		
		ImageView img = (ImageView) view.findViewById(R.id.img);
		
		/***
		 * OnTouchListener for the image 
		 */
		img.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getActionMasked()) {
				case MotionEvent.ACTION_DOWN:
					offset_x = (int) event.getX();
					offset_y = (int) event.getY();
					selected_item = v;
					break;
				default:
					break;
				}

				return false;
			}
		});

		return view;
	}
}
