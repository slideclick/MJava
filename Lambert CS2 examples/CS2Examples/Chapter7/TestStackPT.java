
public class TestStackPT{
   public static void main (String[] args){
      StackPT s = new ArrayStackPT(10);
      //StackPT s = new LinkedStackPT();
      
      try{
          String str = (String)s.peek();
      }catch (Exception e){
          System.out.println("Expect error because peeking when empty:\n" + e);
      }
      
      for (int i = 0; i < 10; i++ )
          s.push (new Integer(i));
          
      System.out.println ("Expect 987 : "+ s.pop() + s.pop() + s.pop()); 
      
      try{
         for (int i = 0; i < 4; i ++)
            s.push (new Integer(i));
      }catch (Exception e){
            System.out.println("Expect full stack error with ArrayStackPT:\n" + e);
      }
      
      System.out.print ("Expect 2106543210 for ArrayStackPT,\n" +
                        "Expect 32106543210 for LinkedStackPT : "); 
                          
      while (! s.isEmpty())
          System.out.print(s.pop());
   }                    
}

