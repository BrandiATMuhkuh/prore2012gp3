package at.ac.tuwien.sportmate;

public class AppData 
{
	private static AppData instance = null;

	private AppData(){}
	
	/**
	 * The current User.
	 * */
	private BoGroupMember currentMember;
	
	/**
	 * The group of the current User
	 */
	private BoGroup currentGroup;
	
	/**
	 * The current Member that the user wants to look at.
	 * */
	private BoGroupMember currentViewedMember;
	
	/**
	 * The default Sport category of the user.
	 * */
	private int defaultSportCategory;

	public static AppData getInstance() 
	{
		if (instance == null) 
		{
			instance = new AppData();
		}
		return instance;
	}

	public BoGroupMember getCurrentMember() {
		return currentMember;
	}

	public void setCurrentMember(BoGroupMember currentMember) {
		this.currentMember = currentMember;
	}

	public BoGroup getCurrentGroup() {
		return currentGroup;
	}

	public void setCurrentGroup(BoGroup currentGroup) {
		this.currentGroup = currentGroup;
	}

	public BoGroupMember getCurrentViewedMember() {
		return currentViewedMember;
	}

	public void setCurrentViewedMember(BoGroupMember currentViewedMember) {
		this.currentViewedMember = currentViewedMember;
	}

	public int getDefaultSportCategory() {
		return defaultSportCategory;
	}

	public void setDefaultSportCategory(int defaultSportCategory) {
		this.defaultSportCategory = defaultSportCategory;
	}
	
	public void loadData(){
		if (currentMember != null){
			
			//load current member's targets and activities
			currentMember.activities = DBHandler.getWeeklyActivitiesFromUser(currentMember.user_id);
			currentMember.weeklyTargets = DBHandler.getWeeklyTargetsFromUser(currentMember.user_id);
			
			//load current group and members
			currentGroup = DBHandler.getGroupFromUser(currentMember.user_id);
			currentGroup.groupMembers = DBHandler.getUsersFromGroup(currentGroup.group_id);
			
			//load activities and targets of current group members
			for (BoGroupMember m: currentGroup.groupMembers){
				m.activities = DBHandler.getWeeklyActivitiesFromUser(m.user_id);
				m.weeklyTargets = DBHandler.getWeeklyTargetsFromUser(m.user_id);
			}
		}
	}
	
	public BoGroupMember getGroupMember(int user_id) {
		for (BoGroupMember m : currentGroup.groupMembers) {
			if (m.user_id == user_id)
				return m;
		}
		return null;
	}
}
