import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class <program name> extends JFrame{

   //////////////////////////////////////////////////////// main

   public static void main (String[] args){
      JFrame theFrame = new <program name>();
      theFrame.setSize (200, 125);
      theFrame.setVisible (true);
   }

//////////////////////////////////////////////////////// Model

   private <model class> <model object> = new <model class>();
   
   //////////////////////////////////////////////////////// View

   // Create controls 
   private JLabel     ... = new JLabel ("...");
   private JTextField ... = new JTextField ("...");  
   private JButton    ... = new JButton ("...");
   ...
   
   public <program name>(){

      // Set title
      setTitle("...");

      // Create container and layout
      Container contentPane = getContentPane();
      EasyGridLayout layout = new EasyGridLayout();
      contentPane.setLayout (layout);
      
      // Set constraints 
      layout.setConstraints(... ,1,1,1,1); 
      ...

      // Add controls to container
      contentPane.add (...);
      ...

      // Specify listeners
      <button>.addActionListener(new <button listener>());
      ...
      addWindowListener(new MyWindowAdapter());   
   }

   //////////////////////////////////////////////////////// Controller

   // Button action listener
   private class <button listener> implements ActionListener{
      public void actionPerformed (ActionEvent event){
         ... code that accesses the view and the model ...      
      }
   }

   // Window listener
   private class MyWindowAdapter extends WindowAdapter{
      public void windowClosing (WindowEvent e){
         System.exit(0);
      }
   }
}

