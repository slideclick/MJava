import java.io.*;

abstract public class KeyboardReader{

   // Instance variable ........................................

   protected BufferedReader    buffer;

   // Constructor ..............................................

   public KeyboardReader(){
      InputStreamReader reader = new InputStreamReader(System.in);
      buffer = new BufferedReader(reader);
   }  
 
   // Public method ............................................

   public double readDouble(){
      return Double.parseDouble(getWord());
   }
   
   // Private  methods .........................................
  
   private char getCharacter(){ 
      int ch = -1;   // The compiler insists that we 
                     // initialize the variable.           
      try {
         ch = buffer.read();
      }catch (IOException e){
         System.out.println(e);
         System.exit(0);
      }
      return (char) ch;
   }

   private char getNonSpaceCharacter(){
      char ch;
      do
         ch = getCharacter();
      while (isWhiteSpace(ch));  
      return ch;                    
   }

   private String getWord(){
      String word = "";           
      char ch = getNonSpaceCharacter();

      do{
         word = word + ch;
         ch = getCharacter();
      }while (!isWhiteSpace(ch));

      return word;      
   }

   private boolean isWhiteSpace(char ch){
      return ch == ' ' || ch == '\t' || ch == '\n' || ch == '\r';
   }
}