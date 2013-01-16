package at.ac.tuwien.sportmate;

import java.sql.Date;
import java.sql.Time;

public class BoWeeklyTarget {
	
	BoCategory category; 
	
	int weekly_target_min;
	Date target_changed_at_date;
	Time target_changed_at_time;

	public BoWeeklyTarget() 
	{
	
	}
	
	public BoWeeklyTarget(BoCategory category, int weekly_target_min, Date date, Time time) {
		this.category = category;
		this.target_changed_at_date = date;
		this.target_changed_at_time = time;
		this.weekly_target_min = weekly_target_min;
	}
	
	

	public BoCategory getCategory() {
		return category;
	}

	public void setCategory(BoCategory category) {
		this.category = category;
	}

	public int getWeekly_target_min() {
		return weekly_target_min;
	}

	public void setWeekly_target_min(int weekly_target_min) {
		this.weekly_target_min = weekly_target_min;
	}

	public Date getTarget_changed_at_date() {
		return target_changed_at_date;
	}

	public void setTarget_changed_at_date(Date target_changed_at_date) {
		this.target_changed_at_date = target_changed_at_date;
	}

	public Time getTarget_changed_at_time() {
		return target_changed_at_time;
	}

	public void setTarget_changed_at_time(Time target_changed_at_time) {
		this.target_changed_at_time = target_changed_at_time;
	}
	
}
