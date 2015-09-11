import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import ioutil.*;

public class DemoMenusAndTextAreas extends JFrame{
    
   //////////////////////////////////////////////////////// main

   public static void main(String[] args){
      JFrame theFrame = new DemoMenusAndTextAreas();
      theFrame.setSize(350, 250);
      theFrame.setVisible(true);         
   }
   
   //////////////////////////////////////////////////////// Model

   // none

   //////////////////////////////////////////////////////// View

   // Create controls
   private JTextArea taDisplay     = new JTextArea("Greetings!\n");
    
   private JMenu     muFile        = new JMenu("File");      
   private JMenuItem miFileNew     = new JMenuItem("New");
   
   private JMenu     muEdit        = new JMenu("Edit");       
   private JMenuItem miEditAppend  = new JMenuItem("Append");
   private JMenuItem miEditReplace = new JMenuItem("Replace");

   public DemoMenusAndTextAreas(){

      // Set title
      setTitle("Menus and TextArea Demo");

      // Create container and layout
      Container contentPane = getContentPane();
      EasyGridLayout layout = new EasyGridLayout();
      contentPane.setLayout (layout);

      // Set constraints and add controls to container
      JScrollPane spDisplay = new JScrollPane(taDisplay);
      layout.setConstraints(spDisplay  , 1,1,1,1);
      contentPane.add(spDisplay);

      // Create the menu bar and add menus and menu items
      JMenuBar menuBar = new JMenuBar();
      setJMenuBar(menuBar);
      menuBar.add(muFile);
      menuBar.add(muEdit);
      muFile.add(miFileNew);
      muEdit.add(miEditAppend);
      muEdit.add(miEditReplace);

      // Specify listeners
      miFileNew.addActionListener(new FileNewListener());
      miEditAppend.addActionListener(new EditAppendListener());
      miEditReplace.addActionListener(new EditReplaceListener());
      addWindowListener(new MyWindowAdapter());   
   }
    
   //////////////////////////////////////////////////////// Controller

   // File-new listener
   private class FileNewListener implements ActionListener{
      public void actionPerformed(ActionEvent e){
         taDisplay.setText("");
      }
   }

   // Edit-append listener
   private class EditAppendListener implements ActionListener{
      public void actionPerformed(ActionEvent e){
         taDisplay.append("Appending one more line.\n");
      }
   }

   // Edit-modify listener
   private class EditReplaceListener implements ActionListener{
      public void actionPerformed(ActionEvent e){
         taDisplay.setText("Setting a new line of text.\n");
      }
   }
   
   // Window listener
   private class MyWindowAdapter extends WindowAdapter{
      public void windowClosing (WindowEvent e){
         System.exit(0);
      }
   }
}