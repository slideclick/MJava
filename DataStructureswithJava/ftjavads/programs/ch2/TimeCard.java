import java.text.DecimalFormat;

import ds.time.Time24;

public class TimeCard
{
	private  String workerID;
	private Time24 punchInTime, punchOutTime;	// supplier-class objects
	private double payrate;

	public TimeCard(String workerID, double payrate, int punchInHour,
							int punchInMinute)
	{
		// initialize workerID and payrate
		this.workerID = workerID;
		this.payrate = payrate;

		// create Time24 object by calling constructor Time24(hour,minute)
		punchInTime = new Time24(punchInHour, punchInMinute);

		// create Time24 object by calling default constructor Time24()
		punchOutTime = new Time24();
	}

	public String payWorker(int punchOutHour, int punchOutMinute)
	{
		// local variables for time worked and hours worked
		Time24 timeWorked;
		double hoursWorked;		// timeWorked converted to hours

		// numeric format object for hours worked and pay
		DecimalFormat fmt = new DecimalFormat("0.00");

		// update punchOutTime by calling setTime()
		punchOutTime.setTime(punchOutHour, punchOutMinute);


		// evaluate time worked with Time24 interval() method
		timeWorked = punchInTime.interval(punchOutTime);

		// hoursWorked is timeWorked as fractional part of an hour
		hoursWorked = timeWorked.getHour() + timeWorked.getMinute()/60.0;

		// return  formatted string
		return	"Worker:      " + workerID + "\n" +
				"Start time:  " + punchInTime + "  End time: " +
				 			punchOutTime + "\n" +
				"Total time:  " + fmt.format(hoursWorked) +
		  					" hours" + "\n" +
				"At $" + fmt.format(payrate) + " per hour, pay is $" +
		  					fmt.format(payrate*hoursWorked);
	}
}

