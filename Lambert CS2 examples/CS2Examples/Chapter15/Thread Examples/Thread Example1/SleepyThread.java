public class SleepyThread extends Thread{
    
   private int sleepInterval;
    
   public SleepyThread(int number, int sleepMax){
      super ("Thread " + number);      
      sleepInterval = (int) (Math.random() * sleepMax);
   }
   
   public void run(){
      System.out.println("Name:           " +      // Identify myself
		                   getName() +     
		                   "\nSleep interval: " + 
								 sleepInterval);
                   
      try{
         sleep(sleepInterval);                   // I sleep for a bit
      }
      catch(InterruptedException e){
         System.out.println(e.toString());
      }
      
      System.out.println(getName() + " waking up");      // I'm awake
   }   
}      