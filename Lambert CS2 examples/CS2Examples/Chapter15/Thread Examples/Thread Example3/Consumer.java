public class Consumer extends Thread{
    
   private SharedCell cell;
   private int accessCount;
   
   public Consumer(int accessCount, SharedCell cell){
      super ("Consumer");      
      this.accessCount = accessCount;
      this.cell = cell;;
   }
   
   public void run(){
      System.out.println(getName() + " starting up\n"); // Identify myself 
      int value;
      do{
         try{
            sleep((int) (Math.random() * ThreadTester.SLEEP_INTERVAL));                                   
         }
         catch(InterruptedException e){
            System.out.println(e.toString());
         }
         value = cell.getData();         // Consume by accessing shared cell
      }while (value != accessCount);
 
      System.out.println(getName() + " is done consuming");  // Done and terminate
   }
   
}
      