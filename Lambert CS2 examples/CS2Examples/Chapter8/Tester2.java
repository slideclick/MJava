import lamborne.*;

public class Tester2{

   public static void main (String[] args){

      // Create a new queue
      Queue q = new ArrayQueue();

      // Add several ints to the queue
      for (int i = 1; i <= 10; i++)
         q.enqueue("" + i);

      System.out.println(q.toString());

      for (int i = 1; i <= 2; i++)
         q.dequeue();

      System.out.println(q.toString());

      for (int i = 1; i <= 2; i++)
         q.enqueue("" + i * 100);

      System.out.println(q.toString());

      q.enqueue("" + 55);

      System.out.println(q.toString());

   }
}
