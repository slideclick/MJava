import ds.util.HeapPQueue;
import ds.util.Less;

public class EventDrivenSimulation
{
	// minimum heap releases event elements in order of their time
	private HeapPQueue<Event> eventQueue =
			new HeapPQueue<Event>(new Less<Event>());
	
	public void pushEvent(Event e)
	{
		eventQueue.push(e);
	}

	public void run()
	{
		while (!eventQueue.isEmpty())
		{
			Event nextEvent = eventQueue.pop();
			nextEvent.doEvent();
		}
	}
}