import java.io.*;
import java.util.Scanner;
import ds.util.HeapPQueue;

public class Program15_3
{
   public static void main(String[] args) throws IOException
   {
      // handle job requests
      HeapPQueue<JobRequest> jobPool =
      	new HeapPQueue<JobRequest>();

      // job requests are read from file "job.dat"
      Scanner sc = new Scanner(new FileReader("job.dat"));

      // time spent working for each category of employee.
      // initial time 0 for each category
      int[] jobServicesUse = {0,0,0,0};
      JobRequest job = null;

      // read file. insert each job into priority queue jobPool.
      while ((job = JobRequest.readJob(sc)) != null)
         jobPool.push(job);

      // delete jobs from priority queue and output information
      System.out.println("Category     Job ID    Job Time");
      while (!jobPool.isEmpty())
      {
         // remove a job from the priority queue and output it
         job = (JobRequest)jobPool.pop();
         System.out.println(job);

         // accumulate job time for the category of employee
         jobServicesUse[job.getStatus().value()] +=
         		job.getJobTime();
      }
      System.out.println();

      writeJobSummary(jobServicesUse);
   }

   private static void writeJobSummary(int[] jobServicesUse)
   {
		System.out.println("Total Pool Usage");
      System.out.println("   President    " + jobServicesUse[3]);
      System.out.println("   Director     " + jobServicesUse[2]);
      System.out.println("   Manager      " + jobServicesUse[1]);
      System.out.println("   Clerk        " + jobServicesUse[0]);
   }
 }

/*
Run

Category     Job ID    Job Time
President      303        25
President      306        50
Director       300        20
Director       307        70
Director       310        60
Director       302        40
Manager        311        30
Manager        304        10
Manager        305        40
Clerk          308        20
Clerk          309        20
Clerk          301        30

Total Pool Usage
   President    75
   Director     190
   Manager      80
   Clerk        70
*/
