import lamborne.*;

public class Tester4{

   public static void main (String[] args){

      // Create a new priority queue with priorities 1 and 2
      PriorityQueue q = new LinkedPriorityQueue(2);

      // Add three integers with priority 1 to the queue
      for (int i = 1; i <= 3; i++)
         q.enqueue(new Integer(i), 1);

      // Add three integers with priority 2 to the queue
      for (int i = 10; i <= 12; i++)
         q.enqueue(new Integer(i), 2);

      // Display the contents of the queue
      System.out.println(q);

      // Verify the ordering of the integers
      while (! q.isEmpty())
         System.out.println(((Integer)q.dequeue()).intValue());
   }
}
