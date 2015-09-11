import java.util.*;
import lamborne.*;

// NEED TO ADD TESTS FOR PRECONDITIONS  AND elements

public class TestSet{
   public static void main (String[] args){
      HashSetPT s = new HashSetPT();
      /*
      Set r = new SetChained(3);
      Set t = new SetChained(1);
      Set u = new SetChained(2);
      Set v,w,x,y,z;
      */
      
      System.out.println ("Expect false: " + s.contains("a"));
      
      System.out.println ("Expect \ntrue true true true false false:\n"
                            + s.add("a") + " "
                            + s.add("b") + " "
                            + s.add("c") + " "
                            + s.add("d") + " "
                            + s.add("b") + " "
                            + s.add("b"));
      System.out.println ("Expect the set abcd: \n" + s);
      System.out.println ("Expect \n true false true true true:\n " 
                            + s.contains("a") + " "
                            + s.contains("x") + " "
                            + s.contains("b") + " "
                            + s.contains("c") + " "
                            + s.contains("d"));
      System.out.println ("Expect \n true false true true true false\n " 
                            + s.remove("a") + " "
                            + s.remove("x") + " "
                            + s.remove("b") + " "
                            + s.remove("c") + " "
                            + s.remove("d") + " "
                            + s.remove("x"));
      System.out.println ("Expect the empty set: \n" + s);
      System.out.println ("Expect true     : " + s.isEmpty());
      
      /*
      
      r.add("u"); r.add("v"); r.add("w"); r.add("x");
      s.add("a"); s.add("b"); s.add("c"); s.add("d");
      t.add("b"); t.add("c"); 
      u.add("a"); u.add("b"); u.add("x"); u.add("y");
      v = r.intersection(r).intersection(u).union(s).difference(t);
      w = (s.union(u)).difference(s.intersection(u));
      x = (s.difference(u)).union(u.difference(s)); 
      System.out.println ("Expect the set adx: \n" + v);
      System.out.println ("Expect the set cdxy: \n" + w);

      System.out.println ("Expect the set cdxy: \n" + x.debugString());
      System.out.println ("Expect 4           : "   + x.size());
      System.out.println ("Expect true  :" + w.equals(w));
      System.out.println ("Expect true  :" + w.equals(x));
      System.out.println ("Expect false :" + s.equals(t));
      System.out.println ("Expect false :" + s.equals(u));
      System.out.println ("Expect false :" + u.equals(s));
      
      System.out.println ("Expect true  :" + u.subset(u));
      System.out.println ("Expect true  :" + t.subset(s));
      System.out.println ("Expect false :" + t.subset(u));
      System.out.println ("Expect false :" + u.subset(s));
      */                                      
                                               
  }
}

