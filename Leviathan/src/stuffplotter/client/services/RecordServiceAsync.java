package stuffplotter.client.services;

import java.util.List;

import stuffplotter.shared.Achievement;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface RecordServiceAsync
{
	/**
	 * Add an amount of experience to the user
	 * @pre		user is a valid data store key, xp > 0
	 * @post 	user experience is increased by xp
	 * 
	 * @param 	user		key of the user account
	 * @param	xp 			amount of xp to add
	 * @param	callback	AsyncCallback object of type Void
	 */
	void addExperience(String user, int xp, AsyncCallback<Void> callback);
	
	/**
	 * Add an amount of experience to the user
	 * @pre		user is a valid data store key
	 * @post 	id is now an element of user achievements
	 * 
	 * @param 	user		key of the user account
	 * @param	id 			enum of the unlocked achievement
	 * @param	callback	AsyncCallback object of type Void
	 */
	void unlockAchievement(String user, Achievement id, AsyncCallback<Void> callback);
	void getAchievments(String user, List<Achievement> achievements, AsyncCallback<Achievement> callback);
}
