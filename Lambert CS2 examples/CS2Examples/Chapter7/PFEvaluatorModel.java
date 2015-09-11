// PFEvaluatorModel
// (c) 2000 Ken Lambert and Martin Osborne

import java.util.*;

public class PFEvaluatorModel {

   private PFEvaluator evaluator;

   public int evaluate (String expressionStr)
   {
      evaluator = new PFEvaluator(new Scanner (expressionStr));
      int value = evaluator.evaluate();
      return value;
   }
   
   public String format (String expressionStr)
   {
      String normalizedStr = "";
      Iterator scanner = new Scanner (expressionStr);
      while (scanner.hasNext())
         normalizedStr += (Token) scanner.next() + " ";
      return normalizedStr;
   }   

   public String evaluationStatus()
   {
      return evaluator.toString();
   }
}
