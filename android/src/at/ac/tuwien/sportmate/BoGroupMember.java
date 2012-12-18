package at.ac.tuwien.sportmate;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;

public class BoGroupMember {

	int group_id;
	int user_id;
	String user_name;
	Date user_joining_date;
	Date user_group_joining_date;
	Date user_group_leaving_date;
	int default_activity;

	List<BoActivity> activities;
	List<BoWeeklyTarget> weeklyTargets;

	public BoGroupMember() {

	}

	public List<BoActivity> getActivities() {
		return activities;
	}

	public void setActivities(List<BoActivity> activities) {
		this.activities = activities;
	}

	public BoGroupMember(int group_id, int user_id, String user_name,
			Date user_joining_date, Date user_group_joining_date,
			Date user_group_leaving_date, int default_activity) {
		this.group_id = group_id;
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_joining_date = user_joining_date;
		this.user_group_joining_date = user_group_joining_date;
		this.user_group_leaving_date = user_group_leaving_date;
		this.default_activity = default_activity;

	}

	public int getGroup_id() {
		return group_id;
	}

	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Date getUser_joining_date() {
		return user_joining_date;
	}

	public void setUser_joining_date(Date user_joining_date) {
		this.user_joining_date = user_joining_date;
	}

	public Date getUser_group_joining_date() {
		return user_group_joining_date;
	}

	public void setUser_group_joining_date(Date user_group_joining_date) {
		this.user_group_joining_date = user_group_joining_date;
	}

	public Date getUser_group_leaving_date() {
		return user_group_leaving_date;
	}

	public void setUser_group_leaving_date(Date user_group_leaving_date) {
		this.user_group_leaving_date = user_group_leaving_date;
	}

	public int getDefault_activity() {
		return default_activity;
	}

	public void setDefault_activity(int default_activity) {
		this.default_activity = default_activity;
	}

	public int calculateWeeklyTargetPoints() {

		int sum = 0;
		for (BoWeeklyTarget w : this.weeklyTargets) {
			sum += w.getCategory().getCategory_intensity()
					* w.weekly_target_min;
		}
		return sum;
	}

	public int getWeeklyTargetCategoryMins(int category_id) {
		int mins = 0;
		String name = "category_name";
		for (BoWeeklyTarget w : weeklyTargets) {
			if (w.category.category_id == category_id) {
				mins = w.weekly_target_min;
				name = w.category.category_name;
			}
		}
		//Log.d(name + " target mins: ", "" + mins);
		return mins;
	}

	public int calculateAllPoints() {

		int points = 0;

		for (BoActivity a : activities) {
			points += a.category.category_intensity * a.duration_min;
		}

		return points;
	}
	
	public int getCategoryMinutes(int category_id) {

		int minutes = 0;

		for (BoActivity a : activities) {
			if (a.category.category_id == category_id) {
				minutes += a.duration_min;
			}
		}

		return minutes;
	}
	
	public int calculateWeeklyPercentage(){
		
		double percentage = ((double)calculateAllPoints()/(double)calculateWeeklyTargetPoints()) * 100;
		
		if (percentage > 100) return 100;
		else return (int)percentage;
		
	}
	
	public int calculateWeeklyCategoryPercentage(int category_id){
		
		double percentage = ((double)getCategoryMinutes(category_id)/(double)getWeeklyTargetCategoryMins(category_id)) * 100;
	
		Log.d("Test", "calculateWeeklyCategoryPercentage: " + percentage);
		if (percentage > 100) return 100;
		else return (int)percentage;
	}
	
	public int calculateWeeklyCategoryPoints(int category_id){
		int points = 0;
		
		for (BoActivity a: activities){
			if (a.category.category_id == category_id){
				points += a.duration_min * a.category.category_intensity;
			}
		}
		
		return points;
	}
	
	public int calculateWeeklyCategoryTargetPoints(int category_id){
		int points = 0;
		
		for (BoWeeklyTarget w: weeklyTargets){
			if (w.category.category_id == category_id){
				points += w.weekly_target_min * w.category.category_intensity;
			}
		}
		
		return points;
	}
}
