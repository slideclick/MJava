import ds.util.ALStack;

public class InfixToPostfix
{
   // the infix expression to convert
   private String infixExpression = null;
   // built to contain the postfix equivalent of infixExpression
   private String postfixExpression = null;
   // stack of Symbol objects
   private ALStack<Symbol> operatorStack = null;

   // the Symbol object op holds the current
   // symbol. pop the stack and output as long as the symbol
   // on the top of the stack has a precedence >= that of
   // the current operator
   private void clearToLowerPrec(Symbol op)
   {
      Symbol op2;

      while(!operatorStack.isEmpty() &&
         (op2 = operatorStack.peek()).compareTo(op) >= 0)
      {
         operatorStack.pop();
         postfixExpression += op2.getOp() + " ";
      }
   }

   // is ch one of '+','-','*','/','%','^'
   private boolean isOperator(char ch)
   {
      return ch == '+' || ch == '-' || ch == '*' ||
             ch == '%' || ch == '/' || ch == '^';
   }

   // default constructor. infix and postfix expressions
   // are NULL strings. create an empty stack
   public InfixToPostfix()
   {
      infixExpression = "";
      postfixExpression = "";
      operatorStack = new ALStack<Symbol>();
   }

   // initialize the infix expression. postfix expression
   // is NULL string. create an empty stack
   public InfixToPostfix(String infixExp)
   {
      infixExpression = infixExp;
      postfixExpression = "";
      operatorStack = new ALStack<Symbol>();
   }

   // change the infix expression
   public void setInfixExp(String infixExp)
   {
      // assign a new infix expression
      infixExpression = infixExp;
      // make postfixExpression the NULL String
      postfixExpression = "";
   }

   // return a string that contains the equivalent postfix
   // expression. the function throws ArithmeticException
   // if an error occurs during conversion
   public String toPostfix()
   {
      Symbol op;
      // maintain rank for error checking
      int rank = 0, i;
      char ch;

      // process until end of the expression
      for (i=0; i < infixExpression.length(); i++)
      {
         ch = infixExpression.charAt(i);

         //  ********  process an operand  ********
         // an operand is a single digit non-negative integer
         if (Character.isDigit(ch))
         {
            // just add operand to output expression, followed by
            // a blank
            postfixExpression += ch + " ";
            // rank of an operand is 1, accumulated rank
            // must be 1
            rank++;
            if (rank > 1)
               throw new ArithmeticException(
               	"InfixToPostfix: Operator expected");
         }
         //  *********  process an operator or '('  **********
         else if (isOperator(ch) || ch == '(')
         {
            // rank of an operator is -1. rank of '(' is 0.
            // accumulated rank should be 0
            if (ch != '(')
                  rank--;

            if (rank < 0)
               throw new ArithmeticException(
               	"InfixToPostfix: Operand expected");
            else
            {
               // output the operators on the stack with higher
               // or equal precedence. push the current operator
               // on the stack
               op = new Symbol(ch);
               clearToLowerPrec(op);
               operatorStack.push(op);
            }
         }
         //  *********  process a right parenthesis  **********
         else if (ch == ')')
         {
            // build an Symbol object holding ')', which
            // has precedence lower than the stack precedence
            // of any operator except '('. pop the operator stack
            // and output operators from the subexpression until
            // '(' surfaces or the stack is empty. if stack is
            // empty, a '(' is missing; otherwise, pop off '('.
            op = new Symbol(ch);
            clearToLowerPrec(op);
            if(operatorStack.isEmpty())
               throw new ArithmeticException(
               	"InfixToPostfix: Missing '('");
            else
               operatorStack.pop(); // get rid of '('
         }
         //  *********  make sure ch is whitespace  **********
         else if (!Character.isWhitespace(ch))
            throw new ArithmeticException(
            	"InfixToPostfix: Invalid input");
      }

      // finish processing
      if (rank != 1)
         throw new ArithmeticException(
         	"InfixToPostfix: Operand expected");
      else
      {
         // flush operator stack and complete expression
         // evaluation. if find left parenthesis, a right
         // parenthesis is missing.
         while (!operatorStack.isEmpty())
         {
            op = (Symbol)operatorStack.pop();
            if (op.getOp() == '(')
               throw new ArithmeticException(
               	"InfixToPostfix: Missing ')'");
            else
               postfixExpression += op.getOp() + " ";
         }
      }

      return postfixExpression;
	}
}
