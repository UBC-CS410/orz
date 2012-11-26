package stuffplotter.signals;

import com.google.gwt.event.shared.EventHandler;

public interface EventSchedulerEventHandler extends EventHandler
{
	public void onSchedulerUpdate(EventSchedulerEvent event);
}
