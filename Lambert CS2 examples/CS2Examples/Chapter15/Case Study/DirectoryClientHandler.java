import java.net.*;
import java.io.*;
import java.util.*;

public class DirectoryClientHandler extends Thread{

   private DirectoryInterface serverOutput;
   private DirectoryModel model;
   private Socket client;

   public DirectoryClientHandler (DirectoryInterface view, 
                                  DirectoryModel model,
                                  Socket clientSocket){
      serverOutput = view;
      this.model = model;
      client = clientSocket;
      start();
   }
      
   public void run (){
      try{
         PrintStream clientOutput = 
			            new PrintStream(client.getOutputStream(), true);
         BufferedReader clientInput = 
			               new BufferedReader(new InputStreamReader(         
								                   client.getInputStream()));
         while (true){
            String command = clientInput.readLine();
            if (command == null)
               break;
            else if (command.equals("FIND")){
               String name = clientInput.readLine();
               if (name == null) break;
               String number = model.findEntry(name);
               if (number == null)
                  clientOutput.println("Sorry, " + name + 
						                     " not in directory");
               else{
                  clientOutput.println(number);
                  serverOutput.append("Accessed " + name + " " + 
						                    number + "\n");
               }
            }else if (command.equals("ADD")){
                String name = clientInput.readLine();
                if (name == null) break;
                String number = clientInput.readLine();
                if (name == null) break;
                model.addEntry(name, number);
                clientOutput.println("Added " + name + " " + number);
                serverOutput.append("Added " + name + " " + number + "\n");
            }else if (command.equals("EXIT")){
				    serverOutput.append("Client " + client + "\nexiting\n");
					 break;
				}		    
         }
         client.close();
      }
      catch (IOException e){
         serverOutput.append ("Error in i/o:\n" + e.toString() + "\n");
      }
   }

}
