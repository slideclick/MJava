package ioutil;

import java.io.*;

public class KeyboardReader extends TextReader {

   // Constructor ..............................................

   public KeyboardReader(){
      InputStreamReader reader = new InputStreamReader(System.in);
      buffer = new BufferedReader(reader);
   }   

   // Public methods ...........................................

   public char getChar(String prompt){
      System.out.print(prompt);
      return getChar();
   }

   public char readChar(String prompt){
      System.out.print(prompt);
      return readChar();
   }      

   public double readDouble(String prompt){
      System.out.print(prompt);
      return readDouble();
   }
   
   public int readInt(String prompt){
      System.out.print(prompt);
      return readInt();
   }
   
   public String readLine(String prompt){
      System.out.print(prompt);
      return readLine();
   }

   public String readWord(String prompt){
      System.out.println(prompt);
      return readWord();
   }

   // Private and protected methods ............................

   protected String getWord(){
      String word = "";           
      char ch = getNonSpaceCharacter();

      do{
         word = word + ch;
         markBuffer();
         ch = getCharacter();
      }while (!isWhiteSpace(ch));
      resetBuffer();

      return word;      
   }
}