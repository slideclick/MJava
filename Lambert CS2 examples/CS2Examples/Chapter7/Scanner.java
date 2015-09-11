// Scanner
// (c) 2000 Ken Lambert and Martin Osborne
 
import java.util.*;

public class Scanner implements Iterator{

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
        currentToken = null;
    }
    
    Scanner (String str)
    {
        if (str == null || str.length() == 0)
            currentToken = null;
        else{   
            expressionString = str;
            getFirstToken();
        }
    }    
    
//=============================================================================

    public boolean hasNext()
    {
        return currentToken != null;
    }

    public Object next()
    {
        if (!hasNext())
           throw new NoSuchElementException("There are no more elements");            
        Token temp = currentToken;
        getNextToken();
        return temp;
    }

    public void remove()
    {
        throw new UnsupportedOperationException("Remove not allowed");
    }
    
//=============================================================================

    private void getFirstToken()
    {
        index = 0;
        currentChar = expressionString.charAt(0);
        getNextToken();
    }
    
    private void getNextToken()
    {
        skipWhiteSpace();
        if (Character.isDigit (currentChar))
            currentToken = new Token (getInteger());
        else {
            if (currentChar == EOE)
                currentToken = null;
            else{
                currentToken = new Token (currentChar);
                nextChar();
            }
        }
    }
    
    private void nextChar()
    {
        if (index >= expressionString.length() - 1){
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
