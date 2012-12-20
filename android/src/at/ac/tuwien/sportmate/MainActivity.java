package at.ac.tuwien.sportmate;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends FragmentActivity implements ActionBar.TabListener
{
	private static String myTab = "";
	private final static String TAG = "MainActivity";
	private static MenuItem item;
	private static MenuItem item2;
	private static Fragment myCurrentFragment = null;
	private static ActionBar actionBar;

	private static Tab activityStart; 
	private static Tab start; 
	private static Tab user; //my profile
	private static boolean showMember;

	private static ViewPager mViewPager;
	
	AppData data;

	static AppSectionsPagerAdapter mAppSectionsPagerAdapter;


	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		//load all weekly data From DB
		data = AppData.getInstance();
		data.setCurrentMember(DBHandler.getGroupMember(1));
		data.loadData();

		// Create the adapter that will return a fragment for each of the three primary sections
		// of the app.
		mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(getSupportFragmentManager());

		// setup action bar for tabs
		actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayShowHomeEnabled(false);

		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mAppSectionsPagerAdapter);
		mViewPager.setOnPageChangeListener(
				new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) 
					{
						// When swiping between pages, select the
						// corresponding tab.
						getActionBar().setSelectedNavigationItem(position);
					}
				});

		start = actionBar
				.newTab()
				.setText("Start")
				.setTabListener(this);
		actionBar.addTab(start);

		user = actionBar
				.newTab()
				.setText("User")
				.setTabListener(this);
		actionBar.addTab(user);

		activityStart =  actionBar
				.newTab()
				.setText("Start")
				.setTabListener(this);

		Tab tab = actionBar
				.newTab()
				.setText("Group")
				.setTabListener(this);
		actionBar.addTab(tab);


		myTab = tab.getText().toString();

		/*tab = actionBar
				.newTab()
				.setText("Target")
				.setTabListener(
						new MyTabListener<SelectTargetFragment>(this, "Target",
								SelectTargetFragment.class));
		actionBar.addTab(tab);*/

	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) 
	{
		// When the given tab is selected, switch to the corresponding page in the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());

		//MenuItem handling
		myTab = tab.getText().toString();

		if(item != null && item2 != null)
		{
			if(myTab == "Start")
			{
				item.setTitle("");
				item2.setTitle("");
			} else if(myTab == "User")
			{
				item.setTitle("");
				item2.setTitle("");
			} else if(myTab == "Group")
			{
				item.setTitle("");
				item2.setTitle("Edit");
			}if(showMember)
			{
				item.setTitle("");
				item2.setTitle("My Profile");
			}
		}
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
	}

	//Add Options Menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.options_menu, menu);
		item = menu.findItem(R.id.menu_item1);
		item2 = menu.findItem(R.id.menu_item2);
		item.setTitle("");
		item2.setTitle("");
		return true;
	}

	public static void selectUser()
	{
		showMember = true;
		SingleStatistic single = (SingleStatistic) mAppSectionsPagerAdapter.getActiveFragment(mViewPager, 1);
		single.updateView();
		mViewPager.setCurrentItem(1);
	}

	public static void showStart()
	{
		mViewPager.setCurrentItem(0);
	}

	public static void showActivityStart() 
	{
		actionBar.selectTab(activityStart); 
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{		
		if(myTab.equals("Group"))
		{
			GroupFragment gf = (GroupFragment) mAppSectionsPagerAdapter.getActiveFragment(mViewPager, 2);

			switch (item.getItemId()) 
			{
			case R.id.menu_item1:
				return true;
			case R.id.menu_item2:
				gf.saveData();
				return true;
			}
		} else if(showMember)
		{
			switch (item.getItemId()) 
			{
			case R.id.menu_item1:
				return true;
			case R.id.menu_item2:
				data.setCurrentViewedMember(data.getCurrentMember());
				SingleStatistic single = (SingleStatistic) mAppSectionsPagerAdapter.getActiveFragment(mViewPager, 1);
				single.updateView();
				showMember = false;
				item2.setTitle("");
				return true;
			}
		}
		return false;
	}

	public static class AppSectionsPagerAdapter extends FragmentPagerAdapter 
	{
		FragmentManager mFragmentManager;
		
		public AppSectionsPagerAdapter(FragmentManager fm) {
			super(fm);
			mFragmentManager = fm;
		}
		
		public FragmentManager getFragmentManager()
		{
			return mFragmentManager;
		}

		public android.support.v4.app.Fragment getItem(int i) 
		{
			switch (i) {
			case 0:
				return new StartFragment();
			case 1:
				return new SingleStatistic();
			case 2:
				return new GroupFragment();
			default:
				return new StartFragment();
			}
		}

		@Override
		public int getCount() 
		{
			return 3;
		}

		public Fragment getActiveFragment(ViewPager container, int position) 
		{
			String name = makeFragmentName(container.getId(), position);
			return  mFragmentManager.findFragmentByTag(name);
		}

		private static String makeFragmentName(int viewId, int index) {
			return "android:switcher:" + viewId + ":" + index;
		}
	}
}