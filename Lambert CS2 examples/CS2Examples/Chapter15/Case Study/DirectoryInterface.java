import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import ioutil.*;

public class DirectoryInterface extends JFrame{

   //////////////////////////////////////////////////// main

   public static void main(String[] args){
      Frame frm = new DirectoryInterface();
      frm.setSize (500, 300);
      frm.setVisible(true);
   }

   //////////////////////////////////////////////////// Model

   private DirectoryModel model;

   //////////////////////////////////////////////////// View

   // Create controls
   private JLabel namesLabel    = new JLabel("Names");
   private JLabel statusLabel   = new JLabel("Status");
   private DefaultListModel lmNames = new DefaultListModel();
   private JList ltNames = new JList(lmNames);
   private JTextArea statusArea = new JTextArea("");
   
   public DirectoryInterface(){
      setTitle("Directory Server");
		
      // Create container and layout
      Container contentPane = getContentPane();
      EasyGridLayout layout = new EasyGridLayout();
      contentPane.setLayout (layout);

      // Set constraints
		layout.setConstraints(namesLabel, 1,1,1,1);
		layout.setConstraints(statusLabel, 1,3,1,1);
		JScrollPane spName = new JScrollPane(ltNames);
		layout.setConstraints(spName, 2,1,2,5);
		JScrollPane spStatus = new JScrollPane(statusArea);
		layout.setConstraints(spStatus, 2,3,4,5);
		
      // Add controls to container
      contentPane.add(namesLabel);
      contentPane.add(statusLabel);
      contentPane.add(spName);
      contentPane.add(spStatus);

      // Specify listeners
      addWindowListener(new MyWindowAdapter());   
		
      model = new DirectoryModel(this);
      new DirectoryServerDaemon(this, model);
   }
      
   public void append(String text){
      statusArea.append(text);
   }
   
   public void displayNames(Object[] names){
      lmNames.clear();
      for (int i = 0; i < names.length; i++)
         lmNames.add(i, (String)names[i]);
   }

   //////////////////////////////////////////////////// Controller
	
	// Window listener
   private class MyWindowAdapter extends WindowAdapter{
      public void windowClosing (WindowEvent e){
         System.exit(0);
      }
   }

}
