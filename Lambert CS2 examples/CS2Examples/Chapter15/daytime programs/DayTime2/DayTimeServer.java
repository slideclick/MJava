import java.net.*;
import java.io.*;
import java.util.*;

public class DayTimeServer{

   public static void main(String[] args){
      try{
         System.out.println ("Start Server");
         InetAddress localHost = InetAddress.getLocalHost();
         System.out.println ("Host: " + localHost + "\n");
         ServerSocket listen = new ServerSocket (13);
         System.out.println ("Listening on port: " + 
			                    listen.getLocalPort() + "\n");
			while (true){
            Socket client = listen.accept();
            System.out.println ("Client: " + 
				                    client.toString() + "\n");
            PrintStream clientOutput = 
			               new PrintStream(client.getOutputStream(), 
								                true);
            BufferedReader clientInput = 
				               new BufferedReader(new InputStreamReader(
									                   client.getInputStream()));
            clientOutput.println(new Date());
            String s = clientInput.readLine();
            System.out.println (s + "\n");
            client.close();
         }
      }catch (Exception e){
         System.out.println ("Error:\n" + e.toString());
      }
   }
}
