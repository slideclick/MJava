import java.util.*;

public class TestListIteratorPT{
    
   public static void main (String[] args){

     // Uncomment one of these at a time and test
      //ListPT list = new ArrayListPT();
      ListPT list = new LinkedListPT();


      list.add(0, "h");
      list.add(1, "p");
      list.add(2, "x");
      list.add(0, "g");
      list.add(0, "a");
      list.add(3, "k");
      System.out.println ("#1<<<<<<<<<<<<<<<<<<<<<<<");
      System.out.print ("Expect aghkpx : " + list.toString());
 
      System.out.println ("#2<<<<<<<<<<<<<<<<<<<<<<<");
      System.out.print ("Expect aghkpx : ");
      ListIteratorPT iter = list.iterator();
      while (iter.hasNext())
         System.out.print(iter.next() + " ");
          
      System.out.println ("#3<<<<<<<<<<<<<<<<<<<<<<<");
      System.out.print ("Expect xpkhga : "); 
      while (iter.hasPrevious())
         System.out.print(iter.previous() + " ");

      System.out.println ("#4<<<<<<<<<<<<<<<<<<<<<<<");
      System.out.print ("Expect aghkpx : ");
      while (iter.hasNext())
         System.out.print(iter.next() + " ");
      
      while (iter.hasPrevious()){
         String s = (String)iter.previous();
         if (s.equals("h"))
            iter.remove();
      }
 
      System.out.println ("#5<<<<<<<<<<<<<<<<<<<<<<<");
      System.out.print ("Expect agkpx : ");
      while (iter.hasNext())
         System.out.print(iter.next() + " ");
         
      while (iter.hasPrevious()){
         String s = (String)iter.previous();
         if (s.equals("k"))
            iter.add("z");
      }
 
      System.out.println ("#6<<<<<<<<<<<<<<<<<<<<<<<");
      System.out.print ("Expect agzkpx : ");
      while (iter.hasNext())
         System.out.print(iter.next() + " ");

   }
}

