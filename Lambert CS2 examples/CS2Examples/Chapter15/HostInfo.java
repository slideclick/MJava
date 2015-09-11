// Displays IP address of current host

import java.net.*;

public class HostInfo{

   public static void main(String[] args){
      try{
         InetAddress ipAddress = InetAddress.getLocalHost();
         System.out.println("IP address:\n" + ipAddress);
      }
      catch(UnknownHostException e){
         System.out.println("Unknown host:\n" + e.toString());
      }
   }
}