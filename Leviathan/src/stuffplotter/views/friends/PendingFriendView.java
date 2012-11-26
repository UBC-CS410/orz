package stuffplotter.views.friends;

import com.google.gwt.event.dom.client.HasClickHandlers;

public interface PendingFriendView
{
	/**
	 * Retrieve the remove friend "button".
	 * @pre true;
	 * @post true;
	 * @return the remove friend "button".
	 */
	public HasClickHandlers getConfirmBtn();
	
	/**
	 * Retrieve the view friend "button".
	 * @pre true;
	 * @post true;
	 * @return the view friend "button".
	 */
	public HasClickHandlers getDenyBtn();
	
	/**
	 * Retrieve the email of the user.
	 * @pre true;
	 * @post true;
	 * @return the email of the user.
	 */
	public String getEmail();
	
	/**
	 * Retrieve the name of the user.
	 * @pre true;
	 * @post true;
	 * @return the name of the user.
	 */
	public String getName();
}
