public class Producer extends Thread{
    
   private SharedCell cell;
   private int accessCount;
   
   public Producer(int accessCount, SharedCell cell){
      super ("Producer");      
      this.accessCount = accessCount;
      this.cell = cell;
   }
   
   public void run(){
      System.out.println(getName() + " starting up\n"); // Identify myself 
      
      for (int i = 1; i <= accessCount; i++){
         try{
            sleep((int) (Math.random() * ThreadTester.SLEEP_INTERVAL));                                   
         }
         catch(InterruptedException e){
            System.out.println(e.toString());
         }
         cell.setData(i);                  // Produce by setting shared cell
      }
 
      System.out.println(getName() + " is done producing");  // Done and terminate
   }
   
}
      