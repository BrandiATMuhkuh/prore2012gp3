package at.ac.tuwien.sportmate;

import java.util.List;

public class BoActivities {

	BoCategory category;
	BoGroup group;
	BoGroupMember groupMember; 
	List<BoActivity> activities; 
	
	BoActivities()
	{
		
	}

	
	public BoCategory getCategory() {
		return category;
	}
	public void setCategory(BoCategory category) {
		this.category = category;
	}
	public BoGroup getGroup() {
		return group;
	}
	public void setGroup(BoGroup group) {
		this.group = group;
	}
	public BoGroupMember getGroupMember() {
		return groupMember;
	}
	public void setGroupMember(BoGroupMember groupMember) {
		this.groupMember = groupMember;
	}
	
}
