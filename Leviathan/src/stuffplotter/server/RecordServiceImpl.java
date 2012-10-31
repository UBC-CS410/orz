package stuffplotter.server;

import java.util.List;

import stuffplotter.client.RecordService;
import stuffplotter.shared.Account;
import stuffplotter.shared.Achievement;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class RecordServiceImpl extends RemoteServiceServlet implements RecordService {

	@Override
	public void addAchievement(Account acc, Achievement id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Achievement getAchievments(Account acc,
			List<Achievement> achievements) {
		// TODO Auto-generated method stub
		return null;
	}

}
