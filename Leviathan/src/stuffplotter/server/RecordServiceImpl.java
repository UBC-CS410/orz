package stuffplotter.server;

import java.util.List;

import stuffplotter.client.services.RecordService;
import stuffplotter.shared.Account;
import stuffplotter.shared.AchievementDescription;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class RecordServiceImpl extends RemoteServiceServlet implements RecordService {

	/**
	 * Add an amount of experience to the user
	 * @pre		user is a valid data store key, xp > 0
	 * @post 	user experience is increased by xp
	 * @param 	user	key of the user account
	 * 			xp 		amount of xp to add
	 */
	@Override
	public void addExperience(String user, int xp)
	{
		// TODO Auto-generated method stub
		
	}

	/**
	 * Add an amount of experience to the user
	 * @pre		user is a valid data store key
	 * @post 	id is now an element of user achievements
	 * @param 	user	key of the user account
	 * 			id 		enum of the unlocked achievement
	 */
	@Override
	public void unlockAchievement(String user, AchievementDescription id)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public AchievementDescription getAchievments(String user, List<AchievementDescription> achievements)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
