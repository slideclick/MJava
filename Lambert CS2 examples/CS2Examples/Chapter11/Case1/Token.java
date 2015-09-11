// Token
// (c) 2000 Ken Lambert and Martin Osborne


public class Token extends Object{

    public static final int UNKNOWN  = 0;        // unknown
    public static final int EOE      = 1;        // end-of-expression
    public static final int L_PAR    = 2;        // left parenthesis
    public static final int R_PAR    = 3;        // righ parenthesis
    
    public static final int INT      = 4;        // integer

    public static final int FIRST_OP = 5;        // first    operand code
    public static final int MINUS    = 5;        // minus    operand
    public static final int PLUS     = 6;        // plus     operand
    public static final int MUL      = 7;        // multiply operand
    public static final int DIV      = 8;        // divide   operand

//=============================================================================

    public int type;
    public int value;

//=============================================================================

    public static boolean isOperator (char ch)
    {
        return (type (ch) >= FIRST_OP);
    }
    
    private static int type (char ch)
    {
        int type;
        switch (ch){
          case  ';':
            type = EOE;
            break;
          case '(':
            type = L_PAR;
            break;
          case ')':
            type = R_PAR;
            break;
          case '*':
            type = MUL;
            break;
          case '/':
            type = DIV;
            break;
          case '+':
            type = PLUS;
            break;
          case '-':
            type = MINUS;
            break;
          default:
            type = UNKNOWN;
        }
        return type;
    }
    
//=============================================================================

    public Token (int value)
    {
        type = INT;
        this.value = value;
    }

    public Token (char ch)
    {
        type = type (ch);
        value = (int) ch;
    }
    
//=============================================================================

    public  boolean isOperator()
    {
        return (type >= FIRST_OP);
    }
    
//=============================================================================

    public String toString()
    {
        if (type == INT)
            return "" + value;
        else
            return "" + (char)value;
    }

}

