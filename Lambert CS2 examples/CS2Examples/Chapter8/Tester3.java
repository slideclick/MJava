import lamborne.*;

public class Tester3{

   public static void main (String[] args){

      // Create a new queue
      Queue q = new LinkedQueue();

      // Add three integers to the queue
      for (int i = 1; i <= 5; i++)
         q.enqueue(new Integer(i));
      System.out.println(q);
      System.out.println(q.size());
      System.out.println(q.isEmpty());
   }
}
