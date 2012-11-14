package stuffplotter.shared;

public enum Achievement {
	
	FIRST_LOG_IN("Logged in for the First Timed"),
    CREATE_FIRST_EVENT("Party time!"), 
    ADD_FIRST_FRIEND("Not a loner"), 
    COMPLETE_FIRST_EVENT("Hang over"), 
    FULL_EVENT_ATTENDANCE("Full house!");
    
    private int achId;
	private String display;

    /**
     * Achievement constructor. 
     * NOTE: Java enum constructor must be private
     * 
     * @param the string display for an achievement
     */
    private Achievement(String pStr) {
            this.display = pStr;
    }
    
    public String getDisplay(){
    	return this.display;
    }

    

}
