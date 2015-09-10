/**
 * The <em>Measurement</em> interface defines the static
 * double <code>PI</code> and the signatures of measurement operations.
 */

public interface Measurement
{
    /**
     * The constant <code>PI</code> uses the value from the Math class.
     */
    public static final double PI = Math.PI;

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

