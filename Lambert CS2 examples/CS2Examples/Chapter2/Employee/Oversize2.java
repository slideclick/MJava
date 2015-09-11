public class Oversize2{
   public static void main(String[] args){      
      // Create an employee object with an oversized array
      // and handle the exception ourselves
      try{
         int [] hours = {1, 2, 3, 4, 5, 6};
         Employee emp = new Employee("Sue", 6.50, hours);
      }catch (IllegalArgumentException e){
         System.out.println ("Caught the error:\n" + e.toString());
      }
   }
}