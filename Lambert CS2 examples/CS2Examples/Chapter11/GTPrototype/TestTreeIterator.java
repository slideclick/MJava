import lamborne.*;
import java.util.*;

public class TestTreeIterator{

  public static void main (String[] args){
      Tree a = new LinkedTree();
      TreeIterator c = a.treeIterator();
     
      c.addRoot ("x");
      c.setCurrent("a");
      c.addFirstChild ("x");
      c.setCurrent("b");
      c.addFirstChild ("c");
      c.addRightSibling ("d");
      c.addRightSibling ("x");
      c.setCurrent("e");
      
      System.out.println("(a(b(c,d,e)) : " + a);
      
      // Now use iterator to access nodes
      System.out.println("a    : " + c.getRoot());
      System.out.println("b    : " + c.getFirstChild());
      System.out.println("c    : " + c.getFirstChild());
      System.out.println("d    : " + c.getRightSibling());
      System.out.println("e    : " + c.getRightSibling());
      System.out.println("null : " + c.getRightSibling());
      System.out.println("b    : " + c.getParent());
      System.out.println("a    : " + c.getParent());
  }
}