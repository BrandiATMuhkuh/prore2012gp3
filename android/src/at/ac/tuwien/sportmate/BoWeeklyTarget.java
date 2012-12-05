package at.ac.tuwien.sportmate;

public class BoWeeklyTarget {
	
	BoCategory category; 
	
	int weekly_target_min;

	public BoWeeklyTarget() 
	{
	
	}
	
	public BoWeeklyTarget(BoCategory category, int weekly_target_min) {
		this.category = category;
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
	
}
