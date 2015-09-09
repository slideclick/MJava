import ds.util.ALStack;

public class PostfixEval
{
   // the postfix expression to evaluate
   private String postfixExpression;
   // stack of operands
   private ALStack<Integer> operandStack;

   // pop an operand from the stack.
   // Precondition: the stack must have at least one entry.
   // if the stack is empty prior to a pop() operation, the
   // function throws ArithmeticException
   private int getOperand()
   {
      // can we pop an operand?
      if (operandStack.isEmpty())
         throw new ArithmeticException("PostfixEval: Too many operators");

      // pop the operand
      return operandStack.pop();
   }

   // compute "left op right". if right is 0 and op
   // is '/' or '%', the function throws ArithmeticException
   private int compute(int left, int right, char op)
   {
      int value = 0;

      // evaluate "left op right"
      switch(op)
      {
         case '+':   value = left + right;
                     break;

         case '-':   value = left - right;
                     break;

         case '*':   value = left * right;
                     break;

         case '%':   if (right == 0)
                        throw new ArithmeticException(
                           "PostfixEval: divide by 0");
                     value = left % right;
                     break;

         case '/':   if (right == 0)
                        throw new ArithmeticException(
                           "PostfixEval: divide by 0");
                     value = left / right;
                     break;

         case '^':   // make sure we are not computing 0^0
                     if (left == 0 && right == 0)
                        throw new ArithmeticException(
                           "PostfixEval: 0^0 undefined");

                     value = 1;
                     // general case. compute value =  1*left*...*left.
                     // if right == 0, skip the loop and left^0 is 1
                     while (right > 0)
                     {
                        value *= left;
                        right--;
                     }
                     break;
      }

      return value;
   }


   // is ch one of '+','-','*','/','%','^'
   private boolean isOperator(char ch)
   {
      return ch == '+' || ch == '-' || ch == '*' ||
             ch == '%' || ch == '/' || ch == '^';
   }


   // default constructor. postfix expression is NULL string
   public PostfixEval()
   {
      postfixExpression = new String();
      operandStack = new ALStack<Integer>();
   }

   // return the postfix expression
   public String getPostfixExp()
   {
      return postfixExpression;
   }

   // change the postfix expression
   public void setPostfixExp(String postfixExp)
   {
      postfixExpression = postfixExp;
   }

   // evaluate the postfix expression and return
   // its value. the function throws ArithmeticException
   // if an error occurs during evaluation
   public int evaluate()
   {
      // expValue contains the evaluated expression
      int left, right, expValue;
      char ch;
      int i;

      // process characters until the end of the string is reached
      // or an error occurs
      for (i=0; i < postfixExpression.length(); i++)
      {
         // get the current character
         ch = postfixExpression.charAt(i);

         // look for an operand, which is a single digit
         // non-negative integer
         if (Character.isDigit(ch))
            // value of operand goes on the stack
            operandStack.push(ch - '0');
         // look for an operator
         else if (isOperator(ch))
         {
            // pop the stack to obtain the right operand
            right = getOperand();
            // pop the stack to obtain the left operand
            left = getOperand();
            // evaluate "left op right" and push on stack
            operandStack.push(compute(left, right, ch));
         }
         // any other character must be whitespace.
         // whitespace includes blank, tab, and newline
         else if (!Character.isWhitespace(ch))
            throw new ArithmeticException("PostfixEval: Improper char");
      }

      // the expression value is on the top of the stack.
      // pop it off
      expValue = ((Integer)operandStack.pop()).intValue();

      // if data remains on the stack, there are too
      // many operands
      if (!operandStack.isEmpty())
         throw new ArithmeticException("PostfixEval: Too many operands");

      return expValue;
   }
}
