package at.ac.tuwien.sportmate;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class StartFragment extends Fragment implements EventInterface, OnClickListener
{
	private View selected_item = null;
	private int offset_x = 0;
	private int offset_y = 0;
	
	View view;
	
	TextView header;

	ImageView image1;
	ImageView image2;
	ImageView image3;
	ImageView image4;
	ImageView image5;
	
	LinearLayout category1;
	LinearLayout category2;
	LinearLayout category3;
	LinearLayout category4;
	LinearLayout category5;
	
	ArrayList<LinearLayout> categoryViews;

	boolean animationDone = false;
	
	@Override
	public void eventA() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		
		categoryViews = new ArrayList<LinearLayout>();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.start, container, false);
		
		header = (TextView) view.findViewById(R.id.chooseCategory);
		
		category1 = (LinearLayout)view.findViewById(R.id.categoryChoose1);
		category2 = (LinearLayout)view.findViewById(R.id.categoryChoose2);
		category3 = (LinearLayout)view.findViewById(R.id.categoryChoose3);
		category4 = (LinearLayout)view.findViewById(R.id.categoryChoose4);
		category5 = (LinearLayout)view.findViewById(R.id.categoryChoose5);
		
		categoryViews.add(category1);
		categoryViews.add(category2);
		categoryViews.add(category3);
		categoryViews.add(category4);
		categoryViews.add(category5);

		image1 = (ImageView) view.findViewById(R.id.categoryImageMain1);
		image1.setOnClickListener(this);
		image2 = (ImageView) view.findViewById(R.id.categoryImageMain2);
		image2.setOnClickListener(this);
		image3 = (ImageView) view.findViewById(R.id.categoryImageMain3);
		image3.setOnClickListener(this);
		image4 = (ImageView) view.findViewById(R.id.categoryImageMain4);
		image4.setOnClickListener(this);
		image5 = (ImageView) view.findViewById(R.id.categoryImageMain5);
		image5.setOnClickListener(this);

		LinearLayout linearLayout1 = (LinearLayout) view.findViewById(R.id.categoryChoose1);
		linearLayout1.setOnClickListener(this);

		LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.categoryChoose2);
		linearLayout2.setOnClickListener(this);

		LinearLayout linearLayout3 = (LinearLayout) view.findViewById(R.id.categoryChoose3);
		linearLayout3.setOnClickListener(this);

		LinearLayout linearLayout4 = (LinearLayout) view.findViewById(R.id.categoryChoose4);
		linearLayout4.setOnClickListener(this);

		LinearLayout linearLayout5 = (LinearLayout) view.findViewById(R.id.categoryChoose5);
		linearLayout5.setOnClickListener(this);

		switch(AppData.getInstance().getDefaultSportCategory())
		{
		case 1:
			image1.setBackgroundResource(R.drawable.ausdauer_banner);
			break;
		case 2:
			image2.setBackgroundResource(R.drawable.kraft_banner);
			break;
		case 3:
			image3.setBackgroundResource(R.drawable.ball_banner);
			break;
		case 4:
			image4.setBackgroundResource(R.drawable.gymnastik_banner);
			break;
		case 5:
			image5.setBackgroundResource(R.drawable.leichte_banner);
			break;
		}

		return view;
	}

	public void onClick(View v)
	{
		switch (v.getId()) 
		{
		case R.id.categoryImageMain1:
			MainActivity.showActivityStart();
			break;
		case R.id.categoryImageMain2:
			MainActivity.showActivityStart();
			break;
		case R.id.categoryImageMain3:
			MainActivity.showActivityStart();
			break;
		case R.id.categoryImageMain4:
			MainActivity.showActivityStart();
			break;
		case R.id.categoryImageMain5:
			MainActivity.showActivityStart();
			break;
		case R.id.categoryChoose1:
			AppData.getInstance().setDefaultSportCategory(1);
			image1.setBackgroundResource(R.drawable.ausdauer_banner);
			image2.setBackgroundResource(R.drawable.kraft_square);
			image3.setBackgroundResource(R.drawable.ball_square);
			image4.setBackgroundResource(R.drawable.gymnastik_square);
			image5.setBackgroundResource(R.drawable.leichte_square);
			selectCategoryView(v);
			break;
		case R.id.categoryChoose2:
			AppData.getInstance().setDefaultSportCategory(2);
			image2.setBackgroundResource(R.drawable.kraft_banner);
			image1.setBackgroundResource(R.drawable.ausdauer_square);
			image3.setBackgroundResource(R.drawable.ball_square);
			image4.setBackgroundResource(R.drawable.gymnastik_square);
			image5.setBackgroundResource(R.drawable.leichte_square);
			selectCategoryView(v);
			break;
		case R.id.categoryChoose3:
			AppData.getInstance().setDefaultSportCategory(3);
			image3.setBackgroundResource(R.drawable.ball_banner);
			image1.setBackgroundResource(R.drawable.ausdauer_square);
			image2.setBackgroundResource(R.drawable.kraft_square);
			image4.setBackgroundResource(R.drawable.gymnastik_square);
			image5.setBackgroundResource(R.drawable.leichte_square);
			selectCategoryView(v);
			break;
		case R.id.categoryChoose4:
			AppData.getInstance().setDefaultSportCategory(4);
			image4.setBackgroundResource(R.drawable.gymnastik_banner);
			image1.setBackgroundResource(R.drawable.ausdauer_square);
			image2.setBackgroundResource(R.drawable.kraft_square);
			image3.setBackgroundResource(R.drawable.ball_square);
			image5.setBackgroundResource(R.drawable.leichte_square);
			selectCategoryView(v);
			break;
		case R.id.categoryChoose5:
			AppData.getInstance().setDefaultSportCategory(5);
			image5.setBackgroundResource(R.drawable.leichte_banner);
			image1.setBackgroundResource(R.drawable.ausdauer_square);
			image2.setBackgroundResource(R.drawable.kraft_square);
			image3.setBackgroundResource(R.drawable.ball_square);
			image4.setBackgroundResource(R.drawable.gymnastik_square);
			selectCategoryView(v);
			break;
		}
	}
	
	private void selectCategoryView(View v){
		
		final View selectedView = v;
		
		final float positionY = v.getY();
		
		
		
		
		
		final TranslateAnimation ta = new TranslateAnimation(0, 0, 0, -positionY);
		ta.setDuration(500);
		ta.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				header.setVisibility(View.GONE);
				for (LinearLayout ll: categoryViews) {
					if (ll.getId()!=selectedView.getId()){
						ll.setVisibility(View.GONE);
					}
				}
				
			}
		});
		
		final AlphaAnimation aa = new AlphaAnimation(1, 0);
		aa.setDuration(500);
		aa.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationEnd(Animation animation) {
				//after views disappeared, animate selected view to top
				
				header.setAlpha(0);
				for (LinearLayout ll: categoryViews){
					if (ll.getId()!=selectedView.getId()) {
						ll.setAlpha(0);
					}	
				}
				
				selectedView.startAnimation(ta);
				
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
			}
		});
		
		header.startAnimation(aa);
		for(LinearLayout ll: categoryViews){
			if (ll.getId() != v.getId()){
				ll.startAnimation(aa);
			}
		}
		
		
		
		
		
		
		
		
	}
}
