package at.ac.tuwien.sportmate;

import java.util.ArrayList;

public  class MemberDataProvider {

	int currentUserId;
	int group_id;
	
	private MemberDataProvider instance;
	
	BoGroup currentGroup;
	ArrayList<BoGroupMember> members;
	

	public MemberDataProvider() {
		
	}
	
	public MemberDataProvider getInstance(){
		if (instance == null) {
			instance = new MemberDataProvider();
		}
		return instance;
	}
	
	public void loadMemberDataFromDB(){
		
		currentGroup = DBHandler.getGroupFromUser(currentUserId);
		members = DBHandler.getUsersFromGroup(currentGroup.group_id);
		
		for (BoGroupMember m: members){
			m.activities = DBHandler.getWeeklyActivitiesFromUser(m.user_id);
			m.weeklyTargets = DBHandler.getWeeklyTargetsFromUser(m.user_id);
		}
	}
	
	public int getCurrentUserId() {
		return currentUserId;
	}

	public void setCurrentUserId(int currentUserId) {
		this.currentUserId = currentUserId;
	}

	public ArrayList<BoGroupMember> getMembers() {
		return members;
	}

	public void setMembers(ArrayList<BoGroupMember> members) {
		this.members = members;
	}

	public BoGroupMember getGroupMember(int user_id) {
		for (BoGroupMember m : members) {
			if (m.user_id == user_id)
				return m;
		}
		return null;
	}

}
