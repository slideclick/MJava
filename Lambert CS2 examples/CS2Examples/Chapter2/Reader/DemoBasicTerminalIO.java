public class DemoBasicTerminalIO{
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
/*
import java.io.*;
public class DemoBasicTerminalIO{
   public static void main (String[] args){
      double fahrenheit;
      double celsius;
      try{
         InputStreamReader reader = new InputStreamReader(System.in);
         BufferedReader buffer = new BufferedReader(reader);
         System.out.print("Enter degrees Fahrenheit: ");
         fahrenheit = Double.parseDouble(buffer.readLine());  
         celsius = (fahrenheit - 32.0) * 5.0 / 9.0;
         System.out.println("The equivalent in Celsius is " + celsius);
      }catch(Exception e){
         System.err.println("Input error -- " + e.toString());
      }
   }
}
*/