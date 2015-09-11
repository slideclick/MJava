// Scanner
// (c) 2000 Ken Lambert and Martin Osborne


public class Scanner extends Object{

    private static final char EOL = '\n';       // end-of-line
    private static final char EOE = ';';        // end-of-expression
    private static final char TAB = '\t';       // tab

//=============================================================================

    private String expressionString;
    private int    index;
    private char   currentChar;
    private Token  currentToken;

//=============================================================================

    Scanner ()
    {
         throw new RuntimeException 
        ("Trying to instantiate a scanner without an expression string");
    }
    
    Scanner (String str)
    {
        if (str == null || str.length() == 0)
            throw new RuntimeException 
            ("Trying to scan a null or empty expression string");
            
        expressionString = str;
    }
    
//=============================================================================

    public String stringUpToCurrentToken()
    {
        return expressionString.substring (0, index);
    }
    
//=============================================================================

    public Token get()
    {
        return currentToken;
    }

//=============================================================================

    public void first()
    {
        index = 0;
        currentChar = expressionString.charAt(0);
        next();
    }
    
//=============================================================================

    public void next()
    {
        skipWhiteSpace();
        if (Character.isDigit (currentChar))
            currentToken = new Token (getInteger());
        else {
            currentToken = new Token (currentChar);
            nextChar();
        }
    }
    
    private void nextChar()
    {
        if (index >= expressionString.length() - 1){
            index = expressionString.length();
            currentChar = EOE;
        }else{
            index++;
            currentChar = expressionString.charAt (index);
        }
    } 
    
    private void skipWhiteSpace()
    {
        while (currentChar == ' ' || currentChar == TAB || currentChar == EOL)
            nextChar();
    }
    
    private int getInteger()
    {
        int num = 0;
        do{
            num = num * 10 + Character.digit (currentChar, 10);
            nextChar();
        } while (Character.isDigit (currentChar));
        return num;
    }
               
    
}     
