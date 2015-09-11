public class Oversize1{

   public static void main(String[] args){
      
      // Create an employee object with an oversized array
      // and let Java handle the exception
      int [] hours = {1, 2, 3, 4, 5, 6};
      Employee emp = new Employee("Sue", 6.50, hours);
   }
}