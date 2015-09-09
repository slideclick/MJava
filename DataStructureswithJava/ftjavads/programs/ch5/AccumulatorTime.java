import ds.time.Time24;

public class AccumulatorTime implements Accumulator<Time24>
{
	private Time24 total;				// data stored by the object

	// constructor creates an object with initial value
	public AccumulatorTime ()
	{ total = new Time24(); }

	// return the total
	public Time24 getTotal()
	{ return total; }

	// update the total time by the specified amount v 
	public void add(Time24 v)
	{ total.addTime(v.getHour()*60 + v.getMinute()); }
}