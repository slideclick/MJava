// base class for all employees
abstract public class AbstractEmployee
{
   // instance variables are accessible by subclass methods
   protected String empName;
   protected String empSSN;

   // create an object with initial values empName and empSSN
   public AbstractEmployee(String empName, String empSSN)
   {
      this.empName = empName;
      this.empSSN = empSSN;
   }

   // update the employee name
   public void setName(String empName)
   { this.empName = empName; }

   // returns a formatted string to display employee information
   public String toString()
   { return "Name:     " + empName + '\n' + "SS#:      " + empSSN; }

   // abstract method declaration for payrollCheck()
   abstract public String payrollCheck();
}
