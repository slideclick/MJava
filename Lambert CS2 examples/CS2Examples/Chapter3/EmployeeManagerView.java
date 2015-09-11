import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class EmployeeManagerView extends JFrame{
    
   //////////////////////////////////////////////////////// main

   public static void main(String[] args){
      JFrame theFrame = new EmployeeManagerView();
      theFrame.setSize(350, 250);
      theFrame.setVisible(true);         
   }
   
   //////////////////////////////////////////////////////// Model

   private EmployeeModel employeeModel;

   //////////////////////////////////////////////////////// View

   // Create controls
   private JLabel    lbList       = new JLabel("Employee List");
   private JLabel    lbOutput     = new JLabel("Empoyee Details");
   private JList     ltEmployee   = new JList(new DefaultListModel());
   private JTextArea taOutput     = new JTextArea("");
    
   private JMenu     muFile       = new JMenu("File");      
   private JMenuItem miFileNew    = new JMenuItem("New");
   
   private JMenu     muEdit       = new JMenu("Edit");       
   private JMenuItem miEditAdd    = new JMenuItem("Add");
   private JMenuItem miEditModify = new JMenuItem("Modify");

   // Create the list model
   private DefaultListModel ltEmployeeModel = (DefaultListModel)ltEmployee.getModel();

   public EmployeeManagerView(){

      // Initialize various things
      employeeModel = new EmployeeModel();
      setTitle("Employee Manager");
      ltEmployee.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      taOutput.setEditable(false);

      // Create container and layout
      Container contentPane = getContentPane();
      EasyGridLayout layout = new EasyGridLayout();
      contentPane.setLayout (layout);

      // Set constraints and add controls to container
      JScrollPane spEmployeeList = new JScrollPane(ltEmployee);
      JScrollPane spOutputArea = new JScrollPane(taOutput);
      layout.setConstraints(lbList         , 1,1,1,1);
      layout.setConstraints(lbOutput       , 1,2,1,1);
      layout.setConstraints(spEmployeeList , 2,1,1,1);
      layout.setConstraints(spOutputArea   , 2,2,1,1);
      contentPane.add(lbList);
      contentPane.add(lbOutput);
      contentPane.add(spEmployeeList);
      contentPane.add(spOutputArea);

      // Create the menu bar and add menus and menu items
      JMenuBar menuBar = new JMenuBar();
      setJMenuBar(menuBar);
      menuBar.add(muFile);
      menuBar.add(muEdit);
      muFile.add(miFileNew);
      muEdit.add(miEditAdd);
      muEdit.add(miEditModify);

      // Specify listeners
      miFileNew.addActionListener(new FileNewListener());
      miEditAdd.addActionListener(new EditAddListener());
      miEditModify.addActionListener(new EditModifyListener());
      ltEmployee.addMouseListener(new ListMouseListener());
      addWindowListener(new MyWindowAdapter());   
   }
    
   //////////////////////////////////////////////////////// Controller

   // Determine index of currently selected item in list
   private int selectedIndex(){
      return ltEmployee.getSelectedIndex();
   }

   // Display current employee
   private void displayCurrentEmployee(){
     if (selectedIndex() != -1){
         Employee emp = employeeModel.getEmployee(selectedIndex());
         taOutput.setText(emp.toString());
      }
      else
         taOutput.setText("");
   }

   // Modify an employee
   private void modifyEmployee(){
      if (selectedIndex() == -1){
         new MessageBox(EmployeeManagerView.this, 
                        "Must select an employee's name first", 
                        300, 200);
         return;
      }
      String selectedName = (String)ltEmployee.getSelectedValue();
      Employee emp = employeeModel.getEmployee(selectedIndex());
      EmployeeDialog dlg = new EmployeeDialog(this, emp);
      dlg.show();
      if (dlg.getDlgCloseIndicator().equals("OK"))
         if (! selectedName.equals(emp.getName())){
           int index = selectedIndex();
            ltEmployeeModel.set(index, emp.getName());
            ltEmployee.setSelectedIndex(index);
         } 
   }   
 
   // File-new listener
   private class FileNewListener implements ActionListener{
      public void actionPerformed(ActionEvent e){
         ltEmployeeModel.clear();
         employeeModel = new EmployeeModel();
         displayCurrentEmployee();
      }
   }

   // Edit-add listener
   private class EditAddListener implements ActionListener{
      public void actionPerformed(ActionEvent e){
         // Create a new employee
         Employee emp = new Employee();

         // Allow the user to fill in the data
         EmployeeDialog dlg = new EmployeeDialog(EmployeeManagerView.this, emp);
         dlg.show();

         // If user did not cancel the dialog
         if (dlg.getDlgCloseIndicator().equals("OK")){

            // Attempt to add the employee to the employeeModel's list
            String message = employeeModel.addEmployee(emp);

            // If there was no space in the list, display a message
            if (message != null){
               new MessageBox(EmployeeManagerView.this, 
                              message , 300, 200);
               return;
            }

            // The employee was added so update the view
            ltEmployeeModel.addElement(emp.getName());
            int index = ltEmployeeModel.size() - 1;
            ltEmployee.setSelectedIndex(index);
            displayCurrentEmployee();
         }
      }
   }

   // Edit-modify listener
   private class EditModifyListener implements ActionListener{
      public void actionPerformed(ActionEvent e){
         modifyEmployee();
         displayCurrentEmployee();
      }
   }
   
   // List mouse listener
   private class ListMouseListener extends MouseAdapter{
      public void mouseClicked(MouseEvent e) {
         if (e.getClickCount() == 2) {
           modifyEmployee();
         }else if (e.getClickCount() == 1){
           displayCurrentEmployee();
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
