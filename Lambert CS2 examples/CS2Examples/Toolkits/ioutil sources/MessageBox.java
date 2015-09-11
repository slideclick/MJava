package ioutil;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class MessageBox extends JDialog {

   //////////////////////////////////////////////////////// View

   // Create controls 
   private JTextArea textArea = new JTextArea ("");  
   private JButton   okButton = new JButton ("OK");

   public MessageBox (JFrame parent, String message, int width, int height){
      super(parent, "Message", true);
      finishConstructor(message, width, height);
   }

   public MessageBox (JDialog parent, String message, int width, int height){
      super(parent, "Message", true);
      finishConstructor(message, width, height);
   }

   private void finishConstructor(String message, int width, int height){

      // Prepare the text area
      textArea.setText(message);
      textArea.setFont(new Font("Courier", Font.PLAIN, 12));
      textArea.setEditable(false);

      // Create container and layout
      Container contentPane = getContentPane();
      EasyGridLayout layout = new EasyGridLayout();
      contentPane.setLayout (layout);
      
      // Add controls to container
      JScrollPane scrollPane = new JScrollPane(textArea);
      layout.setConstraints(scrollPane ,1,1,1,1); 
      contentPane.add (scrollPane);

      layout.setConstraints(okButton ,2,1,1,1); 
      contentPane.add (okButton);

      // Specify listeners
      okButton.addActionListener(new OKButtonListener());
      addWindowListener(new MyWindowAdapter());   

      // Make the message box visible and select the OK button
      setSize(width, height);
      setVisible(true);
   }

   //////////////////////////////////////////////////////// Controller

   // OK button listener
   private class OKButtonListener implements ActionListener{
      public void actionPerformed (ActionEvent event){
         dispose();      
      }
   }

   // Window listener
   private class MyWindowAdapter extends WindowAdapter{
      public void windowClosing (WindowEvent e){
         dispose();
      }
   }
}

