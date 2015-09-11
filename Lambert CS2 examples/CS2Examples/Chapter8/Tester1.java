import lamborne.*;

public class Tester1{

   public static void main (String[] args){

      // Create a new queue
      Queue q = new ArrayQueue();

      // Add three strings to the queue
      q.enqueue ("first");
      q.enqueue ("second");
      q.enqueue ("third");

      // Dequeue each string from the queue and display it on the terminal
      while (! q.isEmpty()){
         String str = (String) q.dequeue();
         System.out.println (str);
      }
   }
}
