package at.ac.tuwien.sportmate;

import java.sql.Date;
import java.sql.Time;

public class BoActivity {


	int activity_id; 
	int user_id;
	int group_id;
	Date date; 
	Time starttime; 
	int duration_min; 
	double intensity; 
	double points; 
	double bonus_points;
	BoCategory category;
	
	BoActivity() {
		
	}
	
	BoActivity(int activity_id, int user_id, int group_id, Date date, Time time, int duration_min, double intensity, double points, double bonus_points)
	{
		this.activity_id = activity_id; 
		this.user_id = user_id;
		this.group_id = group_id;
		this.date = date; 
		this.starttime = time; 
		this.duration_min = duration_min; 
		this.intensity = intensity; 
		this.points = points; 
		this.bonus_points = bonus_points; 
	}
	
	public int getActivity_id() {
		return activity_id;
	}
	public void setActivity_id(int activity_id) {
		this.activity_id = activity_id;
	}
	
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getGroup_id() {
		return group_id;
	}

	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Time getStarttime() {
		return starttime;
	}
	public void setStarttime(Time starttime) {
		this.starttime = starttime;
	}
	public int getDuration_min() {
		return duration_min;
	}
	public void setDuration_min(int duration_min) {
		this.duration_min = duration_min;
	}
	public double getIntensity() {
		return intensity;
	}
	public void setIntensity(double intensity) {
		this.intensity = intensity;
	}
	public double getPoints() {
		return points;
	}
	public void setPoints(double points) {
		this.points = points;
	}
	public double getBonus_points() {
		return bonus_points;
	}
	public void setBonus_points(double bonus_points) {
		this.bonus_points = bonus_points;
	}

	public BoCategory getCategory() {
		return category;
	}

	public void setCategory(BoCategory category) {
		this.category = category;
	} 
}
