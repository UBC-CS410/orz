package stuffplotter.client.services;

import java.util.List;

import stuffplotter.shared.Account;
import stuffplotter.shared.AchievementDescription;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("achievement")
public interface RecordService extends RemoteService {
	void addExperience(String user, int xp);
	void unlockAchievement(String user, AchievementDescription id);
	AchievementDescription getAchievments(String user, List<AchievementDescription> achievements);
}
