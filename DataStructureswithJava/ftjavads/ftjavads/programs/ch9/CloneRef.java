import ds.time.Time24;

public class CloneRef implements Cloneable
{
	private int n;
	private Time24 t;
	
	public CloneRef(int n, int hour, int minute)
	{
		this.n = n;
		t = new Time24(hour, minute);
	}
	
	// returns a clone of the object produced with a shallow copy
	public Object clone()
	{
		CloneRef copy = null;

		try
		{ 
			copy = (CloneRef)super.clone(); 
		}
		catch (CloneNotSupportedException cnse)
		{ 
			throw new InternalError(); 
		}

		// return the cloned object
		return copy;
	}

	public void updateInt(int n)
	{ this.n = n; }

	public void updateTime(int m)
	{ t.addTime(m); }

	public String toString()
	{
		return "n = " + n + " time = " + t;
	}
}