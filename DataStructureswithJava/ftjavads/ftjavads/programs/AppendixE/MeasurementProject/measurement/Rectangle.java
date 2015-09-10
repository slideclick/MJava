package measurement;

/**
 * The <em>Rectangle</em> class provides the measurement operations
 * for figures that are determined by a length and a width.Two constructors
 * create a default rectangle with length and width 0.0 and a rectangle
 * with length len and width wid.
 * @author  William Topp
 */

public class Rectangle implements Measurement
{
    /**
     * Creates a rectangle with zero length and width
     */
    public Rectangle()
    {
		length = 0.0;
		width = 0.0;
	}

    /**
     * Creates a rectangle of length len and width wid
     * @param len the length
     * @param wid the width
     * @throws <code>IllegalArgumentException</code> if either argument
     * is negative..
     */
    public Rectangle(double len, double wid)
    {
		length = len;
		width = wid;
	}

	/**
     * Returns the area of the rectangle.
     * @return  a <code>double</code> having value length * width.
     */
    public double area()
    { return length * width; }

	/**
     * Returns the perimeter of the rectangle.
     * @return  a <code>double</code> having value 2 * (length + width).
     */
    public double perimeter()
    { return 2 * (length + width); }

    /**
     * Returns the current value of length.
     * @return  a <code>double</code>.specifying the length
     */
    public double getLength()
    {  return length; }

     /**
      * Returns the current value of width.
      * @return  a <code>double</code>.specifying the width
      */
    public double getWidth()
    {  return width; }

    /**
     * Updates the dimensions of the rectangle.
     * @param  len  the new value for the length.
     * @param  wid  the new value for the width.
     * @throws <code>InvalidArgumentException</code> if an argument
     * is negative.
     */
    public void setSides(double len, double wid)
    {
		length = len;
		width = wid;
	}

  	/**
  	 * data members to store the dimensions of the rectangle
  	 */
    protected double length, width;
}

