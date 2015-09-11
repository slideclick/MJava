import java.io.*;

public class EmployeeModel extends Object
                           implements Serializable{
                           
   public static final int MAX_EMPLOYEES = 20;
   
   private Employee[] employees;
   private int employeeCount;
   
   public EmployeeModel(){
      employees = new Employee[MAX_EMPLOYEES];
      employeeCount = 0;
   }
   
   public Employee getEmployee(int position){
      return employees[position];
   }
   
   public String addEmployee(Employee emp){
      if (employeeCount == MAX_EMPLOYEES)
         return ("Sorry - no room in array");
      else{
         employees[employeeCount] = emp;
         employeeCount++;
         return null;
      }
   }
}