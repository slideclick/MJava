import java.net.*;
import java.io.*;
import java.util.*;

public class DayTimeServerDaemon extends Thread{

   private DayTimeInterface serverOutput;

   public DayTimeServerDaemon(DayTimeInterface dTI){
      serverOutput = dTI;
      start();
   }
      
   public void run (){
      try{
         serverOutput.println ("Start Server");
         InetAddress localHost = InetAddress.getLocalHost();
         serverOutput.println ("Host: " + localHost + "\n");
         ServerSocket listen = new ServerSocket (13);
         serverOutput.println ("Listening on port: " + 
			                      listen.getLocalPort() + "\n");
			while (true){
            Socket client = listen.accept();
            serverOutput.println ("Client: " + 
				                      client.toString() + "\n");
			   new DayTimeClientHandler (serverOutput, client);
         }
      }catch (Exception e){
         serverOutput.println ("Error:\n" + e.toString());
      }
   }

}