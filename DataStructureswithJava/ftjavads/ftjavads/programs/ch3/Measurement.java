/**
 * The <em>Measurement</em> interface defines the static
 * double <code>PI</code> and the signatures of measurement operations.
 */

public interface Measurement
{

	/**
	* The constant <code>PI</code>
	*/
	public static final double PI = 3.14159265;

	/**
	* Get the area of the figure
	* @return a <code>double</code> specifying the area
	*/
	double area();

	/**
	* Get the perimeter of the figure
	* @return a <code>double</code> specifying the perimeter
	*/
	double perimeter();
}

