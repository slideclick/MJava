import ioutil.*;
public class FtoCUsingKeyboardReader{
   public static void main (String[] args){
      double fahrenheit;
      double celsius;
      KeyboardReader reader = new KeyboardReader();
      System.out.print("Enter degrees Fahrenheit: ");
      fahrenheit = reader.readDouble();  
      celsius = (fahrenheit - 32.0) * 5.0 / 9.0;
      System.out.println("The equivalent in Celsius is " + celsius);
   }
}