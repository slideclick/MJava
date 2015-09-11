import ioutil.KeyboardReader;

public class ThreadTester{
       
   public static final int SLEEP_INTERVAL = 2000;
       
   public static void main (String args[]){
      KeyboardReader reader = new KeyboardReader();
		System.out.print("Number of Accesses: ");
      int accessCount = reader.readInt();             // Obtain # accesses
      SharedCell cell = new SharedCell();             // Create shared cell
      Producer p = new Producer(accessCount, cell);   // Create producer
      Consumer c = new Consumer(accessCount, cell);   // Create consumer
      
      System.out.println("Starting the threads");        // Start 'em up
      p.start();
      c.start();
   } 
}