// backing class for a View object
public class StoreOneTwo
{
	// object has two integer fields
	private int one;
	private int two;
	private View viewObj = null;

	// constructor. assign key and value
	public StoreOneTwo(int one, int two)
	{
		this.one = one;
		this.two = two;
	}

	// return a representation of the object
	public String toString()
	{ return one + "=" + two; }

	public void setOneTwo(int one, int two)
	{
		this.one = one;
		this.two = two;
	}

	// method returns a View object that contains
	// the StoreOneTwo key
	public View viewOne()
	{
		// we only generate one instance of the
		// anonymous inner class object
		if (viewObj == null)
			viewObj =	new View()
				{
					// methods in the View interface
					public int get()
					{ return one; }
					
					public void set(int value)
					{ one = value; }
				};
		return viewObj;
	}
}
