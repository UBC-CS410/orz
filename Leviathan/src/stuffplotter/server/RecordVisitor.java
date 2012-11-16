package stuffplotter.server;

import stuffplotter.shared.Account;
import stuffplotter.shared.AccountStatistic;
import stuffplotter.shared.Event;

/**
 * This interface is used to visit the Account class to either update 
 * their "Achievements Info" fields or check for Achievements
 * @author Matt
 *
 */
public interface RecordVisitor
{
	/**
	 * Visits the accountStatistic
	 * @pre
	 * @post
	 * @param account
	 */
	public void visit(AccountStatistic account);


	public void visit(AccountStatistic account, Event event);
}
