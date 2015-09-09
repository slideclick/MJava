/*	DemoProgram: Converts height in inches into units of feet and
    inches as well as metric units and then outputs the results.   
*/

// application class that contains the main method
public class DemoProgram
{
   public static void main(String[] args)
   {
     // the constant conversion factor inches to centimeters
     final double IN2CM = 2.54;

     // variables for height, feet, inches, and centimeters
     int height = 74, foot, inch;
     double centimeter;

     // convert height to feet and inches
     foot = height / 12;
     inch = height % 12;
     centimeter = height * IN2CM;

     // display the output
     System.out.println("Height of " + foot + " foot " + inch +
            " in metric is " + centimeter + " cm");
   }
}

/*
Height of 6 foot 2 in metric is 187.96 cm
*/
  
