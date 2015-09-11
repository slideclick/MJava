import java.io.*;

public class TestSaveEmployee{
   public static void main (String[] args){

      // Create an Employee object
      int[] days = {8, 10, 6, 8, 8};
      Employee emp = new Employee("Bill", 8.50, days);

      // Open the appropriate streams and write the object to the file
      try{
         FileOutputStream foStream = new FileOutputStream ("employee.dat");
         ObjectOutputStream ooStream = new ObjectOutputStream (foStream);
         ooStream.writeObject (emp);
         foStream.flush();
         foStream.close();
      }catch (Exception e){
         System.out.println ("Error during output: " + e.toString());
      }
   }
}   
