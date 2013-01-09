package at.ac.tuwien.sportmate;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends FragmentActivity implements
ActionBar.TabListener {
	private static String myTab = "";
	private final static String TAG = "MainActivity";
	private static MenuItem item;
	private static MenuItem item2;
	private static Fragment myCurrentFragment = null;
	private static ActionBar actionBar;

	private static Tab activityStart;
	private static Tab start;
	private static Tab user; // my profile
	private static boolean showMember; //true if user selects a member from GroupFragment 
	private static boolean running;

	private static ViewPager mViewPager;

	AppData data;

	NotificationManager mNotificationManager;

	static AppSectionsPagerAdapter mAppSectionsPagerAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// load all weekly data From DB
		data = AppData.getInstance();
		data.setCurrentMember(DBHandler.getGroupMember(1));
		data.loadData();

		// check DB for active members and write into appData
		this.countActiveGroupMembers();

		mNotificationManager = (NotificationManager) this
				.getSystemService(Context.NOTIFICATION_SERVICE);

		// Create the adapter that will return a fragment for each of the three
		// primary sections
		// of the app.
		mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(
				getSupportFragmentManager());

		// setup action bar for tabs
		actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayShowHomeEnabled(false);

		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mAppSectionsPagerAdapter);
		mViewPager
		.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				// When swiping between pages, select the
				// corresponding tab.
				getActionBar().setSelectedNavigationItem(position);
			}
		});
		
		//This means that the ViewPager will keep the Fragments in memory as long as they are less than three tabs away from the currently selected one
		mViewPager.setOffscreenPageLimit(3);

		start = actionBar.newTab().setText("Start").setTabListener(this);
		actionBar.addTab(start);

		user = actionBar.newTab().setText("User").setTabListener(this);
		actionBar.addTab(user);

		activityStart = actionBar.newTab().setText("Start")
				.setTabListener(this);

		Tab tab = actionBar.newTab().setText("Group").setTabListener(this);
		actionBar.addTab(tab);

		myTab = "Start";

		/*
		 * tab = actionBar .newTab() .setText("Target") .setTabListener( new
		 * MyTabListener<SelectTargetFragment>(this, "Target",
		 * SelectTargetFragment.class)); actionBar.addTab(tab);
		 */

	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());

		// MenuItem handling
		myTab = tab.getText().toString();

		if (item != null && item2 != null) 
		{
			item.setTitle("");
			item.setEnabled(false);
			item2.setTitle("");
			item2.setEnabled(false);

			if (myTab == "Start" && running) 
			{
				item.setEnabled(true);
				item.setTitle("Pause");
				item2.setEnabled(true);
				item2.setTitle("Stop");
			} else if (myTab == "User") 
			{
			} else if (myTab == "Group") {
				item.setTitle("");
				item.setEnabled(false);
				item2.setEnabled(true);
				item2.setTitle("Edit");
			}
			if (myTab == "User" && showMember) {
				item.setTitle("");
				item.setEnabled(false);
				item2.setEnabled(true);
				item2.setTitle("My Profile");
			}
		}
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	// Add Options Menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.options_menu, menu);
		item = menu.findItem(R.id.menu_item1);
		item2 = menu.findItem(R.id.menu_item2);
		item.setTitle("");
		item2.setTitle("");
		return true;
	}

	public static void selectUser() {
		showMember = true;
		SingleStatistic single = (SingleStatistic) mAppSectionsPagerAdapter
				.getActiveFragment(mViewPager, 1);
		single.updateView();
		mViewPager.setCurrentItem(1);
	}

	public static void showStart() 
	{
		item.setEnabled(true);
		item.setTitle("Pause");
		item2.setEnabled(true);
		item2.setTitle("Stop");
		running = true;
	}

	public static void showActivityStart() {
		actionBar.selectTab(activityStart);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		if (myTab == "Group") 
		{
			GroupFragment gf = (GroupFragment) mAppSectionsPagerAdapter
					.getActiveFragment(mViewPager, 2);

			switch (item.getItemId()) {
			case R.id.menu_item1:
				return true;
			case R.id.menu_item2:
				gf.saveData();
				return true;
			}
		} else if (showMember) {
			switch (item.getItemId()) {
			case R.id.menu_item1:
				return true;
			case R.id.menu_item2:
				data.setCurrentViewedMember(data.getCurrentMember());
				SingleStatistic single = (SingleStatistic) mAppSectionsPagerAdapter
						.getActiveFragment(mViewPager, 1);
				single.updateView();
				showMember = false;
				item2.setTitle("");
				item2.setEnabled(false);
				return true;
			}
		} else if (running && myTab == "Start")
		{
			StartFragment start = (StartFragment) mAppSectionsPagerAdapter
					.getActiveFragment(mViewPager, 0);
			switch (item.getItemId()) 
			{
			case R.id.menu_item1:
				return true;
			case R.id.menu_item2:
				start.stopActivity();
				running = false;
				return true;
			}
		}
		return false;
	}

	public static class AppSectionsPagerAdapter extends FragmentPagerAdapter {
		FragmentManager mFragmentManager;

		public AppSectionsPagerAdapter(FragmentManager fm) {
			super(fm);
			mFragmentManager = fm;
		}

		public FragmentManager getFragmentManager() {
			return mFragmentManager;
		}

		public android.support.v4.app.Fragment getItem(int i) {
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
		public int getCount() {
			return 3;
		}

		public Fragment getActiveFragment(ViewPager container, int position) {
			String name = makeFragmentName(container.getId(), position);
			return mFragmentManager.findFragmentByTag(name);
		}

		private static String makeFragmentName(int viewId, int index) {
			return "android:switcher:" + viewId + ":" + index;
		}
	}

	public void countActiveGroupMembers() {

		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				while (true) {

					// da DB-fkt aufrufen
					BoGroupMember member = AppData.getInstance()
							.getCurrentMember();
					if (member != null) {
						//System.out.println(member.toString());

						ArrayList<BoGroupMember> activeMembers = DBHandler.getActiveGroupMembers(
								member.user_id, member.group_id);

						boolean notify = false;
						if (activeMembers.size() != AppData.getInstance()
								.getActiveGroupMemberCount() && activeMembers.size()>0) {
							notify = true;
						}

						if (activeMembers.size() == 0) mNotificationManager.cancel(1);

						AppData.getInstance().setActiveMembers(activeMembers);
						AppData.getInstance().setActiveGroupMemberCount(activeMembers.size());

						if (notify) {
							showNotificationForActiveMembers();
						}




					}

					try {
						// refresh intervall
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		};
		new Thread(runnable).start();

	}

	public void showNotificationForActiveMembers() {
		int mId = 1;

		String s = "";
		for(BoGroupMember m: AppData.getInstance().getActiveMembers()){
			s += " " + m.user_name;
		}
		if (AppData.getInstance().getActiveMembers().size()>1){
			s += " machen gerade Sport";
		} else {
			s += " macht gerade Sport";
		}

		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				this)
		.setSmallIcon(R.drawable.logo_app)
		.setContentTitle(s)
		.setContentText("Mach jetzt mit und sicher dir Bonuspunkte");

		// Creates an explicit intent for an Activity in your app
		Intent resultIntent = new Intent(this, MainActivity.class);

		// The stack builder object will contain an artificial back stack for
		// the
		// started Activity.
		// This ensures that navigating backward from the Activity leads out of
		// your application to the Home screen.
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		// Adds the back stack for the Intent (but not the Intent itself)
		stackBuilder.addParentStack(MainActivity.class);
		// Adds the Intent that starts the Activity to the top of the stack
		stackBuilder.addNextIntent(resultIntent);
		PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
				PendingIntent.FLAG_UPDATE_CURRENT);
		//mBuilder.setContentIntent(resultPendingIntent);



		// added:
		Notification notification = mBuilder.build();
		// Will show lights and make the notification disappear when the presses
		// it
		notification.flags |= Notification.FLAG_AUTO_CANCEL
				| Notification.FLAG_SHOW_LIGHTS;

		// mId allows you to update the notification later on.
		mNotificationManager.notify(mId, notification);

	}
	
	@Override
	public void onStop(){
		DBHandler.setActive(AppData.getInstance().getCurrentMember().getUser_id(), 0);
		super.onStop();
		
	}
}