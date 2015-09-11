import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class EmployeeDialogTester extends JFrame{

   //////////////////////////////////////////////////////// main

   public static void main (String[] args){
      JFrame theFrame = new EmployeeDialogTester();
      theFrame.setSize (250, 250);
      theFrame.setVisible (true);
   }

   //////////////////////////////////////////////////////// Model

   private Employee emp;

   //////////////////////////////////////////////////////// View

   // Create controls 
   private JTextArea taOutput = new JTextArea ("");
   private JButton   btModify = new JButton ("Modify");

   public EmployeeDialogTester(){

      // Set title
      setTitle("Employee Dialog Tester");
 
      // Initialize the employee object
      int [] hours = {8, 10, 6, 8, 9};
      emp = new Employee("Sue", 6.50, hours);

      // Initialize the taOutput
      taOutput.setText(emp.toString());
      taOutput.setFont(new Font("Courier", Font.PLAIN, 12));
      taOutput.setEditable(false);

      // Give the window a title 
      setTitle("Employee Dialog Tester");

      // Create container and layout
      Container contentPane = getContentPane();
      EasyGridLayout layout = new EasyGridLayout();
      contentPane.setLayout (layout);
      
      // Set constraints
      layout.setConstraints(taOutput   ,1,1,1,1); 
      layout.setConstraints(btModify   ,2,1,1,1);

      // Add controls to container
      contentPane.add (taOutput);
      contentPane.add (btModify);

      // Specify listeners
      btModify.addActionListener(new ModifyButtonListener());
      addWindowListener(new MyWindowAdapter());   
   }
   
   //////////////////////////////////////////////////////// Controller

   // Some button listener
   private class ModifyButtonListener implements ActionListener{
      public void actionPerformed (ActionEvent event){
         EmployeeDialog dlg = new EmployeeDialog(EmployeeDialogTester.this, emp);
         dlg.show();
         if (dlg.getDlgCloseIndicator().equals("OK")){
            taOutput.setText(emp.toString());
         }
      }
   }

   // Window listener
   private class MyWindowAdapter extends WindowAdapter{
      public void windowClosing (WindowEvent e){
         System.exit(0);
      }
   }
}
