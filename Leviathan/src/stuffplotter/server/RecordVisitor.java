package stuffplotter.server;

import stuffplotter.shared.Account;
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
	 * Visits the account
	 * @pre
	 * @post
	 * @param account
	 */
	public void visit(Account account);
	/**
	 * Visits the account and event simultaneously
	 * @pre
	 * @post
	 * @param account
	 * @param event
	 */
	public void visit(Account account, Event event);
}
