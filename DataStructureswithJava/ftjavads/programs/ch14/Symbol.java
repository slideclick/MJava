// a class that maintains the precedence of operators
public class Symbol implements Comparable<Symbol>
{
      private char op;        // operator
      private int inputPrec;     // input precedence of op
      private int stackPrec;     // stack precedence of op

      // initializes the object for operator ch
      public Symbol (char ch)
      {
         op = ch;    // assign operator

         switch(op)
         {
            // '+' and '-' have input/stack precedence 1
            case '+':
            case '-':   inputPrec = 1;
                     	stackPrec = 1;
                     	break;

            // '*', '%', and '/' have input/stack precedence 2
            case '*':
            case '%':
            case '/':   inputPrec = 2;
                     	stackPrec = 2;
                     	break;

            // '^' is right associative. input precedence 4
            // and stack precedence 3
            case '^':   inputPrec = 4;
                     	stackPrec = 3;
                     	break;

            // '(' has input precendence 5, stack precedence -1
            case '(':   inputPrec = 5;
                     	stackPrec = -1;
                     	break;

            // ')' has input/stack precedence 0
            case ')':   inputPrec = 0;
                     	stackPrec = 0;
                     	break;
         }
      }

      // compare stackPrec of the current object to inputPrec of
      // obj. determines whether the operator on the stack should
      // be output before pushing operator obj.op on the stack
      public int compareTo(Symbol item)
      {
         int result;
         if (stackPrec < item.inputPrec)
            result = -1;
         else if (stackPrec == item.inputPrec)
            result = 0;
         else
            result = 1;

         return result;
      }

      // return operator
      public char getOp()
      { return op; }
}
