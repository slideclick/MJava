// TestPriorityQueue2
// (c) 1999 Ken Lambert and Martin Osborne

import java.util.*;
import lamborne.*;
import BreezyGUI.*;

// NEED TO ADD TESTS FOR PRECONDITIONS

public class TestPriorityQueue2{
   public static void main (String[] args){
      //PriorityQueue q = new LinkedPriorityQueue(4);
      PriorityQueue q = new HeapPriorityQueue();
      
      // Test preconditions
      System.out.println
        ("=========Expect exceptions for peek, dequeue, and enqueue\n");
      try {q.peek();}
        catch (Exception e) {System.out.println("Error: " + e.toString());}
      try {q.dequeue();}
        catch (Exception e) {System.out.println("Error: " + e.toString());}
      try {q.enqueue(null);}
        catch (Exception e) {System.out.println("Error: " + e.toString());}
      GBFrame.pause();
      System.out.println
        ("=========For linked expect exceptions for enqueue, enqueue, and constructor\n" +
         "=========For heap expect exceptions for enqueue and constructor");
      try {q.enqueue("a", 0);}
        catch (Exception e) {System.out.println("Error: " + e.toString());}
      try {q.enqueue("a", 5);}
        catch (Exception e) {System.out.println("Error: " + e.toString());}
      try {new LinkedPriorityQueue(1);}
        catch (Exception e) {System.out.println("Error: " + e.toString());}     
      GBFrame.pause();      

      q.enqueue("a",1);
      q.enqueue("b",2);
      q.enqueue("c",3);
      q.enqueue("d",4);
      q.clear();
      q.enqueue("a1",1);q.enqueue("b1",2);q.enqueue("c1",3);
      q.enqueue("b2",2);q.enqueue("b3",2);q.enqueue("a2",1);
      q.enqueue("a3",1);q.enqueue("c2",3);q.enqueue("c3",3); 
      q.enqueue("x");
      q.enqueue("y");
      q.enqueue("z");
      System.out.println ("Expect [c1c2c3][b1b2b3][a1a2a3xyz] : " + q.toString());
      System.out.println ("Expect c1c2c3b1b2b3a1a2a3xyz : " + 
                              q.dequeue() + q.dequeue() + q.dequeue() +
                              q.dequeue() + q.dequeue() + q.dequeue() +
                              q.dequeue() + q.dequeue() + q.dequeue() +
                              q.dequeue() + q.dequeue() + q.dequeue() );
      GBFrame.pause();
      q.enqueue("a1",1);q.enqueue("b1",2);q.enqueue("c1",3);
      System.out.println ("Expect c1    : " + q.peek());
      System.out.println ("Expect 3     : " + q.size());
      System.out.println ("Expect c1    : " + q.dequeue());
      q.enqueue("b2",2);
      System.out.println ("Expect b1b2a1: " + q.dequeue() + q.dequeue() + q.dequeue()); 
      GBFrame.pause();  
      
      q.clear(); q.enqueue("a",1); q.enqueue("b",2); q.enqueue("c",3);
      PriorityQueue r;
      if (q instanceof LinkedPriorityQueue)
        r = (PriorityQueue)(((LinkedPriorityQueue)q).clone());
      else
        r = (PriorityQueue)(((HeapPriorityQueue)q).clone());
      
      System.out.println ("Expect falsetruetrue : " + r.equals(null) 
                                                    + r.equals(r) 
                                                    + r.equals(q));
                                                    
      r.dequeue();
      System.out.println ("Expect false : " + r.equals(q)); 
      r.enqueue("c",3);
      System.out.println ("Expect true  : " + r.equals(q));
      r.enqueue("b",2);
      System.out.println ("Expect false : " + r.equals(q));  
      Object str[] = r.toArray();
      System.out.println ("Expect 4 cbba: " + str.length + " " + str[0]+str[1]+str[2]+str[3]);
      Collection col = r.collectionView();
      System.out.println ("Expect cbba  : " + col);                                             
      GBFrame.pause();  
  }
}

