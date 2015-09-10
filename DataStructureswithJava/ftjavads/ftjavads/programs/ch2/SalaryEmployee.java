import java.text.DecimalFormat;

// salaried employee "is an" employee with a fixed weekly salary
public class SalaryEmployee extends Employee
{
	// new attributes that extends attributes in Employee
	private double salary;

  	public SalaryEmployee(String empName, String empSSN, double salary)
   {
      	// call the Employee superclass constructor
		super(empName, empSSN);
      	this.salary = salary;
   }

	// accessor method to return the salary
	public double getSalary()
	{ return salary; }

	// mutator method to update the salary
	public void setSalary(double sal)
	{ salary = sal; }

	// return a formated string with salaried employee information
	// including name, ssn, status (salaried) and monthly pay
   public String toString()
   {
		DecimalFormat fmt = new DecimalFormat("#.00");
		return super.toString() + '\n' +
             "Status:   Salary" + '\n' +
             "Salary:   $" + fmt.format(salary);
	}

   // cut a payroll check with the employee name, social security
   // number in parentheses, and salary
   public String payrollCheck()
   {
 		DecimalFormat fmt = new DecimalFormat("#.00");
		return "Pay " + empName + " (" + empSSN + ")  $" +
						fmt.format(salary);
   }
}
