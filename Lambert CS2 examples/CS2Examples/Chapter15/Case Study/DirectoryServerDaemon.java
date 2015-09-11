import java.net.*;
import java.io.*;
import java.util.*;

public class DirectoryServerDaemon extends Thread{

   private DirectoryInterface output;
   private DirectoryModel model;

   public DirectoryServerDaemon(DirectoryInterface view,
                                DirectoryModel model){
      output = view;
      this.model = model;
      start();
   }
      
   public void run (){
      try{
         InetAddress localHost = InetAddress.getLocalHost();
         output.append("Host: " + localHost + "\n");
         ServerSocket listen = new ServerSocket(7777);
         output.append("Listening on port: " + listen.getLocalPort() + "\n");
         while (true){
            Socket client = listen.accept();
            output.append("Client connected:\n" + client + "\n");
            new DirectoryClientHandler(output, model, client);
         }
      }
      catch (Exception e){
         output.append("Error in opening socket:\n" + e.toString() + "\n");
      }
   }

}
