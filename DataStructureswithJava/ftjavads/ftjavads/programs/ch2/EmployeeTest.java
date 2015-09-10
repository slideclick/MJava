public class EmployeeTest
{
	public static void main(String[] args)
	{
		HourlyEmployee hEmp = new HourlyEmployee("Steve Howard",
											  "896-54-3217",10.50,40);
		SalaryEmployee sEmp = new SalaryEmployee("Moira Dunn",
											  "456-14-3787",800.0);

		System.out.println(hEmp);
		System.out.println(sEmp);

		System.out.println(hEmp.payrollCheck() + "\n");

		sEmp.setSalary(sEmp.getSalary() * 1.10);
		System.out.println("Moira Dunn's new salary information\n" + sEmp);
	}
}
