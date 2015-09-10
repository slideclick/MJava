/**
 * The <em>Rectangle</em> class provides the measurement operations
 * for figures that are determined by a length and a width.Two constructors
 * create a default rectangle with length and width 0.0 and a rectangle
 * with specified length and width.
 */

public class Rectangle
{
	/**
	 * data members to store the dimensions of the rectangle
	 */
	protected double length, width;

	/**
	 * Creates a rectangle with zero length and width
	 */
	public Rectangle()
	{
		length = 0.0;
		width = 0.0;
	}

	/**
	 * Creates a rectangle with specified length and width
	 * @param length the length
	 * @param width the width
	 * @throws <code>IllegalArgumentException</code> if either argument
	 * is negative..
	 */
	public Rectangle(double length, double width)
	{
		setSides(length, width);
	}

	/**
	 * Returns the area of the rectangle.
	 * @return  a <code>double</code> having value length * width.
	 */
	public double area()
	{
		return length * width;
	}

	/**
	 * Returns the perimeter of the rectangle.
	 * @return  a <code>double</code> having value 2 * (length + width).
	 */
	public double perimeter()
	{
		return 2 * (length + width);
	}

	/**
	* Returns the current value of length.
	* @return  a <code>double</code>.specifying the length
	*/
	public double getLength()
	{
		return length;
	}

	/**
	* Returns the current value of width.
	* @return  a <code>double</code>.specifying the width
	*/
	public double getWidth()
	{
		return width;
	}

	/**
	* Updates the dimensions of the rectangle.
	* @param  length  the new length of the rectangle.
	* @param  width  the new width of the rectangle.
	* @throws <code>InvalidArgumentException</code> if an argument
	* is negative.
	*/
	public void setSides(double length, double width)
	{
		this.length = length;
		this.width = width;
	}

	/**
	 * Returns a string that describes the rectangle with its length and width
	 * @return  a string with the form "Rectangle with length <length>
	 *          and width <width>"
	 */
	public String toString()
	{
		return "Rectangle with length " + length + " and width " + width;
	}
}

