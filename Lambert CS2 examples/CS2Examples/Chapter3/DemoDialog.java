import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import ioutil.*;

public class DemoDialog extends JDialog{
    
   //////////////////////////////////////////////////// Model

   private Dog dog;

   //////////////////////////////////////////////////// View

   // Create controls 
   private JLabel       lbName   = new JLabel("Name");
   private JTextField   tfName   = new JTextField("");
   private JLabel       lbAge    = new JLabel("Age");
   private IntegerField ifAge    = new IntegerField(0);
   private JButton      btOK     = new JButton("OK");
   private JButton      btCancel = new JButton("Cancel");

   public DemoDialog(Frame parent, Dog dog){

      // Do some initialization in the superclass JDialog 
      super(parent, true);                 

      // Place employee state information in the 
      // dialog's data controls
      this.dog = dog;
      tfName.setText(dog.getName());
      ifAge.setNumber(dog.getAge());

      // Create container and layout
      Container contentPane = getContentPane();
      EasyGridLayout layout = new EasyGridLayout();
      contentPane.setLayout (layout);

      // Set constraints 
      layout.setConstraints(lbName   ,1,1,1,1);
      layout.setConstraints(tfName   ,1,2,1,1);
      layout.setConstraints(lbAge    ,2,1,1,1);
      layout.setConstraints(ifAge    ,2,2,1,1);
      layout.setConstraints(btOK     ,3,1,1,1);
      layout.setConstraints(btCancel ,3,2,1,1);

      // Add controls to the container
      contentPane.add(lbName);
      contentPane.add(tfName);
      contentPane.add(lbAge);
      contentPane.add(ifAge);
      contentPane.add(btOK);
      contentPane.add(btCancel);

      // Specify listeners
      btOK.addActionListener(new OKButtonListener());
      btCancel.addActionListener(new CancelButtonListener());
      addWindowListener(new MyWindowAdapter());   

      // Set the title and make the dialog visible
      setTitle("Doggie Dialog");
      dlgCloseIndicator = "Cancel";     // The default is "Cancel"
      setSize(200, 150);  
   }

   //////////////////////////////////////////////////// Controller

   private String dlgCloseIndicator;   

   public String getDlgCloseIndicator(){
      return dlgCloseIndicator;
   }   

   // OK button listener
   private class OKButtonListener implements ActionListener{
      public void actionPerformed (ActionEvent event){
         dog.setName(tfName.getText());
         dog.setAge(ifAge.getNumber());
         dlgCloseIndicator = "OK";
         dispose();      
      }
   }

   // Cancel button listener
   private class CancelButtonListener implements ActionListener{
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