import ds.time.Time24;

public class Program5_1
{
	public static void main (String[] args)
	{
		Double[] dblArr = {6.2, 4.7, 9.5};
		Integer[] intArr = {7, 1, 9, 3, 8, 4};
		String[] strArr = {"3:45", "2:30", "5:00"};
		AccumulatorNumber accNumber = new AccumulatorNumber();
		AccumulatorTime accTime = new AccumulatorTime();
		int i;
		
		for (i = 0; i < dblArr.length; i++)
			accNumber.add(dblArr[i]);		
		for (i = 0; i < intArr.length; i++)
			accNumber.add(intArr[i]);
		System.out.println("Numeric total is " +
								 accNumber.getTotal());
		
		for (i = 0; i < strArr.length; i++)
			accTime.add(Time24.parseTime(strArr[i]));
		System.out.println("Time total is " + accTime.getTotal());
	}
}

/*
Run:

Numeric total is 52.4
Time total is 11:15
*/
