package stuffplotter.misc;

import stuffplotter.shared.Event;

/**
 * Interface used by all pages of the event creation UI.
 */
public interface EventSubmittable
{
	/**
	 * Method to retrieve the submission from a page for the event to be created.
	 * @pre event != null;
	 * @post true;
	 * @param event - the Event to have its fields populated with before sending to
	 * 				  the backend.
	 */
	public void retrieveSubmission(Event event);
}
