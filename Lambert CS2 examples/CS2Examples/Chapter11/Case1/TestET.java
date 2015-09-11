import java.util.*;

public class TestET{

   public static void main (String[] args){
      ExpressionTree a, b, c;
      
      a = new ExpressionTree (new Token(4));
      b = new ExpressionTree (new Token('+'), 
                              new ExpressionTree (new Token(2)), 
                              new ExpressionTree (new Token(3)));
      c = new ExpressionTree (new Token('*'), a, b);
      c = new ExpressionTree (new Token('-'), c, b); 

      System.out.println ("Expect ((4*(2+3))-(2+3))     : " + c.infix());
      System.out.println ("Expect -*4+23+23             : " + c.prefix());
      System.out.println ("Expect 423+*23+-             : " + c.postfix());
      System.out.println ("Expect -(*(4,+(2,3)),+(2,3)) : " + c.toString());
      System.out.println ("Expect 15                    : " + c.evaluate());
      System.out.println ("Expect exception");
      a = new ExpressionTree();
   }
}