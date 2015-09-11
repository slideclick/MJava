import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import ioutil.*;

public class DirectoryClient extends JFrame{

   //////////////////////////////////////////////////// main

   public static void main(String[] args){
      Frame frm = new DirectoryClient();
      frm.setSize (500, 250);
      frm.setVisible(true);
   } 
   
   //////////////////////////////////////////////////// Model

   // none

   //////////////////////////////////////////////////// View

   // Create controls
   private JLabel ipLabel = new JLabel("Host name or IP number");
   private JLabel nameLabel = new JLabel("Person's name");
   private JLabel numberLabel = new JLabel("Person's number");
   private JTextField ipField = new JTextField("");
   private JTextField nameField = new JTextField("");
   private JTextField numberField = new JTextField("");
   private JButton connectButton = new JButton("Connect");
   private JButton findButton = new JButton("Find");
   private JButton addButton  = new JButton("Add");
   private JTextArea statusArea = new JTextArea("");
   
   private BufferedReader serverInput; 
   private PrintStream serverOutput;
   
   public DirectoryClient(){
      setTitle("Directory Client");
		
      // Create container and layout
      Container contentPane = getContentPane();
      EasyGridLayout layout = new EasyGridLayout();
      contentPane.setLayout (layout);

      // Set constraints
		layout.setConstraints(ipLabel, 1,1,1,1);
		layout.setConstraints(nameLabel, 2,1,1,1);
		layout.setConstraints(numberLabel, 3,1,1,1);
		layout.setConstraints(ipField, 1,2,1,1);
		layout.setConstraints(nameField, 2,2,1,1);
		layout.setConstraints(numberField, 3,2,1,1);
		layout.setConstraints(connectButton, 4,1,1,1);
		layout.setConstraints(findButton, 4,2,1,1);
		layout.setConstraints(addButton, 4,3,1,1);
      JScrollPane sDisplay = new JScrollPane(statusArea);
      layout.setConstraints(sDisplay, 5,1,5,2);
		
		// Add controls to container
      contentPane.add(ipLabel);
      contentPane.add(nameLabel);
      contentPane.add(numberLabel);
      contentPane.add(ipField);
      contentPane.add(nameField);      
		contentPane.add(numberField);
      contentPane.add(connectButton);
      contentPane.add(findButton);
      contentPane.add(addButton);
      contentPane.add(sDisplay);
		
      // Specify listeners
      connectButton.addActionListener(new ConnectButtonListener());
      findButton.addActionListener(new FindButtonListener());
      addButton.addActionListener(new AddButtonListener());
		addWindowListener(new MyWindowAdapter());
   }   

   //////////////////////////////////////////////////// Controller

   private class ConnectButtonListener implements ActionListener{
      public void actionPerformed(ActionEvent event){ 
         String name = ipField.getText();
         try{
            Socket socket = new Socket(name, 7777);
            serverInput = new BufferedReader(
				              new InputStreamReader(socket.getInputStream()));
            serverOutput = new PrintStream(socket.getOutputStream());
            statusArea.append("Connected to server:\n" + socket + "\n");
         }catch (Exception e){
            statusArea.append("Error connecting to server:\n" + 
				                  e.toString() + "\n");
         }
		}
	}
	
   private class AddButtonListener implements ActionListener{
      public void actionPerformed(ActionEvent event){ 
         if (serverInput == null) return;
         String name = nameField.getText();
         String number = numberField.getText();
         try{
            serverOutput.println("ADD");
            serverOutput.println(name);
            serverOutput.println(number);
            statusArea.append(serverInput.readLine() + 
				                  "\n");
         }catch(Exception e){
            statusArea.append("Error:\n" + 
				                  e.toString() + "\n");
         }
		}
	}
	
   private class FindButtonListener implements ActionListener{
      public void actionPerformed(ActionEvent event){ 
         if (serverInput == null) return;
         String name = nameField.getText();
         try{
            serverOutput.println("FIND");
            serverOutput.println(name);
            statusArea.append(serverInput.readLine() + 
				                  "\n");
         }catch(Exception e){
            statusArea.append("Error:\n" + e.toString() + 
				                  "\n");
         }
		}
	}
      
   private class MyWindowAdapter extends WindowAdapter{
      public void windowClosing (WindowEvent event){
         try{
            serverOutput.println("EXIT");
         }catch(Exception e){
            statusArea.append("Error:\n" + e.toString() + 
				                  "\n");
         }	
			System.exit(0);
      }
   }
   
}
