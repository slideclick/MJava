import java.io.*;

public class TestOpenEmployee{
   public static void main (String[] args){

      Employee emp;
      try{
         FileInputStream fiStream = new FileInputStream ("employee.dat");
         ObjectInputStream oiStream = new ObjectInputStream (fiStream);
         emp = (Employee) oiStream.readObject();
         fiStream.close();
         System.out.println (emp);
      }catch (Exception e){
         System.out.println ("Error during input: " + e.toString());
      }
   }
}   
