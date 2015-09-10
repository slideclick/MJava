import java.io.*;
import java.util.Scanner;
import ds.util.LinkedQueue;
import ds.time.Time24;

public class Program15_1
{
	public static void main(String[] args) throws IOException
	{
		final Time24 END_DAY = new Time24(17,00);
		String apptStr;
		
		// time interval from current appt to next appt; 
		Time24 apptTime = null, interviewTime = null;
		
		// input stream to read times as strings from
		// file "appt.dat"
		Scanner input = new Scanner(new FileReader("appt.dat"));

		// queue to hold appointment time for job applicants
		LinkedQueue<Time24> apptQ = new LinkedQueue<Time24>();

		// construct the queue by appt times as strings from file.
		// use parseTime to convert to Time24 object
		while (input.hasNext())
		{
			apptStr = input.nextLine();
			apptQ.push(Time24.parseTime(apptStr));
		}

		// output the day's appointment schedule
		System.out.println("Appointment   Interview");

		// pop next appt time and determine available time for the 
		// interview (time interval to next appt at front of queue)
		while (!apptQ.isEmpty())
		{
			// get the next appointment
			apptTime = apptQ.pop();
			
			// interview time is interval to next appt or to END_DAY
			if (!apptQ.isEmpty())
				interviewTime = apptTime.interval(apptQ.peek());
			else
				interviewTime = apptTime.interval(END_DAY);
				
			// display appointment time and interview time
			System.out.println("   " + apptTime + "        " +
			    interviewTime);
		}
	}
}

/*
File "appt.dat"
	10:00
	11:15
	13:00
	13:45
	14:30
	15:30
	16:30

Run:

Appointment   Interview
   10:00        1:15
   11:15        1:45
   13:00        0:45
   13:45        0:45
   14:30        1:00
   15:30        1:00
   16:30        0:30
*/
