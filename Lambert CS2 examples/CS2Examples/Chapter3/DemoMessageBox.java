import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import ioutil.*;

public class DemoMessageBox extends JFrame {

   //////////////////////////////////////////////////// main

   public static void main (String[] args){
      JFrame theFrame = new DemoMessageBox();
      theFrame.setSize (200, 125);
      theFrame.setVisible (true);
   }

   //////////////////////////////////////////////////// Model

   // none

   //////////////////////////////////////////////////// View

   // Create controls 
   private JButton btPressMe = new JButton ("Save Me");
   
   public DemoMessageBox(){

      // Set title
      setTitle("MessageBox Demo");

      // Create container and layout
      Container contentPane = getContentPane();
      EasyGridLayout layout = new EasyGridLayout();
      contentPane.setLayout (layout);
      
      // Add controls to container
      layout.setConstraints(btPressMe ,1,1,1,1); 
      contentPane.add (btPressMe);

      // Specify listeners
      btPressMe.addActionListener(new SaveMeButtonListener());
      addWindowListener(new MyWindowAdapter()); 
   }

   //////////////////////////////////////////////////// Controller

   // Fahrenheit button listener
   private class SaveMeButtonListener implements ActionListener{
      public void actionPerformed (ActionEvent event){
         new MessageBox(DemoMessageBox.this, 
                        "We are the Borg.\n" +
                        "Resistance is futile.\n" +
                        "You will be assimilated!\n"
                        , 300, 200);
      }
   }

   // Window listener
   private class MyWindowAdapter extends WindowAdapter{
      public void windowClosing (WindowEvent e){
         System.exit(0);
      }
   }
}