import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import ioutil.*;

public class ConcordanceInterface extends JFrame{

   //////////////////////////////////////////////////////// main

   public static void main (String[] args){
      JFrame theFrame = new ConcordanceInterface();
      theFrame.setSize (400, 400);
      theFrame.setVisible (true);
   }

   //////////////////////////////////////////////////////// Model

   private ConcordanceModel model  = new ConcordanceModel();
  
   //////////////////////////////////////////////////////// View

   // Create controls 
   private DefaultListModel  lmFiles  = new DefaultListModel();
   private JLabel            lbFiles  = new JLabel    ("Files");
   private JList             ltFiles  = new JList     (lmFiles);
   private JLabel            lbStats  = new JLabel    ("Statistics");
   private JTextArea         taStats  = new JTextArea ("");    
   private JButton           btAdd    = new JButton   ("Add");
   private JButton           btRemove = new JButton   ("Remove");
   private JButton           btStats  = new JButton   ("Statistics");

   public ConcordanceInterface(){
      
      // Set title
      setTitle ("Concordance System");

      // Create container and layout
      Container contentPane = getContentPane();
      EasyGridLayout layout = new EasyGridLayout();
      contentPane.setLayout (layout);
      
      // Set constraints
      JScrollPane spFiles = new JScrollPane(ltFiles);
      JScrollPane spStats = new JScrollPane(taStats);

      layout.setConstraints(lbFiles  , 1,1,1,1);
      layout.setConstraints(spFiles  , 2,1,1,1);
      layout.setConstraints(lbStats  , 1,2,1,1);
      layout.setConstraints(spStats  , 2,2,2,1);    
      layout.setConstraints(btAdd    , 3,1,1,1);
      layout.setConstraints(btRemove , 3,2,1,1);
      layout.setConstraints(btStats  , 3,3,1,1); 

      // Add controls to container
      contentPane.add(lbFiles);
      contentPane.add(spFiles);
      contentPane.add(lbStats);
      contentPane.add(spStats);    
      contentPane.add(btAdd);
      contentPane.add(btRemove);
      contentPane.add(btStats);

      // Specify listeners
      btAdd.addActionListener(new MyButtonListener());
      btRemove.addActionListener(new MyButtonListener());
      btStats.addActionListener(new MyButtonListener());
      ltFiles.addMouseListener(new ListMouseListener());
      addWindowListener(new MyWindowAdapter());   
   }

   //////////////////////////////////////////////////////// Controller

   private void messageBox(String str){
     new MessageBox(this, str, 350, 100);
   }

   private void addFile(){
      String searchStr = "";        
      int i;
      FileDialog fileDialog = new FileDialog (this, "Input file", 
                                              FileDialog.LOAD);
      fileDialog.setSize(450, 300);
      fileDialog.setVisible (true);
      String fileName = fileDialog.getFile();
      String dirName = fileDialog.getDirectory();
      searchStr = fileName + " in " + dirName;
      if (fileName != null && dirName != null){
         for (i = 0; i < lmFiles.size(); i++){
            if (searchStr.equals (lmFiles.get(i)))
               break;                                 
         }
         if (i >= lmFiles.size()){
            lmFiles.add (0, searchStr);
            model.addFile (dirName, fileName);
         }else
            messageBox ("This file name is already present");
      }
   }
    
   private void removeFile(){
      int index = ltFiles.getSelectedIndex();
      if (index == -1)
         messageBox ("You must select a file name first");
      else{
         model.removeFile (index);
         lmFiles.remove (index);
      }
   }
    
   private void computeStatistics(){
      int index = ltFiles.getSelectedIndex();
      if (index == -1)
         messageBox ("You must select a file name first");
      else{
         String str = model.computeStatistics(index);
         taStats.setText (str);
      } 
   }    

   // List mouse listener
   private class ListMouseListener extends MouseAdapter{
      public void mouseClicked(MouseEvent e) {
         if (e.getClickCount() == 2) 
            computeStatistics();
      }
   }

   // Button action listener
   private class MyButtonListener implements ActionListener{
      public void actionPerformed (ActionEvent event){
         Object buttonObj = event.getSource();
         if (buttonObj == btAdd)
            addFile();
         else if (buttonObj == btRemove)
            removeFile();
         else
            computeStatistics();
      }
   }

   // Window listener
   private class MyWindowAdapter extends WindowAdapter{
      public void windowClosing (WindowEvent e){
         System.exit(0);
      }
   }
}
