package stuffplotter.client;

import java.util.List;

import stuffplotter.shared.Account;
import stuffplotter.shared.Achievement;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("achievement")
public interface RecordService extends RemoteService {
	void addAchievement(Account acc, Achievement id);
	Achievement getAchievments(Account acc, List<Achievement> achievements);
}
