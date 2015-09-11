import BreezyGUI.*;
import java.util.*;
import structures.*;
import structures.Stack;

public class Tester2{
   public static void main (String[] args){
      Stack s = new StackLinked();
      try{
         Object obj = s.peek();
      }catch (Exception e){
         System.out.println("Error: " + e.toString());
      }
      /*//OneWayNode n;      //This line used to make sure that
                           //OneWayNode not visible here
      s.clear();
      //s.push (null);
      
      System.out.println (s.toString());
      
      //Ken, we don't have to do this inside a try-catch. Remember, you 
      //were wondering
      try{
          String str = (String)s.peek();
      }catch (Exception e){
          System.out.println("Error: " + e.toString());
      }
      
      try{
          s.push("a");
          s.push("b");
          s.push("c");
          s.push("d");
      }catch (Exception e){
          System.out.println("Error: " + e.toString());
      }

      System.out.println (""+ s.pop() + s.pop() + s.pop()); 
      System.out.println (s.toString());
      */

      GBFrame.pause();
   }    
}

