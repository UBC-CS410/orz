package stuffplotter.views.events;

/**
 * Interface used by all pages of the event creation UI.
 */
public interface EventSubmittable
{
	/**
	 * Method to accept a EventCreationPageVisitor.
	 * @pre eventVisitor != null;
	 * @post true;
	 * @param eventVisitor - the EventCreationPageVisitor to accept.
	 */
	public void accept(EventCreationPageVisitor eventVisitor);
}
