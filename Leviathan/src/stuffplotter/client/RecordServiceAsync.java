package stuffplotter.client;

import java.util.List;

import stuffplotter.shared.Account;
import stuffplotter.shared.Achievement;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface RecordServiceAsync {
	void addAchievement(Account acc, Achievement id, AsyncCallback<Void> callback);
	void getAchievments(Account acc, List<Achievement> achievements, AsyncCallback<Achievement> callback);
}
