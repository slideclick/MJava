import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import ioutil.*;

public class TaskListView extends JFrame{

   //////////////////////////////////////////////////////// main

   public static void main (String[] args){
      JFrame theFrame = new TaskListView();
      theFrame.setSize (400, 300);               
      theFrame.setVisible (true);
   }

   //////////////////////////////////////////////////////// Model

   private TaskListModel model = new TaskListModel(); 
   private String oldName = "";
   private String oldDescription = "";
   
   //////////////////////////////////////////////////////// View

   // Create controls 
   private JButton    btFindFirst    = new JButton("Find First");
   private JButton    btFindNext     = new JButton("Find Next ");
   private JButton    btInsertBefore = new JButton("Insert Before");
   private JButton    btInsertAfter  = new JButton("Insert After");
   private JButton    brReplace      = new JButton("Replace");
   private JButton    btRemove       = new JButton("Remove");
   private JButton    btRestore      = new JButton("Restore");
   
   private JButton    btFirst        = new JButton("<<");
   private JButton    btPrevious     = new JButton("<");
   private JButton    btNext         = new JButton(">");
   private JButton    btLast         = new JButton(">>");

   private JLabel     lbName         = new JLabel("Name");
   private JLabel     lbDescription  = new JLabel("Description");
   private JLabel     lbCount        = new JLabel("Count 0");
   
   private JTextField tfname         = new JTextField(""); 
   private JTextArea  taDescription  = new JTextArea("");
   
   public TaskListView(){

      // Set title
      setTitle ("Task List");

      // Create container and layout
      Container contentPane = getContentPane();
      EasyGridLayout layout = new EasyGridLayout();
      contentPane.setLayout (layout);
      
      // Set constraints 
      JScrollPane spDescription = new JScrollPane(taDescription);
      layout.setConstraints(btFindFirst    ,6,2,1,1);
      layout.setConstraints(btFindNext     ,6,3,1,1);
      layout.setConstraints(btInsertBefore ,1,4,1,1);
      layout.setConstraints(btInsertAfter  ,2,4,1,1);
      layout.setConstraints(brReplace      ,3,4,1,1);
      layout.setConstraints(btRemove       ,4,4,1,1);
      layout.setConstraints(btRestore      ,5,4,1,1);
   
      layout.setConstraints(btFirst        ,7,1,1,1);
      layout.setConstraints(btPrevious     ,7,2,1,1);
      layout.setConstraints(btNext         ,7,3,1,1);
      layout.setConstraints(btLast         ,7,4,1,1);

      layout.setConstraints(lbName         ,1,1,1,1);
      layout.setConstraints(lbDescription  ,2,1,1,1);
      layout.setConstraints(lbCount        ,5,1,1,1);
   
      layout.setConstraints(tfname         ,1,2,2,1); 
      layout.setConstraints(spDescription  ,2,2,2,4);

      // Add controls to container
      contentPane.add(btFindFirst);
      contentPane.add(btFindNext);
      contentPane.add(btInsertBefore);
      contentPane.add(btInsertAfter);
      contentPane.add(brReplace);
      contentPane.add(btRemove);
      contentPane.add(btRestore);
   
      contentPane.add(btFirst);
      contentPane.add(btPrevious);
      contentPane.add(btNext);
      contentPane.add(btLast);

      contentPane.add(lbName);
      contentPane.add(lbDescription);
      contentPane.add(lbCount);
   
      contentPane.add(tfname); 
      contentPane.add(spDescription);


     // Specify listeners
      btFindFirst.addActionListener(new MyButtonListener());
      btFindNext.addActionListener(new MyButtonListener());
      btInsertBefore.addActionListener(new MyButtonListener());
      btInsertAfter.addActionListener(new MyButtonListener());
      brReplace.addActionListener(new MyButtonListener());
      btRemove.addActionListener(new MyButtonListener());
      btRestore.addActionListener(new MyButtonListener());
      btFirst.addActionListener(new MyButtonListener());
      btPrevious.addActionListener(new MyButtonListener());
      btNext.addActionListener(new MyButtonListener());
      btLast.addActionListener(new MyButtonListener());

      addWindowListener(new MyWindowAdapter());   
   }

   //////////////////////////////////////////////////////// Controller

   private Task getDataOnScreen(){
      String name = tfname.getText().trim();
      String description = taDescription.getText();
      
      Task task = new Task (name, description);
      return task;
   }
   
   private boolean unprocessedDataOnScreen(){
      if (oldName. equals (tfname.getText().trim()) &&
          oldDescription.equals (taDescription.getText()))
         return false;
      else{
         new MessageBox (this, "Unprocessed data on screen!", 
			                250, 100);
         return true;
      }                           
   }
   
   private boolean unprocessedDescriptionOnScreen(){
      if (oldDescription.equals (taDescription.getText()))
         return false;
      else{
         new MessageBox (this, "Unprocessed description on screen!", 
			                250, 100);
         return true;
      }                           
   }
   
   private void displayResultsOf (String str){
      if (str != null)
         new MessageBox (this, str, 250, 100);
      else{
         if (!model.hasCurrentPosition()){
            tfname.setText ("");
            taDescription.setText ("");
         }else{
            Task task = model.get();
            tfname.setText (task.getName());
            taDescription.setText (task.getDescription());
        }
        oldName = tfname.getText();
        oldDescription = taDescription.getText();
        lbCount.setText ("Count " + model.size() + "     ");
     }
   }

   // Button action listener
   private class MyButtonListener implements ActionListener{
      public void actionPerformed (ActionEvent event){
         Object buttonObj = event.getSource();
         if      (buttonObj == btInsertBefore)  
            displayResultsOf (model.insertBefore (getDataOnScreen()));
         
         else if (buttonObj == btInsertAfter)   
            displayResultsOf (model.insertAfter (getDataOnScreen()));
         
         else if (buttonObj == brReplace)       
            displayResultsOf (model.replace (getDataOnScreen()));
         
         else if (buttonObj == btRemove){        
            if (unprocessedDataOnScreen()) return;       
            displayResultsOf (model.remove());
         
         }else if (buttonObj == btRestore){
            tfname.setText (oldName);
            taDescription.setText (oldDescription);
         
         }else if (buttonObj == btFirst){
            if (unprocessedDataOnScreen()) return;       
            displayResultsOf (model.getFirst());
         
         }else if (buttonObj == btPrevious){      
            if (unprocessedDataOnScreen()) return;       
            displayResultsOf (model.getPrevious());
         
         }else if (buttonObj == btNext){          
            if (unprocessedDataOnScreen()) return;       
            displayResultsOf (model.getNext());
         
         }else if (buttonObj == btLast){          
            if (unprocessedDataOnScreen()) return;       
            displayResultsOf (model.getLast());
         
         }else if (buttonObj == btFindFirst){    
            if (unprocessedDescriptionOnScreen()) return; 
            displayResultsOf (model.findFirst (getDataOnScreen()));
         
         }else if (buttonObj == btFindNext){
            if (unprocessedDescriptionOnScreen()) return;       
            displayResultsOf (model.findNext (getDataOnScreen()));
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