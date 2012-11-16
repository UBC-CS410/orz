package stuffplotter.shared;

public enum AchievementDescription {
	
	FIRST_LOG_IN("Logged in for the First Time"),
    CREATE_FIRST_EVENT("Party time!"), 
    ADD_FIRST_FRIEND("Not a loner"), 
    COMPLETE_FIRST_EVENT("Hang over"), 
    FULL_EVENT_ATTENDANCE("Full house!");
    
    //Achievement information
    private int achId;
	private String display; //

    /**
     * Achievement constructor. 
     * NOTE: Java enum constructor must be private
     * remove this later
     * 
     * @param the string display for an achievement
     */
    private AchievementDescription(String pStr) {
            this.display = pStr;
    }
    
    public String getDisplay(){
    	return this.display;
    }

    

}
