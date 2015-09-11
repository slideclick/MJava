import BreezyGUI.*;

public class Tester1{
   public static void main (String[] args){
      /*StackListPT s = new StackListPT();
      //StackArrayPT s = new StackArrayPT();
      s.clear();
      System.out.println (s.stateInformation());

      //Ken, we don't have to do this inside a try-catch. Remember, you
      //were wondering
      try{
          String str = (String)s.peek();
      }catch (Exception e){
          System.out.println("Error: " + e.toString());
      }

      try{
          for (int i = 0; i < 10; i++ )
              s.push (new Integer(i));
      }catch (Exception e){
          System.out.println("Error: " + e.toString());
      }

      System.out.println (""+ s.pop() + s.pop() + s.pop());
      System.out.println (s.stateInformation()); */

      StackListPT s = new StackListPT();
      try{
         Object obj = s.peek();
      }catch (Exception e){
         System.out.println("Error: " + e.toString());
      }
      GBFrame.pause();
   }
}

