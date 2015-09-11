// TestStack
// (c) 1999 Ken Lambert and Martin Osborne

import java.util.*;
import lamborne.*;
import BreezyGUI.*;

// NEED TO ADD TESTS FOR PRECONDITIONS

public class TestStack{

   public static void main (String[] args){

      lamborne.Stack s = new LinkedStack();

      // Test preconditions
      
      System.out.println
        ("=========Expect exceptions for peek, pop, and push\n");
      try {s.peek();}
        catch (Exception e) {System.out.println("Error: " + e.toString());}
      try {s.pop();}
        catch (Exception e) {System.out.println("Error: " + e.toString());}
      try {s.push(null);}
        catch (Exception e) {System.out.println("Error: " + e.toString());}

      GBFrame.pause();

      // Test basic operations
      
      s.push("a");
      s.push("b");
      s.push("c");
      s.push("d");
      System.out.println ("Expect d c b a : " + s.toString());
      System.out.println ("Expect dcb     : " + s.pop() + s.pop() + s.pop()); 
      System.out.println ("Expect a       : " + s.toString());
      s.push("x"); s.pop();
      System.out.println ("Expect 1       : " + s.size());
      System.out.println ("Expect a       : " + s.pop());
      System.out.println ("Expect no item : " + s.toString());

      GBFrame.pause();
      
      // Further tests
      
      s.push("a");
      System.out.println ("Expect false   : " + s.isEmpty());
      s.clear();
      System.out.println ("Expect true    : " + s.isEmpty());
      s.push("a"); s.push("b"); s.push("c");s.push("d");
      lamborne.Stack t = (lamborne.Stack)((LinkedStack)s).clone();
      lamborne.Stack u = new LinkedStack(s.collectionView());
      Object[] a = s.toArray();
      System.out.println ("Expect 4 dcba : " + a.length + " " + a[0]+a[1]+a[2]+a[3]);
      System.out.println ("Expect dcba twice : " + t + u);
      lamborne.Stack v = new LinkedStack(s.iterator());
      lamborne.Stack w = new LinkedStack();
      w.push("x"); w.push("b"); w.push("c"); w.push("d");
      v.pop();
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
      u.pop();
      Collection z = u.collectionView();
      System.out.println ("Expect true true false : "  + x.containsAll(y) + " "
                                                       + x.containsAll(z) + " "
                                                       + z.containsAll(x));
      System.out.println("Expect 2 exceptions==============");
      try {x.remove("a");}
        catch (Exception e) {System.out.println("Expect exception: " + e.toString());}
      try {s.pop(); iter.next();}
        catch (Exception e) {System.out.println("Expect exception: " + e.toString());}

      GBFrame.pause();

  }
}

