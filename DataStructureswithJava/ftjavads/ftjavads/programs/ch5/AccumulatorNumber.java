public class AccumulatorNumber implements Accumulator<Number>
{
	private double total;	// accumulation of numeric values

	// constructor initializes total to 0.0
	public AccumulatorNumber ()
	{ total = 0.0; }

	// return the total
	public double getTotal()
	{ return total; }

	// update the total by the value of v as a double 
	public void add(Number v)
	{ total = total + v.doubleValue(); }
}
