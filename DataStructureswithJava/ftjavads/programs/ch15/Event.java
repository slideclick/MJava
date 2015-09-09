public class Event implements Comparable<Event>                                         
{
    protected int time;
    public Event(int t)
    {	time = t; }
    
    public void doEvent()
    { }
    
    public int compareTo(Event e)
    {
    	if(time < e.time)
    		return -1;
    	else if (time == e.time)
    		return 0;
    	else
    		return 1;
    }
}