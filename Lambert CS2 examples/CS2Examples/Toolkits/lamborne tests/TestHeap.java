// TestHeap
// (c) 1999 Ken Lambert and Martin Osborne

import lamborne.*;
import java.util.*;
import BreezyGUI.*;

// NEED TO ADD TESTS FOR PRECONDITIONS

public class TestHeap{

  public static void main (String[] args){
      String str;
      Heap heap = new ArrayHeap(1);
      List list;

      // Test preconditions
      System.out.println
        ("=========Expect exceptions for pop, add, and constructor\n");
      try {heap.pop();}
        catch (Exception e) {System.out.println("Error: " + e);}
      try {heap.add(null);}
        catch (Exception e) {System.out.println("Error: " + e);}
      try {new ArrayHeap(0);}
        catch (Exception e) {System.out.println("Error: " + e);}
      GBFrame.pause();
     
      list = new LinkedList();
      list.add("x"); list.add("h"); list.add("b"); list.add("k");
      list.add("d"); list.add("s"); list.add("a"); list.add("s");
      List list2 = new LinkedList();
      list2.add("x"); list2.add("s"); list2.add("a"); list2.add("s");
      list2.add("d"); list2.add("h"); list2.add("b"); list2.add("k");

      System.out.println ("#1<<<<<<<<<<<<<<<<<<<<<<<");
      
      heap.add("x"); heap.add("h"); heap.add("b"); heap.add("k");
      heap.add("d"); heap.add("s"); heap.add("a"); heap.add("s");
      
      System.out.println ("Expect 8            : " + heap.size());
      heap.clear();
      System.out.println ("Expect 0            : " + heap.size());

      heap.add("x"); heap.add("h"); heap.add("b"); heap.add("k");
      heap.add("d"); heap.add("s"); heap.add("a"); heap.add("s");
      heap.pop(); heap.pop();
      heap.add("s"); heap.add("x");
      System.out.println ("Expect xsskhdba     : " + heap);   
      System.out.println ("Expect x            : " + heap.peek());
      System.out.println ("Expect xsskhdba     : " + heap.pop()
                                                   + heap.pop()
                                                   + heap.pop()
                                                   + heap.pop()
                                                   + heap.pop()
                                                   + heap.pop()
                                                   + heap.pop()
                                                   + heap.pop()
                         );
      System.out.println ("Expect 0            : " + heap.size());
      System.out.println ("Expect empty heap   : " + heap); 
      
      GBFrame.pause();System.out.println ("#2<<<<<<<<<<<<<<<<<<<<<<<");
      heap = new ArrayHeap (list.iterator());
      ArrayHeap heap2 = new ArrayHeap (list2);
      ArrayHeap heap3 = (ArrayHeap)(heap2.clone());
      ArrayHeap heap4 = new ArrayHeap (list); heap4.pop();
//      System.out.println ("Debug heap2 : " + heap2);
//      System.out.println ("Debug heap3 : " + heap3);
      System.out.println ("Expect xsskhdba  ttff   : " + heap + " " +      
                           heap.equals(heap2) + heap2.equals(heap3)+heap3.equals(heap4)+
                           heap.equals("a"));  
 
      Object items[] = heap.toArray();
      Object temp = items[1]; items[1] = items[2]; items[2] = temp;
      items = AbstractHeap.sort (items);
      str = "";
      for (int i = 0; i < items.length; i++)
          str += items[i];
      System.out.println ("Expect abdhkssx     : " + str);
      
      items = AbstractHeap.sort (list2);
      str = "";
      for (int i = 0; i < items.length; i++)
          str += items[i];
      System.out.println ("Expect abdhkssx     : " + str);
      GBFrame.pause();
  }
}


        

