package ioutil;

import java.io.*;

public class FileReader extends TextReader {

   // Instance variable

   private boolean eof;

   // Constructor ..............................................

   public FileReader(String fileName){
      eof = false;
      try {
         FileInputStream stream = new FileInputStream(fileName);
         InputStreamReader reader = new InputStreamReader(stream);
         buffer = new BufferedReader(reader);
      }catch (IOException e){
         System.out.println(e);
         System.exit(0);
      }
   }

   // Public methods ...........................................

   public void close(){
      try {
         buffer.close();
      }catch (IOException e){
         System.out.println(e);
         System.exit(0);
      }
   }

   public boolean iseof(){
      return eof;
   }

   // Private and protected methods ............................
  
   protected char getCharacter(){ 
      char ch = super.getCharacter();
      if (ch == (char)-1)
         eof = true;
      return ch;
   }


   protected String getWord(){
      char ch;

      ch = getNonSpaceCharacter();

      if (eof) 
         return "0";             // There is no word, so set to "0" so that
                                 // readInt and readDouble don't blow up

      String word = "";           // There is a word
      do{
         word = word + ch;
         markBuffer();
         ch = getCharacter();
      }while (!isWhiteSpace(ch) && !eof);
      resetBuffer();               // Put back whitespace

      eof = false;

      return word;      
   }
}