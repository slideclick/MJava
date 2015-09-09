import java.text.DecimalFormat;

// hourly employee "is an" employee paid by the hour
public class HourlyEmployee extends Employee
{
   // attributes for hourly pay; they extend attributes in Employee
   private double hourlyPay;
   private double hoursWorked;

   public HourlyEmployee(String empName, String empSSN,
   					double hourlyPay, double hoursWorked)
   {
      super(empName,empSSN);
      this.hourlyPay = hourlyPay;
      this.hoursWorked = hoursWorked;
   }

   // access and update the hourly pay and hours worked
	public void setHourlyPay(double hourlyPay)
	{ this.hourlyPay = hourlyPay; }

	public void setHoursWorked(double hoursWorked)
	{ this.hoursWorked = hoursWorked; }

   // access and update the hourly pay and hours worked
	public double getHourlyPay()
	{ return hourlyPay; }

	public double getHoursWorked()
	{ return hoursWorked; }

   // call toString() from superclass and add info type of
   // employee (hourly), hourly pay rate and hours worked
	public String toString()
	{
		DecimalFormat fmt = new DecimalFormat("#.00");
		return super.toString() + '\n' +
             "Status:   Hourly" + '\n' +
             "Rate:     $" + fmt.format(hourlyPay) + '\n' +
             "Hours:    " + fmt.format(hoursWorked);
   }

   // cut a payroll check with the employee name, social security
   // number in parentheses, and salary
   public String payrollCheck()
   {
 		DecimalFormat fmt = new DecimalFormat("#.00");
		return "Pay " + empName + " (" + empSSN + ")  $" +
						fmt.format(hourlyPay * hoursWorked);
   }
}
