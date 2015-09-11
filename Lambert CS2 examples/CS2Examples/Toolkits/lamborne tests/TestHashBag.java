// TestBag
// (c) 1999 Ken Lambert and Martin Osborne

import java.util.*;
import lamborne.*;
import BreezyGUI.*;

// NEED TO ADD TESTS FOR PRECONDITIONS

public class TestHashBag{
   public static void main (String[] args){
      Bag b = new HashBag(3);
      Bag c,d;
      
      b.add("a");
      b.add("a");
      b.add("b");
      b.add("a");
      b.add("a");
      b.add("a");
      b.add("c");
      b.remove("a");
      b.remove("b");
      b.remove("c");
      b.add("c");
      System.out.println ("Expect hashcode + bag a4c1 using debugString: \n" +
                            b.hashCode() + "\n" + ((HashBag)b).debugString());
      System.out.println ("Expect bag a4c1 using toString \n" + b);
      
      c = (HashBag)b.clone();
      System.out.println ("Clone test expect a4c1 :\n" + ((HashBag)c).debugString());
      System.out.println ("Equality test expect true true: " + c.equals(b) + " " + b.equals(c));
      
      c = new HashBag (b.iterator(),1); c.add("c");
      System.out.println ("Constructor test expect a1c2 :\n" + ((HashBag)c).debugString());
      System.out.println ("Constructor test expect a1c2 :\n" + c);
      
      d = new HashBag (b.collectionView(),3); d.add("c");
      System.out.println ("Constructor test expect a1c2 :\n" + d);
      System.out.println ("Equality test expect true true true: " 
                           + c.equals(d) + " " + d.equals (c) + " " + c.equals(c));
                           
      GBFrame.pause();
      
      c.remove("a"); c.add("b"); d.remove("c"); d.add("a");
      System.out.println ("\nEquality test expect false false 3 3: " 
                           + c.equals(b) + " " + b.equals(c) + " " + c.size() + " " + d.size());
                           

      String str =        "Expect \n 4 0 1 0 true false 5 3 false -1 0\n "
                          + b.getCount("a")  + " "
                          + b.getCount("b")  + " "
                          + b.getCount("c")  + " "
                          + b.getCount("d")  + " "
                          + b.contains("a")     + " "
                          + b.contains("x")     + " "
                          + b.size()            + " "
                          + b.remove("a")       + " "
                          + b.isEmpty()         + " ";
      
      str += b.remove("b") + " ";
      str += b.remove("c") + " ";
      
      System.out.println (str);
      System.out.println ("Expect bag a3: \n" + b);
      b.add("a"); b.add("c"); b.add("d"); b.add("e"); b.add("e"); b.add("b");
      System.out.println ("Expect 9 a4b1c1d1e2:  " + b.size() + " " + b);
      
      b.clear();
      System.out.println ("Expect \n-1 true 0 empty bag\n" 
                            + b.remove("x") + " "
                            + b.isEmpty()   + " "
                            + b.size()      + " "
                            + "\n" + b);
                            

  }
}

