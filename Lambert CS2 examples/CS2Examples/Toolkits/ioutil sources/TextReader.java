package ioutil;

import java.io.*;

abstract public class TextReader{

   // Instance variables .......................................

   protected BufferedReader    buffer;

   // Public methods ...........................................

   public char getChar(){
      return getCharacter();
   }

   public char readChar(){
      return getNonSpaceCharacter();
   }

   public double readDouble(){
      return Double.parseDouble(getWord());
   }
   
   public int readInt(){
      return Integer.parseInt(getWord());
   }
   
   public String readLine(){
      return getLine();
   }

   public String readWord(){
      return getWord();
   }

   // Private and protected methods ............................
  
   protected void markBuffer(){
      try {
         buffer.mark(100);
      }catch (IOException e){
         System.out.println(e);
         System.exit(0);
      }
   }

   protected void resetBuffer(){
      try {
         buffer.reset();
      }catch (IOException e){
         System.out.println(e);
         System.exit(0);
      }
   }

   private String getLine(){
      String line = "";  // The compiler insist that we initialize the variable
      try {
         line = buffer.readLine();
      }catch (IOException e){
         System.out.println(e);
         System.exit(0);
      }
      return line;
   }

   protected char getCharacter(){ 
      int ch = -1;   // The compiler insists that we initialize the variable.           
      try {
         ch = buffer.read();
      }catch (IOException e){
         System.out.println(e);
         System.exit(0);
      }
      return (char) ch;
   }

   protected char getNonSpaceCharacter(){
      char ch;
      do
         ch = getCharacter();
      while (isWhiteSpace(ch));  
      return ch;                    // if no non white space left, returns (char)-1, i.e.,
                                    // eof,  when reading from a file
   }

   abstract protected String getWord();

   protected boolean isWhiteSpace(char ch){
      return ch == ' ' || ch == '\t' || ch == '\n' || ch == '\r';
   }
}