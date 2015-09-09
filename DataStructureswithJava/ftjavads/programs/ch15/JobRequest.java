import java.util.Scanner;

enum JobStatus
{
	clerk (0), manager(1), director(2), president(3);
	
	// variable, constructor, and access method that associates
	// an integer value with the enum value
	private int jsValue;
	
	JobStatus(int value)
	{
		jsValue = value;
	}

	public int value()
	{
		return jsValue;
	}
}

// class defining a job request
public class JobRequest implements Comparable<JobRequest>
{
	// employee priority level, job ID, and job time
	// compare job requests by status
	private JobStatus jobStatus;
	private int jobID;
	private int jobTime;

	// constructor initialize data members
	public JobRequest(JobStatus status, int ID, int time)
	{
		jobStatus = status;
		jobID = ID;
		jobTime = time;
	}

	public JobStatus getStatus()
	{ return jobStatus; }

	public int getJobID()
	{ return jobID; }

	public int getJobTime()
	{ return jobTime; }

	public String toString()
	{
		// build the return string in s
		String s = null;

		switch (jobStatus)
		{
			case president:	s = "President      ";
									break;
			case director:		s = "Director       ";
									break;
			case manager:		s = "Manager        ";
									break;
			case clerk:			s = "Clerk          ";
									break;
		}

		s += jobID + "        " + jobTime;

		return s;
	}

	public int compareTo(JobRequest item)
	{
		if (jobStatus.value() < item.jobStatus.value())
			return -1;
		else if (jobStatus.value() == item.jobStatus.value())
			return 0;
		else
			return 1;
	}

	public static JobRequest readJob(Scanner f)
	{
		// variables to initialize JobRequest object
		String empType;
		JobStatus status = null;
		int ID, time;
		
		if (!f.hasNext())
			return null;

		// each line starts with a string that identifies the
		// type of employee. assign status field
		empType = f.next();
				
		switch(empType.charAt(0))
		{
			case 'P':   status = JobStatus.president;
							break;
			case 'D':   status = JobStatus.director;
							break;
			case 'M':   status = JobStatus.manager;
							break;
			case 'C':   status = JobStatus.clerk;
							break;
		}
		
		// read the integer jobID and jobTime fields
		ID = f.nextInt();
		time = f.nextInt();
		
		return new JobRequest(status, ID, time);
	}
}
