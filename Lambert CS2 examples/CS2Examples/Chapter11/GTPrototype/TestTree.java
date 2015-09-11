import lamborne.*;
import java.util.*;

public class TestTree{

   public static void main (String[] args){
      Tree a = new LinkedTree();
            
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
      
      System.out.println("Hierarchical list: \n" + a.hierarchicalList());

      System.out.println("b : " + a.get(new int[]{1}));
      System.out.println("c : " + a.get(new int[]{1,1}));
      System.out.println("d : " + a.get(new int[]{1,2}));
      System.out.println("e : " + a.get(new int[]{1,3}));
      System.out.println("a : " + a.get(new int[]{0}));
      
      System.out.println("null : " + a.get(new int[]{2}));
   }
}