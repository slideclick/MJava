package measurement;

 // the interface defines the static double PI and the prototypes
 // for measurement operations
public interface Measurement
{
     // the constant PI uses the value from the Math class
    public static final double PI = Math.PI;

    // return the area of the figure
    public double area();

    // return the perimeter of the figure
    public double perimeter();
}
