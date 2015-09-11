public class SharedCell{
    
   private int data;
   
   public SharedCell(){
      data = -1;
   }
   
   public void setData(int data){
      System.out.println(Thread.currentThread().getName() +
                         " setting data to " + data);
      this.data = data;
   }
   
   public int getData(){
      System.out.println(Thread.currentThread().getName() +
                   " accessing data " + data);
      return data;
   }
   
}