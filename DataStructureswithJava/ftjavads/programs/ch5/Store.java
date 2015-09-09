public class Store<T>
{
	private T value;				// data stored by the object
	
	// constructor creates an object with initial value
	public Store (T v)
	{ value = v; }
	
	// return the stored value as type T
	public T getValue()
	{ return value; }
	
	// update the stored value
	public void setValue(T v)
	{ value = v; }
	
	public String toString()
	{ return "Value = " + value; }
}
