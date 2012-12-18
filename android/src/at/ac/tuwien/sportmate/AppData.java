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
}
