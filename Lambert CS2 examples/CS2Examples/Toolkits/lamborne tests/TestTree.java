// TestGT
// (c) 1999 Ken Lambert and Martin Osborne

import lamborne.*;
import java.util.*;
import BreezyGUI.*;

public class TestTree{

  public static void main (String[] args){
      Tree a = new LinkedTree();
      Tree b = new LinkedTree();
      TreeIterator c = a.treeIterator();

      System.out.println ("#1<<<<<<<<<<<<<<<<<<<<<<<");
 
      b.addRoot ("a");
      
      c.addRoot ("x");
      c.setCurrent("a");
      c.addFirstChild ("x");
      c.setCurrent("b");
      c.addFirstChild ("c");
      c.addRightSibling ("d");
      c.addRightSibling ("x");
      c.setCurrent("e");
      
      System.out.println("b : " + a.get(new int[]{1}));
      System.out.println("c : " + a.get(new int[]{1,1}));
      System.out.println("d : " + a.get(new int[]{1,2}));
      System.out.println("e : " + a.get(new int[]{1,3}));
      System.out.println("a : " + a.get(new int[]{0}));
      System.out.println("null : " + a.get(new int[]{2}));

      a.clear();
      a.addRoot ("x");
      a.set(new int[]{0}, "a");
      a.addFirstChild (new int[]{}, "x");
      a.set(new int[] {1}, "b");
      a.addFirstChild (new int[] {1},"c");
      a.addRightSibling (new int[] {1,1},"d");
      a.addRightSibling (new int[] {1,2}, "x");
      a.set(new int[] {1,3}, "e");
      System.out.println("(a(b(c,d,e)) : " + a);
      GBFrame.pause();
  }
}


        

