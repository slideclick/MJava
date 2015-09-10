import measurement.*;

public class MeasurementDemo
{
   public static void main(String[] args)
   {
   	Measurement[] figure = {
   		new Circle(5), new Rectangle(3,5), new Square(2) };
   	
   	for (int i=0;i < figure.length;i++)
   		System.out.println("Area = " + figure[i].area() +
   								 "  Perimeter = " + figure[i].perimeter());
   }
}

/*
Run:

Area = 78.53981633974483  Perimeter = 31.41592653589793
Area = 15.0  Perimeter = 16.0
Area = 4.0  Perimeter = 8.0
*/
