import ioutil.*;
import java.io.*;

public class AddIntegersInAFile {

   public static void main(String[] args){
  
      // Ask the user for the file names
      KeyboardReader keyboard = new KeyboardReader();
      String inFileName = keyboard.readWord("Enter name of input file: ");
      String outFileName = keyboard.readWord("Enter name of output file: ");

      // Sum the integers in the input file
      ioutil.FileReader inFile = new ioutil.FileReader(inFileName);
      int sum = 0;
      int num = inFile.readInt();
      while (!inFile.iseof()){
         sum += num;
         num = inFile.readInt();
      }
      inFile.close();

      // Print the sum to the output file
      try{
         FileOutputStream 
            fileOutputStream = new FileOutputStream(outFileName);
         PrintWriter printWriter = new PrintWriter(fileOutputStream, true);
         printWriter.println("The sum of the integers is " + sum + ".");
         printWriter.close();
      }catch(Exception e){
         System.err.println("Input error -- " + e.toString());
      }
   }
}