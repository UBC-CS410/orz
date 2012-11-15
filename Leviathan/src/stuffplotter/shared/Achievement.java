package stuffplotter.shared;

public enum Achievement {
    CREATE_FIRST_EVENT("Party time!"), 
    ADD_FIRST_FRIEND("Not a loner"), 
    COMPLETE_FIRST_EVENT("Hang over"), 
    FULL_EVENT_ATTENDANCE("Full house!");
    
    //Achievement information
    private int achId;
	private String name; //

    /**
     * Achievement constructor. 
     * NOTE: Java enum constructor must be private
     * remove this later
     * 
     * @param the string display for an achievement
     */
    private Achievement(String pStr) {
            this.name = pStr;
    }

	/**
	 * @return the achId
	 */
	public String getAchName() {
		return name;
	}

	/**
	 * @param achId the achId to set
	 */
	public void setAchName(String name) {
		this.name = name;
	}

    

}
