// TestBinarySearchTree
// (c) 1999 Ken Lambert and Martin Osborne

import lamborne.*;
import java.util.*;
import BreezyGUI.*;

public class TestBinarySearchTree{

  public static void main (String[] args){
      String str;
      SortedCollection sc, testNull;
      List list;
      boolean testit;
      
      // Test preconditions
      testNull = new LinkedBSTSortedCollection();
      System.out.println
        ("=========Expect exceptions for insert and remove\n");
      try {testNull.add(null);}
        catch (Exception e) {System.out.println("Error: " + e.toString());}
      try {testNull.remove(null);}
        catch (Exception e) {System.out.println("Error: " + e.toString());}

      list = new LinkedList();
      list.add("x");
      list.add("h");
      list.add("b");
      list.add("k");
      list.add("d");
      list.add("s");
      list.add("a");
      list.add("s");

      System.out.println ("#1<<<<<<<<<<<<<<<<<<<<<<<");
      
      sc = new LinkedBSTSortedCollection(list, false);
      System.out.println ("Expect 8 abdhkssx               : " + sc.size() + sc);
      sc.clear();
      System.out.println ("Expect 0                        : " + sc.size());

      sc = new LinkedBSTSortedCollection(list, false);
      System.out.println ("Expect 8 xhbkdsas               : " + list.size() + " " + list);
      System.out.println ("Expect true                     : " + sc.contains("x"));
      System.out.println ("Expect true                     : " + sc.contains("s"));
      System.out.println ("Expect true                     : " + sc.contains("b"));
      System.out.println ("Expect true                     : " + sc.contains("a"));
      System.out.println ("Expect false                    : " + sc.contains("z"));
      System.out.println ("Expect false                    : " + sc.contains("t"));
      System.out.println ("Expect false                    : " + sc.contains("c"));
      System.out.println ("Expect x(h(b(a,d),k(,s(,s))),)  : " + 
                                        ((LinkedBSTSortedCollection)sc).debugString());
      str = "";
      Iterator iter = sc.iterator();
      while (iter.hasNext()) str += iter.next();
      System.out.println ("Expect abdhkssx                 : " + str);
      
      GBFrame.pause(); System.out.println ("#2<<<<<<<<<<<<<<<<<<<<<<<");
      
      System.out.println ("Expect 8                        : " + sc.size());
      str = "";
      for (int i = 0; i < sc.size(); i++){
          //System.out.println (i);
          str += sc.get(i); 
      }
      System.out.println ("Expect abdhkssx                 : " + str);
      
      GBFrame.pause(); System.out.println ("#3a<<<<<<<<<<<<<<<<<<<<<<<");
      
      System.out.println (sc.toString() +  " removing x "  + sc.remove("x"));
      System.out.println (sc.toString() +  " removing h "  + sc.remove("h"));
      System.out.println (sc.toString() +  " removing x " + sc.remove("x"));
      System.out.println (sc.toString() +  " removing s "  + sc.remove("s"));
      System.out.println (sc.toString() +  " removing s "  + sc.remove("s"));
      System.out.println (sc.toString() +  " removing a "  + sc.remove("a"));
      System.out.println (sc.toString() +  " removing d "  + sc.remove("d"));
      System.out.println (sc.toString() +  " removing b "  + sc.remove("b"));
      System.out.println (sc.toString() +  " removing k "  + sc.remove("k"));
      System.out.println (sc.toString() +  " removing x " + sc.remove("x"));
      System.out.println ("Expect 0 :" + sc.size());
      
      GBFrame.pause(); System.out.println ("#3b<<<<<<<<<<<<<<<<<<<<<<<");
      
      sc = new LinkedBSTSortedCollection(list, false);
      System.out.println (sc.toString() +  " removing a " + sc.remove("a"));
      System.out.println (sc.toString() +  " removing s "  + sc.remove("s"));
      System.out.println (sc.toString() +  " removing s "  + sc.remove("s"));
      System.out.println (sc.toString() +  " removing h "  + sc.remove("h"));
      System.out.println (sc.toString() +  " removing x "  + sc.remove("x"));
      System.out.println (sc.toString() +  " removing x " + sc.remove("x"));
      System.out.println (sc.toString() +  " removing d "  + sc.remove("d"));
      System.out.println (sc.toString() +  " removing k "  + sc.remove("k"));
      System.out.println (sc.toString() +  " removing b "  + sc.remove("b"));
      System.out.println (sc.toString() +  " removing x " + sc.remove("x"));
      System.out.println ("Expect 0 :" + sc.size());
      
      GBFrame.pause(); System.out.println ("#4<<<<<<<<<<<<<<<<<<<<<<<");

      iter = sc.iterator();
      str = "";
      while (iter.hasNext()) str += iter.next().toString();
      System.out.println ("Expect empty list               : " + str);
      sc.add("x");
      sc.add("h");
      sc.add("b");
      sc.add("k");
      sc.add("d");
      sc.add("s");
      sc.add("a");
      sc.add("s");
      System.out.println ("Expect x(h(b(a,d),k(,s(,s))),)  : " + 
                              ((LinkedBSTSortedCollection)sc).debugString());
      System.out.println ("Expect xsda                     : " + sc.get("x")
                                                               + sc.get("s")
                                                               + sc.get("d")
                                                               + sc.get("a"));
      testit = (sc.get("c") == null);                                                         
      System.out.println ("Expect true                     : " + testit);

      GBFrame.pause(); System.out.println ("#5<<<<<<<<<<<<<<<<<<<<<<<");

      sc = new LinkedBSTSortedCollection();
      sc.addUnique("x");
      sc.addUnique("h");
      sc.addUnique("b");
      sc.addUnique("k");
      sc.addUnique("d");
      sc.addUnique("s");
      sc.addUnique("a");
      sc.addUnique("s");
      sc.addUnique("x");
      sc.addUnique("h");
      sc.addUnique("b");
      sc.addUnique("k");
      sc.addUnique("d");
      sc.addUnique("s");
      sc.addUnique("a");
      System.out.println ("Expect x(h(b(a,d),k(,s)),)  : " + 
                               ((LinkedBSTSortedCollection)sc).debugString());

      GBFrame.pause(); 
      System.out.println ("#6<<<<<<<<<<<<<<<<<<<<<<");
      
      sc = new LinkedBSTSortedCollection (list, false);
      SortedCollection sc2 = new LinkedBSTSortedCollection (list, false);
      SortedCollection sc3 = new LinkedBSTSortedCollection (list, true);
      SortedCollection sc4 = (SortedCollection)((LinkedBSTSortedCollection)sc).clone();
      System.out.println ("ttft : " + sc.equals(sc) + sc.equals(sc2)
                                    + sc2.equals(sc3) + sc.equals(sc4));
      sc.add("o"); sc4.add("p");
      System.out.println ("f : " + sc.equals(sc4));
      sc4.remove("p");
      
      System.out.println ("abdhkossx : " + sc);
      System.out.println ("ahx : " + sc.get(0)+sc.get(3)+sc.get(8));
      try{sc.get(9);}                                                      
      catch(Exception e){System.out.println ("Index error : " + e);}
      System.out.println ("Hash code : " + sc.hashCode());
      sc2.clear();
      System.out.println ("ft : " + sc.isEmpty() + sc2.isEmpty());
      System.out.println ("abxo : " + sc.remove(0)+sc.remove(0)+sc.remove(6)+sc.remove(3));
      
     
  }
}

        

