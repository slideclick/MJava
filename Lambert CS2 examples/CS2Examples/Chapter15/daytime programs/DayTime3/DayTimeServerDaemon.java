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
         }
      }catch (Exception e){
         serverOutput.println ("Error:\n" + e.toString());
      }
   }

}