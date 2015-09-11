// Token
// (c) 2000 Ken Lambert and Martin Osborne

public class Token {

    public  static final int UNKNOWN  = 0;        // unknown
    
    public  static final int INT      = 4;        // integer
            
    public  static final int MINUS    = 5;        // minus    operator
    public  static final int PLUS     = 6;        // plus     operator
    public  static final int MUL      = 7;        // multiply operator
    public  static final int DIV      = 8;        // divide   operator

    private static final int FIRST_OP = 5;        // first operator code
    
//=============================================================================

    private int type;
    private int value;

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
    
    public boolean isOperator()
    {
        return type >= FIRST_OP;
    }

    public String toString()
    {
        if (type == INT)
            return "" + value;
        else
            return "" + (char)value;
    }
    
    public int getType()
    {
       return type;
    }
    
    public int getValue()
    {
       return value;
    }

//=============================================================================

    private static int type (char ch)
    {
        int type;
        switch (ch){
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
}

