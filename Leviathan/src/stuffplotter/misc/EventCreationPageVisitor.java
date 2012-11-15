package stuffplotter.misc;

import stuffplotter.views.events.EventDateSelectionPanel;
import stuffplotter.views.events.EventInfoInputPanel;
import stuffplotter.views.events.EventInfoPanel;
import stuffplotter.views.events.FriendSelectionPanel;

/**
 * Interface for all classes that can be accepted by a class that implements the
 * EventSubmittable interface.
 */
public interface EventCreationPageVisitor
{
	/**
	 * Visit an EventInfoPanel and perform certain tasks.
	 * @pre infoPanel != null;
	 * @post true;
	 * @param eventInfoPanel - the EventInfoPanel to perform the tasks on.
	 */
	public void visit(EventInfoPanel eventInfoPanel);
	
	/**
	 * Visit an EventDateSelectionPanel and perform certain tasks.
	 * @pre dateSelectionPanel != null;
	 * @post true;
	 * @param dateSelectionPanel - the EventDateSelectionPanel to perform the tasks on.
	 */
	public void visit(EventDateSelectionPanel dateSelectionPanel);
	
	/**
	 * Visit a FriendSelectionPanel and perform certain tasks.
	 * @pre friendSelection != null;
	 * @post true;
	 * @param friendSelection - the FriendSelectionPanel to perform the tasks on.
	 */
	public void visit(FriendSelectionPanel friendSelection);
}
