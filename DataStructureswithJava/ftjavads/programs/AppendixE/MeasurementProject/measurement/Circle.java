package measurement;

/**
 * The <em>Circle</em> class provides measurement operations for
 * objects that are determined by their radius.
 * Constructors create a default circle with radius 0.0 and a
 * circle with radius r.
 */

public class Circle implements Measurement
{
    /**
     * Creates a circle of radius 0.0.
     */
    public Circle()
    { }

    /**
     * Creates a circle of radius r
     * @param r the radius
     * @throws <code>IllegalArgumentException</code> if the argument is negative..
     */
    public Circle(double r)
    { radius = r; }


	/**
     * Returns the area of the circle.
     * @return  a <code>double</code> having value PI * r (squared).
     */
    public double area()
    { return Measurement.PI * radius * radius; }

	/**
     * Returns the perimeter of the circle.
     * @return  a <code>double</code> having value 2 * PI * r.
     */
    public double perimeter()
    { return 2 * Measurement.PI * radius; }

    /**
     * Returns the current value of radius.
     * @return  a <code>double</code>.specifying the radius
     */
    public double getRadius()
    {  return radius; }

    /**
     * Updates the radius to have value r.
     * @param  r  the new value for the radius.
     * @throws  InvalidArgumentException if argument is negative.
     */
    public void setRadius(double r)
    { radius = r; }

    /**
     * data member to store the radius
     */
    private double radius;
}

