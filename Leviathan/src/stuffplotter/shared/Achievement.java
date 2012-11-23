package stuffplotter.shared;

public enum Achievement {
	
	//Misc. Achievements
	FIRST_LOG_IN(0, "Newcomer...", "Login for the first time", "Welcome to stuff plotter!"),
    
	//Event Achievements
	CREATE_FIRST_EVENT(10, "The Host", "Host your first event", "Yay!! first event!"), 
    CREATE_50_EVENTS(11, "Scheduler King", "Host 50 events", "Party time!"), 
    FIRST_PERFECT_EVENT(12,"Ace", "Perfect score on 1st event","Pefect score for the Perfect Host!"), 
    FULL_EVENT_ATTENDANCE(13, "Full House", "Everyone invited shows up", "Event too popular to turn down!"),
    COMPLETE_FIRST_EVENT(14, "The First Event", "Completed your first event", "First event down, and many more to go!"),
    //Friend Achievements
    
    ADD_FIRST_FRIEND(30, "First Mate", "Make your first Friend", "On your way to popularity!");
    
    //Achievement information
	private int achId;
	private String name;
	private String desc;
	private String msg;
	

    /**
     * Achievement constructor. 
     * NOTE: Java enum constructor must be private
     * remove this later
     * 
     * @param the string display for an achievement
     */
    private Achievement(int aId, String name, String desc, String msg) {
        this.achId = aId;
        this.name = name;
    	this.desc = desc;
    	this.msg = msg;
    }

	public int getAchId()
	{
		return achId;
	}

	public String getName()
	{
		return name;
	}

	public String getDesc()
	{
		return desc;
	}


	public String getMsg()
	{
		return msg;
	}

}
