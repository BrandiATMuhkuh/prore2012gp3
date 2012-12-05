package at.ac.tuwien.sportmate;

import java.util.List;

public class BoGroup {

	int group_id; 
	String group_name; 
	List<BoGroupMember> groupMembers; 



	BoGroup()
	{
		
	}


	public List<BoGroupMember> getGroupMembers() {
		return groupMembers;
	}


	public void setGroupMembers(List<BoGroupMember> groupMembers) {
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
}
