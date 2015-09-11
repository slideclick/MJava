import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import ioutil.*;

public class DemoList extends JFrame{
    
   //////////////////////////////////////////////////// main

   public static void main(String[] args){
      JFrame theFrame = new DemoList();
      theFrame.setSize(350, 250);
      theFrame.setVisible(true);         
   }
   
   //////////////////////////////////////////////////// Model

   // none

   //////////////////////////////////////////////////// View

   // Create controls
   private DefaultListModel lmNames = new DefaultListModel();
   private JList            ltNames = new JList(lmNames);

   private JTextField tfName    = new JTextField("");
   private JButton    btAdd     = new JButton("Add at End");
   private JButton    btInsert  = 
                      new JButton("Insert Before Selection");
   private JButton    btRemove  = new JButton("Remove Selection");
   private JButton    btReplace = new JButton("Replace Selection");
   private JButton    btClear   = new JButton("Clear List");    

   public DemoList(){

      // Set window title
      setTitle("List Demo");

      // Set the list selection model
      ltNames.setSelectionMode
        (ListSelectionModel.SINGLE_SELECTION);           

      // Disable all but the add button
      enableDisableButtons();

      // Create container and layout
      Container contentPane = getContentPane();
      EasyGridLayout layout = new EasyGridLayout();
      contentPane.setLayout (layout);

      // Set constraints
      JScrollPane spName = new JScrollPane(ltNames);
      layout.setConstraints(spName    ,1,1,1,6);
      layout.setConstraints(tfName    ,1,2,1,1);
      layout.setConstraints(btAdd     ,2,2,1,1);
      layout.setConstraints(btInsert  ,3,2,1,1);
      layout.setConstraints(btRemove  ,4,2,1,1);
      layout.setConstraints(btReplace ,5,2,1,1);
      layout.setConstraints(btClear   ,6,2,1,1);

      // Add controls to container
      contentPane.add(spName);
      contentPane.add(tfName);
      contentPane.add(btAdd);
      contentPane.add(btInsert);
      contentPane.add(btRemove);
      contentPane.add(btReplace);
      contentPane.add(btClear);

      // Specify listeners
      ltNames.addMouseListener(new ListMouseListener());
      btAdd.addActionListener(new AddListener());
      btInsert.addActionListener(new InsertListener());
      btRemove.addActionListener(new RemoveListener());
      btReplace.addActionListener(new ReplaceListener());
      btClear.addActionListener(new ClearListener());
      addWindowListener(new MyWindowAdapter());   
   }

   // Enable and disable buttons as appropriate
   private void enableDisableButtons(){
      if (lmNames.size() == 0){
         btInsert.setEnabled(false);
         btRemove.setEnabled(false);
         btReplace.setEnabled(false);
         btClear.setEnabled(false);
      }else{
         btClear.setEnabled(true);
      }
      if (ltNames.getSelectedIndex() == -1){
         btInsert.setEnabled(false);
         btRemove.setEnabled(false);
         btReplace.setEnabled(false);
      }else{
         btInsert.setEnabled(true);
         btRemove.setEnabled(true);
         btReplace.setEnabled(true);
      }
   }
    
   //////////////////////////////////////////////////// Controller
 
   private void messageBox(String msg){
      new MessageBox(this, msg, 300, 100);
   }

   // Determine if the new name is OK
   private boolean isNameOK(String name){
      if (name.equals("")){
         messageBox("Please enter a name.");
         return false;
      }else if (lmNames.contains(name)){
         messageBox("The name " + name +
                    " is already in the list.");
         return false;
      }else
         return true;
   }
      
   // List mouse listener
   private class ListMouseListener extends MouseAdapter{
      public void mouseClicked(MouseEvent e) {
         if (lmNames.isEmpty())
            return;
         int index = ltNames.getSelectedIndex();
         String name = (String)lmNames.get(index);
         if (e.getClickCount() == 2) 
            messageBox("You double clicked " + name + ".");
         else if (e.getClickCount() == 1){
            tfName.setText(name);
            enableDisableButtons();
         }
      }
   }

   // Add listener
   private class AddListener implements ActionListener{
      public void actionPerformed(ActionEvent e){
         String name = tfName.getText().trim();
         if (!isNameOK(name))
            return;
         lmNames.addElement(name);
         ltNames.setSelectedIndex(lmNames.size() - 1);
         enableDisableButtons();
      }
   }
   
   // Insert listener
   private class InsertListener implements ActionListener{
      public void actionPerformed(ActionEvent e){
         int index = ltNames.getSelectedIndex();
         String name = tfName.getText().trim();
         if (!isNameOK(name))
            return;
         lmNames.add(index, name);
         ltNames.setSelectedIndex(index);
      }
   }
   
   // Remove listener
   private class RemoveListener implements ActionListener{
      public void actionPerformed(ActionEvent e){
         int index = ltNames.getSelectedIndex();
         lmNames.remove(index);
         tfName.setText("");
         enableDisableButtons();
      }
   }
   
   // Replace listener
   private class ReplaceListener implements ActionListener{
      public void actionPerformed(ActionEvent e){
         int index = ltNames.getSelectedIndex();
         String name = tfName.getText().trim();
         if (!isNameOK(name))
            return;
         lmNames.set(index, name);
      }
   }
   
   // Clear listener
   private class ClearListener implements ActionListener{
      public void actionPerformed(ActionEvent e){
         lmNames.clear();
         tfName.setText("");
         enableDisableButtons();
      }
   }
     
   // Window listener
   private class MyWindowAdapter extends WindowAdapter{
      public void windowClosing (WindowEvent e){
         System.exit(0);
      }
   }
}