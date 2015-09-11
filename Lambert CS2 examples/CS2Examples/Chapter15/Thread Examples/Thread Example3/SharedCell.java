public class SharedCell{
    
   private int data;
   private boolean writeable;
   
   public SharedCell(){
      data = -1;
      writeable = true;
   }
   
   public synchronized void setData(int data){
      while (! writeable){                         // Producer must wait
         try{                                      // until consumer has
            wait();                                // accessed cell
         } 
         catch(InterruptedException e){
            System.out.println(e.toString());
         }
      }
      
      System.out.println(Thread.currentThread().getName() +
                         " setting data to " + data);
      this.data = data;
      writeable = false;
      notify();                         // Tell consumer to become ready
   }
   
   public synchronized int getData(){
      while (writeable){                           // Consumer must wait
         try{                                      // until producer has
            wait();                                // accessed cell
         } 
         catch(InterruptedException e){
            System.out.println(e.toString());
         }
      }
      System.out.println(Thread.currentThread().getName() +
                         " accessing data " + data);
      writeable = true;
      notify();                         // Tell producer to become ready
      return data;
   }
   
}