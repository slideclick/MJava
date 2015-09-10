import ds.util.Arrays;
import ds.time.Time24;

public class Program5_2
{
	public static void main (String[] args)
	{
		String[] strArr = {"red", "green", "blue"};
		Integer[] intArr = {40, 70, 50, 30};
		Time24[] timeArr = {new Time24(14,15), new Time24(10, 45),
                          new Time24(22,00), new Time24(3,30)};
		SalaryEmployee[] emp = {
			new SalaryEmployee("Dunn, Moira", "471-23-8092",800.0),
			new SalaryEmployee("Garcia, Avey","398-67-1298",1200.0),
			new SalaryEmployee("Ye, Don", "682-76-1298",2000.0)};
		
		Arrays.selectionSort(strArr);
		System.out.println("Sorted strings: " +
								 Arrays.toString(strArr));
		
		Arrays.selectionSort(intArr);
		System.out.println("Sorted integers: " +
								 Arrays.toString(intArr));
		
		Arrays.selectionSort(timeArr);
		System.out.println("Sorted times: " +
								 Arrays.toString(timeArr));
		
		Arrays.selectionSort(emp);
		for (int i=0; i < emp.length; i++)
			System.out.println(emp[i].payrollCheck());
	}
}

/*
Run:

Sorted strings: [blue, green, red]
Sorted integers: [30, 40, 50, 70]
Sorted times: [3:30, 10:45, 14:15, 22:00]
Pay Garcia, Avey (398-67-1298)  $1200.00
Pay Dunn, Moira (471-23-8092)  $800.00
Pay Ye, Don (682-76-1298)  $2000.00
*/
