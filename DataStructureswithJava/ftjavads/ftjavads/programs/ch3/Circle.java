/**
 * The <em>Circle</em> class provides measurement operations for
 * objects that are determined by their radius.
 * Constructors create a default circle with radius 0.0 and a
 * circle with a specified radius.
 */

public class Circle implements Measurement
{
	/**
	 * data member to store the radius
	 */
	private double radius;

	/**
	 * Creates a circle of radius 0.0.
	 */
	public Circle()
	{
		radius = 0.0;
	}

	/**
	 * Creates a circle of with the specified radius
	 * @param radius the radius of this object
	 * @throws <code>IllegalArgumentException</code> if the argument is negative..
	 */
	public Circle(double radius)
	{
		// calls setRadius() which throws exception if radius < 0.0
		setRadius(radius);
	}

	/**
	 * Returns the area of the circle.
	 * @return  a <code>double</code> having value PI * r (squared).
	 */
	public double area()
	{
		return Measurement.PI * radius * radius;
	}

	/**
	 * Returns the perimeter of the circle.
	 * @return  a <code>double</code> having value 2 * PI * r.
	 */
	public double perimeter()
	{
		return 2 * Measurement.PI * radius;
	}

	/**
	 * Returns the current value of radius.
	 * @return  a <code>double</code>.specifying the radius
	 */
	public double getRadius()
	{
		return radius;
	}

	/**
	 * Updates the radius with the specified value.
	 * @param  radius  the new redius for the circle.
	 * @throws  IllegalArgumentException if argument is negative.
	 */
	public void setRadius(double radius)
	{
		if (radius < 0.0)
			throw new IllegalArgumentException("Circle has a positive radius");
		this.radius = radius;
	}

	/**
	 * Returns a string that describes the circle with its radius
	 * @return  a string with the form "Circle with radius <radius>"
	 */
	public String toString()
	{
		return "Circle with radius " + radius;
	}
}

