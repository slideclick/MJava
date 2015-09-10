package measurement;

/**
 * The <em>Square</em> class extends the class <code>Rectangle</code>. New
 * methods <code>getSide</code> and <code>setSide</code> are specific to
 * the Square class
 */

public class Square extends Rectangle
{
    /**
     * Creates a Square with sides 0.0.
     */
    public Square()
    { super(0.0, 0.0); }

    /**
     * Creates a Square with sides s.
     * @param s the length of a side
     * @throws <code>IllegalArgumentException</code> if the argument is negative..
     */
    public Square(double s)
    { super(s,s); }

    /**
     * Update the side of the square by calling setSides()..
     * @see Rectangle#setSides
     * @param s the length of a side
     * @throws <code>IllegalArgumentException</code> if the argument is negative..
     */
    public void setSide(double s)
    { super.setSides(s,s); }

    /**
     * Returns the current value of a side.
     * @return  a <code>double</code> the length of a side>.
     */
    public double getSide()
    {  return length; }
}

