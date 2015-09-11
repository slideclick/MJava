import lamborne.*;
import java.util.*;
import ioutil.*;

public class TestBTPT{

   public static void main (String[] args){
      BinaryTreePT a, b, c;
      KeyboardReader reader = new KeyboardReader();
      
      System.out.println ("#1<<<<<<<<<<<<<<<<<<<<<<<");
      
      a = new BinaryTreePT();
      System.out.println ("Expect empty string          : " + a);
      a = new BinaryTreePT("5");
      System.out.println ("Expect 5                     : " + a);
      b = new BinaryTreePT ("+", new BinaryTreePT ("3"), 
                                 new BinaryTreePT ("4"));
      System.out.println ("Expect +(3,4)                : " + b);
      c = new BinaryTreePT ("*", a, b);
      System.out.println ("Expect *(5,+(3,4))           : " + c);
      c = new BinaryTreePT ("-", c, b);
      System.out.println ("Expect -(*(5,+(3,4)),+(3,4)) : " + c);
      System.out.println ("Expect 9                     : " + c.size());
   
      System.out.println ("#2<<<<<<<<<<<<<<<<<<<<<<<");
      
      System.out.println ("Expect -*5+34+34             : " + 
                          c.toStringPreorder());
      System.out.println ("Expect 5*3+4-3+4             : " + 
                          c.toStringInorder());
      System.out.println ("Expect 534+*34+-             : " +
                          c.toStringPostorder());
      System.out.println ("Expect -*+5+3434             : " + 
                          c.toStringLevelorder());
                     
      System.out.println ("#3<<<<<<<<<<<<<<<<<<<<<<<");
      
      BinaryTreePT d = (BinaryTreePT)c.clone();
      BinaryTreePT e = new BinaryTreePT ("*", a, b);
      e = new BinaryTreePT ("-", e, b);
      BinaryTreePT f = new BinaryTreePT ("*", a, b);
      f = new BinaryTreePT ("-", b, f);
      System.out.println ("Expect equal trees : " + c + "\n" + 
                          "                   : " + d + "\n" +
                          "                   : " + e);
      System.out.println ("Expect tt ff ft f: " + 
                          c.equals(c) + c.equals(d)   + " "  +
                          c.equals(b)   + c.equals("x") +  " "  +
                          c.equals(null) + c.equals(e) +  " " +
                          c.equals(f) );
   }
}