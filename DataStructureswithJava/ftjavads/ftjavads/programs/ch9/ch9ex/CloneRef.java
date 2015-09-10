import ds.time.Time24;

public class CloneRef implements Cloneable
{
	private int n;
	private Time24 t;
	
	public CloneRef(int n, int h, int m)
	{
		this.n = n;
		t = new Time24(h,m);
	}
	
	public void updateInt(int n)
	{ this.n = n; }

	public void updateTime(int m)
	{ t.addTime(m); }

	public String toString()
	{
		return "Int value = " + n + " Time value = " + t;
	}
	
	public Object clone()
	{
		Object copy = null;

		try
		{ copy = super.clone(); }
		catch (CloneNotSupportedException cnse)
		{ throw new InternalError(); }

		// return the cloned object
		return copy;
	}
	
	// returns a deep copy of the CloneRef object. the Time24 reference
	// variable t of the new object is distinct from the current object's
	// reference variable t
	public Object deepClone()
	{ 
		Object deepcopy = null;

		try
		{ 
			deepcopy = super.clone();
			((CloneRef)deepcopy).t = new Time24(t.getHour(), t.getMinute()); 
		}
		catch (CloneNotSupportedException cnse)
		{ throw new InternalError(); }

		// return the cloned object
		return deepcopy;
	}
}
