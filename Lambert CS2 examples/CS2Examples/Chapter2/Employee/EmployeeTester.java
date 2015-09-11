public class EmployeeTester{

   public static void main(String[] args){
      
      // Create a default employee object
      Employee emp1 = new Employee();
      
      // Create an employee object with given attributes
      int [] hours = {8, 10, 6, 8, 9};
      Employee emp2 = new Employee("Sue", 6.50, hours);
      
      // Display some information about emp2
      System.out.println(emp2.toString()); 
      System.out.println("Total hours: " + emp2.getTotalHours());
      System.out.println("Weekly pay : " + emp2.computePay());
      
      // Compare the employees for equality before and after cloning
      System.out.println("Equals: " + emp1.equals(emp2));
      emp1 = (Employee)emp2.clone();
      System.out.println("Equals: " + emp1.equals(emp2));
   }
}