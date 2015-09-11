// PFEvaluator
// (c) 2000 Ken Lambert and Martin Osborne

import java.util.Iterator;
import lamborne.*;

public class PFEvaluator {

   private String expressionSoFar;
   private Stack operandStack;
   private Iterator scanner;
   
   public PFEvaluator()
   {
      this (null);
   }
   
   public PFEvaluator (Iterator scanner)
   {
      expressionSoFar = "";
      operandStack = new LinkedStack();
      this.scanner = scanner;
   }   

   public int evaluate(){
      if (scanner == null || !scanner.hasNext()){
         throw new IllegalArgumentException("Expression contains no tokens");
      }

      Token t1, t2, currentToken, result;
      while (scanner.hasNext()){
         currentToken = (Token) scanner.next();
         expressionSoFar += currentToken + " ";
         if(currentToken.getType() == Token.INT){
            operandStack.push(currentToken);
         }else if(currentToken.isOperator()){ 
            if (operandStack.size() < 2)
               throw new RuntimeException ("Too few operands on the stack");
            t2 = (Token) operandStack.pop();  // Right operand went on last
            t1 = (Token) operandStack.pop();
            result = 
               new Token (computeValue(currentToken, t1.getValue(), 
                                                     t2.getValue()));
            operandStack.push(result);
        }else
           throw new RuntimeException ("Unknown token type");
      }
      
      if (operandStack.size() > 1)
         throw new RuntimeException ("Too many operands on the stack"); 
            
      result = (Token) operandStack.pop();
      return result.getValue();
   }
   
   public String evaluationStatus()
   {
      String str = "";
      if (expressionSoFar == "")
         str += "Portion of expression processed: none\n";
      else 
         str += "Portion of expression processed: " + expressionSoFar + "\n";
      if (operandStack.isEmpty())
         str += "The stack is empty";
      else{
         Iterator iter = operandStack.iterator();
         String operands = "";
         while (iter.hasNext())
            operands = iter.next() + " " + operands;
         str += "Operands on the stack          : " + operands;
      }
      return str;
   } 

   private int computeValue(Token op, int value1, int value2){
      int result = 0;
      switch (op.getType()){
         case Token.PLUS:
            result = value1 + value2;
            break;
         case Token.MINUS:
            result = value1 - value2;
            break;
         case Token.MUL:
            result = value1 * value2;
            break;
         case Token.DIV:
            result = value1 / value2;
            break;
         default:
            throw new RuntimeException ("Unknown operator"); 
      }
      return result;
   }

}
