// Gets the IP address of any machine by name

import java.net.*;
import ioutil.KeyboardReader;

public class Resolver{
 
   public static void main(String[] args){
	   System.out.print("Name: ");
	   KeyboardReader reader = new KeyboardReader();
      try{
         String name = reader.readLine();
         InetAddress ipAddress = InetAddress.getByName(name);
         System.out.println("IP address:\n" + ipAddress);
      }
      catch (Exception e){
         System.out.println("Unknown host:\n" + e.toString());
      }
   }
}