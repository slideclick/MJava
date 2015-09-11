// TestGT
// (c) 1999 Ken Lambert and Martin Osborne

import lamborne.*;
import java.util.*;
import BreezyGUI.*;

// NEED TO ADD TESTS FOR PRECONDITIONS

public class TestTreeIterator{

  public static void main (String[] args){
      Tree t = new LinkedTree();
      Tree u = new LinkedTree();
      Tree v = new LinkedTree();
      TreeIterator a,b,c;
      Object item;
      

      System.out.println ("#1<<<<<<<<<<<<<<<<<<<<<<<");
      
      a = t.treeIterator();
      b = u.treeIterator();
      c = v.treeIterator();
      
      b.addRoot ("a");
      
      c.addRoot ("x");
      c.setCurrent("a");
      c.addFirstChild ("x");
      c.setCurrent("b");
      c.addFirstChild ("c");
      c.addRightSibling ("d");
      c.addRightSibling ("x");
      c.setCurrent("e");

      c.getParent();
      c.addRightSibling ("f");
      c.addFirstChild ("h");
      c.getParent();
      c.addFirstChild ("g");
      c.getRightSibling();
      c.addFirstChild("i");
      c.getParent();
      c.getParent();
      c.addRightSibling("j");
      System.out.println ("Expect empty tree              : " + t);
      System.out.println ("Expect a                       : " + u);
      System.out.println ("Expect a(b(c,d,e),f(g,h(i)),j) : " + v);
      System.out.println ("Expect 0                       : " + t.size());
      System.out.println ("Expect 1                       : " + u.size());
      System.out.println ("Expect 10                      : " + v.size());
      
      GBFrame.pause();
      System.out.println ("Expect a(b(c,d,e),f(g,h(i)),j)  :" + v);
      System.out.println ("Hierarchical List v:\n" + v.hierarchicalList());
      GBFrame.pause();
      
      item = c.getRoot();
      System.out.println ("Expect a false false true true : " + c.getCurrent() 
                                                              + " " + c.hasRightSibling()
                                                              + " " + c.hasParent()
                                                              + " " + c.hasChild()
                                                              + " " + c.hasCurrentPosition());
      c.getFirstChild(); c.getRightSibling(); c.getRightSibling();                                                        
      System.out.println ("Expect j false true false true : " + c.getCurrent() 
                                                              + " " + c.hasRightSibling()
                                                              + " " + c.hasParent()
                                                              + " " + c.hasChild()
                                                              + " " + c.hasCurrentPosition());
      c.getParent();c.getFirstChild(); c.getRightSibling(); c.getFirstChild();                                                        
      System.out.println ("Expect g true true false true  : " + c.getCurrent() 
                                                              + " " + c.hasRightSibling()
                                                              + " " + c.hasParent()
                                                              + " " + c.hasChild()
                                                              + " " + c.hasCurrentPosition());
      System.out.println ("Expect hihfa  null abc null null : " + c.getRightSibling()
                                                              + c.getFirstChild()
                                                              + c.getParent() 
                                                              + c.getParent()
                                                              + c.getParent()
                                                              + " " + c.getParent() + " "
                                                              + c.getRoot()
                                                              + c.getFirstChild()
                                                              + c.getFirstChild()
                                                              + " " + c.getFirstChild() + " "
                                                              + " " + c.getFirstChild() + " "
                                                              );

      GBFrame.pause();
      
      System.out.println ("Exepct c : " + c.getParent());
      System.out.println ("Expect d : " + c.getRightSibling()); 
      System.out.println ("Expect e : " + c.getRightSibling()); 
      System.out.println ("Expect e : " + c.removeCurrent());         
      System.out.println ("Expect b : " + c.getParent());      
      System.out.println ("Expect c : " + c.getFirstChild());   
      System.out.println ("Expect c : " + c.removeCurrent());        
      System.out.println ("Expect d : " + c.removeCurrent());        
      System.out.println ("Expect b : " + c.getParent());   
      System.out.println ("Expect false                   : " + c.hasChild());
      c.removeCurrent() /* now on f */ ; c.getFirstChild(); c.removeCurrent();
      System.out.println ("Expect a(f(h(i)),j)            : "  + v);
  }
}


        

