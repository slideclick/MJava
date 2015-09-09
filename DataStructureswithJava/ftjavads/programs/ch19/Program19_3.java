import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;

import ds.util.TreeMap;
import ds.time.Time24;

public class Program19_3
{
	public static void main(String[] args)
	{
		// a TreeMap object whose entries are a student name
		// and the total hours worked during a week. use a Time24
		// object for the value component of an entry
		TreeMap<String, Time24> timecardMap =
			new TreeMap<String,Time24>();
		Time24 workTime, timeValue;

		// object used to input the data from file "studwk.dat"
		Scanner fin = null;
		
		try
		{
			fin = new Scanner(new FileReader("studwk.dat"));
		}
		catch (FileNotFoundException fnfe)
		{
			System.err.println("Cannot open \"studwk.dat\"");
			System.exit(1);
		}

		// variables to store input data
		String studName, endStuff;
		int workhour, workminute;

		// input successive lines in the file consisting of the
		// student name and the scheduled work time
		while (fin.hasNext())
		{
			studName = fin.next();

			// get hours and minutes from the input line
			workhour = fin.nextInt();
			workminute = fin.nextInt();

			workTime = new Time24(workhour, workminute);

			// access the entry corresponding to the student name
			timeValue = timecardMap.get(studName);

        	// if timeValue is null, we have a new entry with a
        	// Time24 object as the value
			if (timeValue == null)
				timecardMap.put(
					studName, new Time24(workhour, workminute));
			else
         	// update the current Time24 value and put entry back
         	// into the timecardMap
         	{
					timeValue.addTime(workhour*60 + workminute);
					timecardMap.put(studName, timeValue);
				}
		}

		// display the timecardMap
		System.out.println("Student-Time: " + timecardMap);
	}
}

/*
File: "studwk.dat"
Tolan	4 15
Dong	3 00
Tolan	3 15
Weber	5 30
Tolan	2 45
Brock	4 20
Dong	4 00
Dong	3 30
Tolan	3 15
Weber	2 30

Run:

Student-Time: {Brock=4:20, Dong=10:30, Tolan=13:30, Weber=8:00}
*/
