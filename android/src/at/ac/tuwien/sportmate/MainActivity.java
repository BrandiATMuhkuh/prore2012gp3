package at.ac.tuwien.sportmate;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends Activity
{
	private static String myTab = "";
	private final static String TAG = "MainActivity";
	private static Menu menu;
	private static MenuItem item;
	private static MenuItem item2;
	private static Fragment myCurrentFragment = null;
	private static ActionBar actionBar;
	private static Tab member; //Profile of other members
	private static Tab user; //my profile
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// setup action bar for tabs
		actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayShowHomeEnabled(false);

		Tab tab = actionBar
				.newTab()
				.setText("Start")
				.setTabListener(
						new MyTabListener<StartFragment>(this, "Start",
								StartFragment.class));
		actionBar.addTab(tab);
		
		user = actionBar
				.newTab()
				.setText("User")
				.setTabListener(
						new MyTabListener<SingleStatistic>(this, "User",
								SingleStatistic.class));
		actionBar.addTab(user);
		
		member = actionBar
				.newTab()
				.setText("GroupMember")
				.setTabListener(
						new MyTabListener<SingleStatistic>(this, "GroupMember",
								SingleStatistic.class));
		//actionBar.addTab(user);


		tab = actionBar
				.newTab()
				.setText("Group")
				.setTabListener(
						new MyTabListener<GroupFragment>(this, "Group",
								GroupFragment.class));
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


	//Add Options Menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		this.menu = menu;
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.options_menu, menu);
		item = menu.findItem(R.id.menu_item1);
		item2 = menu.findItem(R.id.menu_item2);
		item.setTitle("");
		item2.setTitle("");
		
		return true;
	}

	public static class MyTabListener<T extends Fragment> implements
	TabListener 
	{
		private Fragment mFragment;
		private final Activity mActivity;
		private final String mTag;
		private final Class<T> mClass;

		/**
		 * Constructor used each time a new tab is created.
		 * 
		 * @param activity
		 *            The host Activity, used to instantiate the fragment
		 * @param tag
		 *            The identifier tag for the fragment
		 * @param clz
		 *            The fragment's Class, used to instantiate the fragment
		 */
		public MyTabListener(Activity activity, String tag, Class<T> clz) {
			mActivity = activity;
			mTag = tag;
			mClass = clz;
		}

		/* The following are each of the ActionBar.TabListener callbacks */

		public void onTabSelected(Tab tab, FragmentTransaction ft) 
		{

			// Check if the fragment is already initialized
			if (mFragment == null) 
			{
				// If not, instantiate and add it to the activity
				mFragment = Fragment.instantiate(mActivity, mClass.getName());
				ft.add(android.R.id.content, mFragment, mTag);
			} else {
				// If it exists, simply attach it in order to show it
				ft.setCustomAnimations(android.R.animator.fade_in,
						R.animator.animationtest);
				ft.attach(mFragment);
			}
			myCurrentFragment = mFragment;
			
			//MenuItem handling
			myTab = tab.getText().toString();
			Log.i(TAG, myTab);
			if(menu != null)
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
				} else if(myTab == "Target")
				{
					item.setTitle("");
					item2.setTitle("");
				} else if(myTab == "GroupMember")
				{
					item.setTitle("");
					item2.setTitle("My Profile");
				}

			}
		}

		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			if (mFragment != null) {
				ft.setCustomAnimations(android.R.animator.fade_in,
						R.animator.test);
				ft.detach(mFragment);
			}
		}

		public void onTabReselected(Tab tab, FragmentTransaction ft) {
		}
	}
	
	public static void selectUser()
	{
		actionBar.selectTab(member);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		if(myTab.equals("Group"))
		{
			GroupFragment groupFragment = (GroupFragment) myCurrentFragment;

			switch (item.getItemId()) 
		    {
		        case R.id.menu_item1:
		            return true;
		        case R.id.menu_item2:
		        	groupFragment.saveData();
		            return true;
		    }
		} else if(myTab.equals("GroupMember"))
		{
			switch (item.getItemId()) 
		    {
		        case R.id.menu_item1:
		            return true;
		        case R.id.menu_item2:
		        	actionBar.selectTab(user);
		            return true;
		    }
		}
	    return false;
	}
}