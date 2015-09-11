import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class EmployeeDialog extends JDialog{
    
   //////////////////////////////////////////////////////// Model

   private Employee emp;
   // The dialog is passed an employee object and must refer to it
   // at several locations thereafter.

   //////////////////////////////////////////////////////// View

   // Create controls 
   private JLabel       lbName    = new JLabel("Name");
   private JTextField   tfName    = new JTextField("");
   private JLabel       lbPayRate = new JLabel("Pay Rate");
   private DoubleField  dfPayRate = new DoubleField(0);
   private JLabel       lbDays    = new JLabel("Daily hours");
   private IntegerField ifDay1    = new IntegerField(0);
   private IntegerField ifDay2    = new IntegerField(0);
   private IntegerField ifDay3    = new IntegerField(0);
   private IntegerField ifDay4    = new IntegerField(0);
   private IntegerField ifDay5    = new IntegerField(0);
   private JButton      btOK      = new JButton("OK");
   private JButton      btCancel  = new JButton("Cancel");

   public EmployeeDialog(Frame parent, Employee emp){
      super(parent, true);                            // ** REQUIRED **

      // Place employee state information in the dialog's data controls
      this.emp = emp;
      tfName.setText(emp.getName());
      dfPayRate.setNumber(emp.getPayRate());
      ifDay1.setNumber(emp.getHours(1));
      ifDay2.setNumber(emp.getHours(2));
      ifDay3.setNumber(emp.getHours(3));
      ifDay4.setNumber(emp.getHours(4));
      ifDay5.setNumber(emp.getHours(5));

      // Create container and layout
      Container contentPane = getContentPane();
      EasyGridLayout layout = new EasyGridLayout();
      contentPane.setLayout (layout);

      // Set constraints 
      layout.setConstraints(lbName     ,1,1,1,1);
      layout.setConstraints(tfName     ,1,2,1,1);
      layout.setConstraints(lbPayRate  ,2,1,1,1);
      layout.setConstraints(dfPayRate  ,2,2,1,1);
      layout.setConstraints(lbDays     ,3,1,1,1);
      layout.setConstraints(ifDay1     ,3,2,1,1);
      layout.setConstraints(ifDay2     ,3,3,1,1);
      layout.setConstraints(ifDay3     ,3,4,1,1);
      layout.setConstraints(ifDay4     ,3,5,1,1);
      layout.setConstraints(ifDay5     ,3,6,1,1);    
      layout.setConstraints(btOK       ,4,1,2,1);
      layout.setConstraints(btCancel   ,4,3,4,1);

      // Add controls to the container
      contentPane.add(lbName);
      contentPane.add(tfName);
      contentPane.add(lbPayRate);
      contentPane.add(dfPayRate);
      contentPane.add(lbDays);
      contentPane.add(ifDay1);
      contentPane.add(ifDay2);
      contentPane.add(ifDay3);
      contentPane.add(ifDay4);
      contentPane.add(ifDay5);
      contentPane.add(btOK);
      contentPane.add(btCancel);

      // Specify listeners
      btOK.addActionListener(new OKButtonListener());
      btCancel.addActionListener(new CancelButtonListener());
      addWindowListener(new MyWindowAdapter());   

      // Make the dialog visible
      setTitle("Employee Dialog");
      dlgCloseIndicator = "Cancel";            // Default is "Cancel"
      setSize(300, 200);                             
   }

   //////////////////////////////////////////////////////// Controller

   private String dlgCloseIndicator;   

   public String getDlgCloseIndicator(){
      return dlgCloseIndicator;
   }   

   // OK button listener
   private class OKButtonListener implements ActionListener{
      public void actionPerformed (ActionEvent event){
         emp.setName(tfName.getText());
         emp.setPayRate(dfPayRate.getNumber());
         emp.setHours(1, ifDay1.getNumber());
         emp.setHours(2, ifDay2.getNumber());
         emp.setHours(3, ifDay3.getNumber());
         emp.setHours(4, ifDay4.getNumber());
         emp.setHours(5, ifDay5.getNumber());
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