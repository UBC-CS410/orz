package stuffplotter.shared;

public enum Achievement {
	
	//Misc. Achievements
	FIRST_LOG_IN(0, "Stuff Beginner", "Login for the first time", "Welcome to stuff plotter!", "images/beginner.png"),
	REACH_LVL_5(1, "Stuff Veteran", "Reach level 5", "Welcome to stuff plotter!", "images/blank.jpg"),
	REACH_LVL10(2, "Stuff Master", "Reach level 10", "Congratulations!! You have reached the max level!", "images/blank.jpg"),
    
	//Event Achievements
	CREATE_FIRST_EVENT(10, "The Host", "Host your first event", "Yay!! first event!", "images/thehost.jpg"), 
    CREATE_5_EVENTS(11, "Scheduler King", "Host 5 events", "Party time!", "images/schedulerking.jpg"), 
    FIRST_PERFECT_EVENT(12,"Ace", "Perfect score on 1st event","Pefect score for the Perfect Host!", "images/ace.jpg"), 
    FULL_EVENT_ATTENDANCE(13, "Full House", "Everyone invited shows up", "Event too popular to turn down!", "images/blank.jpg"),
    COMPLETE_FIRST_EVENT(14, "The First Event", "Completed your first event", "First event down, and many more to go!", "images/blank.jpg"),
    COMPLETE_MULTI_DAY_EVENT(15, "More than one", "Completed a Multi Day Event", "We need more than one day!", "images/blank.jpg"),
    RATE_AN_EVENT(16, "Feedback", "Rate an Event", "Feedback is awesome!", "images/feedback.jpg"),
    COMMENT_AN_EVENT(17, "Engage", "Write a comment to an Event", "You are open to communication", "images/engage.png"),
    WRITE_50_COMMENTS(18, "The Communicator", "Write a total of 50 comments", "Looks like we got ourselves a Chatty-Kathy", "images/blank.jpg"),
    COMPLETE_3_EVENTS_SAMEDAY(19, "Busy Body", "Attend and complete 3 Events on one day", "Look at you, running all over town!", "images/blank.jpg"),
    
    //Friend Achievements
    ADD_FIRST_FRIEND(30, "First Mate", "Make your first Friend", "On your way to popularity!", "images/firstfriend.jpg"),
    ADD_10_FRIENDS(31, "Mr. Popular", "Have 10 Friends in your list", "You can never have too many friends!", "images/blank.jpg"),
    		
	//Blank Achievement
    BLANK(99, "Blank", "Pick me, if you do not want to show an Achivement Badge.", "Pfft, I'm no show off.", "images/blank.jpg");
    //Achievement information
	private int achId;
	private String name;
	private String desc;
	private String msg;
	private String img;
	

    /**
     * Achievement constructor. 
     * NOTE: Java enum constructor must be private
     * remove this later
     * 
     * @param the string display for an achievement
     */
    private Achievement(int aId, String name, String desc, String msg, String img) {
        this.achId = aId;
        this.name = name;
    	this.desc = desc;
    	this.msg = msg;
    	this.img = img;
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
	
	public String getImg()
	{
		return img;
	}

}
