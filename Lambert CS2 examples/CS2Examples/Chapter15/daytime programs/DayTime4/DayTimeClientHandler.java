import java.net.*;
import java.io.*;
import java.util.*;

public class DayTimeClientHandler extends Thread{

   private DayTimeInterface serverOutput;
   Socket client;

   public DayTimeClientHandler(DayTimeInterface view, Socket clientSocket){
      serverOutput = view;
      client = clientSocket;
      start();
   }
      
   public void run (){
      try{
         PrintStream clientOutput = 
			            new PrintStream(client.getOutputStream(), 
								             true);
         BufferedReader clientInput = 
				            new BufferedReader(new InputStreamReader(
									                client.getInputStream()));
         clientOutput.println(new Date());
         String s = clientInput.readLine();
         serverOutput.println (s + "\n");
         client.close();
      }catch (Exception e){
         serverOutput.println ("Error:\n" + e.toString());
      }
   }

}
