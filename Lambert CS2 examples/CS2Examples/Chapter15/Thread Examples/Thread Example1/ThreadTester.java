import ioutil.KeyboardReader;

public class ThreadTester{
    
   public static void main (String args[]){
	   KeyboardReader reader = new KeyboardReader();
	   System.out.print("Number of Threads: ");
      int numThreads = reader.readInt();                      // Obtain # threads
	   System.out.print("Maximum Sleep Time (Millisec): ");
      int sleepMax = reader.readInt();                        // Obtain sleep max
      
      SleepyThread [] threads = new SleepyThread[numThreads]; // Create array
      for (int i = 0; i < numThreads; i++)
         threads[i] = new SleepyThread(i, sleepMax);          // Create the threads
      System.out.println("Starting the threads");
      
      for (int i = 0; i < numThreads; i++)                    // Start them up
         threads[i].start();
    }
}