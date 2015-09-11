import java.net.*;
import java.io.*;
import ioutil.KeyboardReader;

public class DayTimeClient{
    
   public static void main (String[] args){
      KeyboardReader reader = new KeyboardReader();
      System.out.print ("Host name or IP number: ");
      String name = reader.readLine();
      try{
         Socket socket = new Socket (name, 13);
         InputStream is = socket.getInputStream();
         BufferedReader serverInput = new BufferedReader(
                                      new InputStreamReader(is));
         PrintStream serverOutput = 
                     new PrintStream (socket.getOutputStream());
         String time = serverInput.readLine();
         System.out.println ("The time is:\n" + time);
         serverOutput.println ("Thanks!");                              
         socket.close();
      }catch (Exception e){
         System.out.println ("Error:\n" + e.toString());
      }
   }
}
