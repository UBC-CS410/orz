package stuffplotter.client.services;

import java.util.List;

import stuffplotter.shared.Account;
import stuffplotter.shared.Achievement;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("achievement")
public interface RecordService extends RemoteService {
	void addExperience(String user, int xp);
	void unlockAchievement(String user, Achievement id);
	Achievement getAchievments(String user, List<Achievement> achievements);
}
