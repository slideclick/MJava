// TestQueue
// (c) 1999 Ken Lambert and Martin Osborne

import java.util.*;
import lamborne.*;
import BreezyGUI.*;

// NEED TO ADD TESTS FOR PRECONDITIONS

public class TestQueue{

   public static void main (String[] args){

      Queue s = new LinkedQueue();

      // Test preconditions
      
      System.out.println
        ("=========Expect exceptions for peek, dequeue, and enqueue\n");
      try {s.peek();}
        catch (Exception e) {System.out.println("Error: " + e.toString());}
      try {s.dequeue();}
        catch (Exception e) {System.out.println("Error: " + e.toString());}
      try {s.enqueue(null);}
        catch (Exception e) {System.out.println("Error: " + e.toString());}

      GBFrame.pause();

      // Test basic operations
      
      s.enqueue("a");
      s.enqueue("b");
      s.enqueue("c");
      s.enqueue("d");
      System.out.println ("Expect abcd : " + s.toString());
      System.out.println ("Expect abc     : " + s.dequeue() + s.dequeue() + s.dequeue()); 
      System.out.println ("Expect d       : " + s.toString());
      s.enqueue("x"); s.dequeue();
      System.out.println ("Expect 1       : " + s.size());
      System.out.println ("Expect x       : " + s.dequeue());
      System.out.println ("Expect no item : " + s.toString());

      GBFrame.pause();
      
      // Further tests
      
      s.enqueue("a");
      System.out.println ("Expect false   : " + s.isEmpty());
      s.clear();
      System.out.println ("Expect true    : " + s.isEmpty());
      s.enqueue("a"); s.enqueue("b"); s.enqueue("c");s.enqueue("d");
      Queue t = (Queue)((LinkedQueue)s).clone();
      Queue u = new LinkedQueue(s.collectionView());
      Object[] a = s.toArray();
      System.out.println ("Expect 4 abcd : " + a.length + " " + a[0]+a[1]+a[2]+a[3]);
      System.out.println ("Expect abcd twice : " + t + u);
      Queue v = new LinkedQueue(s.iterator());
      Queue w = new LinkedQueue();
      w.enqueue("x"); w.enqueue("b"); w.enqueue("c"); w.enqueue("d");
      v.dequeue();
      System.out.println ("Expect false true true false false: " 
                                                      + s.equals(null) + " "
                                                      + s.equals(s)    + " "
                                                      + s.equals(t)    + " "
                                                      + s.equals(v)    + " "
                                                      + s.equals(w));

      GBFrame.pause();

      Iterator iter = s.iterator();
      System.out.println("Expect 1 exceptions==============");
      try {iter.remove();}
        catch (Exception e) {System.out.println("Expect exception: " + e.toString());}
        
      Collection x = s.collectionView();
      Collection y = t.collectionView();
      u.dequeue();
      Collection z = u.collectionView();
      System.out.println ("Expect true true false : "  + x.containsAll(y) + " "
                                                       + x.containsAll(z) + " "
                                                       + z.containsAll(x));
      System.out.println("Expect 2 exceptions==============");
      try {x.remove("a");}
        catch (Exception e) {System.out.println("Expect exception: " + e.toString());}
      try {s.dequeue(); iter.next();}
        catch (Exception e) {System.out.println("Expect exception: " + e.toString());}

      GBFrame.pause();

  }
}

