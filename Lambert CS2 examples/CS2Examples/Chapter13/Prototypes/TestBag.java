import java.util.*;
import lamborne.*;

// NEED TO ADD TESTS FOR PRECONDITIONS

public class TestBag{
   public static void main (String[] args){
      HashBagPT b = new HashBagPT();
      HashBagPT c;
      
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
      
      System.out.println ("Expect bag a4c1: \n" + b);
      System.out.println ("Expect 5          : " + b.size());
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
      
      b.clear();
      System.out.println ("Expect \n-1 true empty bag\n" 
                            + b.remove("x") + " "
                            + b.isEmpty()   + " "
                            + "\n" + b);

  }
}

