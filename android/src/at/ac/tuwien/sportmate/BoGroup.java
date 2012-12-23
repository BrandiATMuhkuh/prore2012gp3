package at.ac.tuwien.sportmate;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

public class BoGroup {

	int group_id; 
	String group_name; 
	ArrayList<BoGroupMember> groupMembers; 



	BoGroup()
	{
		
	}


	public List<BoGroupMember> getGroupMembers() {
		return groupMembers;
	}


	public void setGroupMembers(ArrayList<BoGroupMember> groupMembers) {
		this.groupMembers = groupMembers;
	}

	BoGroup(int group_id, String group_name)
	{
		this.group_id = group_id; 
		this.group_name = group_name; 
	}


	public int getGroup_id() {
		return group_id;
	}


	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}


	public String getGroup_name() {
		return group_name;
	}


	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	
	public int calculateWeeklyPercentage() {
		
		double groupPercentage = 0;
		for (BoGroupMember m: groupMembers) {
			groupPercentage += (double)m.calculateWeeklyPercentage()/(double)groupMembers.size();
		}
		
		return (int)groupPercentage;
	
	}
	
	public int getWeeklyPoints() {
		int points = 0;
		for (BoGroupMember m: groupMembers) {
			points += m.calculateWeeklyPoints();
		}
		
		return points;
	}
	
	public int getWeeklyTargetPoints() {
		int points = 0;
		for (BoGroupMember m: groupMembers) {
			points += m.calculateWeeklyTargetPoints();
		}
		
		return points;
	}
}
