import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import ioutil.*;

public class SkillsInterface extends JFrame{

   //////////////////////////////////////////////////////// main

   public static void main (String[] args){
      JFrame theFrame = new SkillsInterface();
      theFrame.setSize (600, 350);
      theFrame.setVisible (true);
   }

   //////////////////////////////////////////////////////// Model

    private SkillsModel model;
    private TreeList treeList;

   //////////////////////////////////////////////////////// View

   // Create controls 
   private DefaultListModel lmList = new DefaultListModel();
   private JList      ltList            = new JList     (lmList);
   private JLabel     lbName            = new JLabel    ("Name");
   private JTextField tfName            = new JTextField("          " );
   private JLabel     lbDescription     = new JLabel    ("Description" );
   private JTextArea  taDescription     = new JTextArea ("");
   private JTextArea  taResults         = new JTextArea ("");
   private JButton    btAddFirstChild   = new JButton ("Add First Child");
   private JButton    btAddRightSibling = new JButton ("Add Right Sibling");
   private JButton    btSet             = new JButton ("Set");
   private JButton    btRemove          = new JButton ("Remove");
   private JButton    btListAll         = new JButton ("List All");
   private JButton    btListSkill       = new JButton ("List All With Skill");
    
   //Levels of item in the tree
   private static final int kRootLevel       = 1;
   private static final int kDepartmentLevel = 2;
   private static final int kEmployeeLevel   = 3;
   private static final int kSkillLevel      = 4;
    


   public SkillsInterface(){

      // Set title and initialize
      setTitle ("Skill Maintenance System");
      model = new SkillsModel();
      treeList = new TreeList (ltList, lmList, kSkillLevel);
      treeList.add ("ROOT", 0, kRootLevel);

      // Create container and layout
      Container contentPane = getContentPane();
      EasyGridLayout layout = new EasyGridLayout();
      contentPane.setLayout (layout);
      
      // Set constraints
      JScrollPane spList = new JScrollPane(ltList);
      JScrollPane spDescription = new JScrollPane(taDescription);
      JScrollPane spResults = new JScrollPane(taResults); 
      layout.setConstraints(spList           ,1,1,1,4);
      layout.setConstraints(lbName           ,1,2,1,1);
      layout.setConstraints(tfName           ,2,2,1,1);
      layout.setConstraints(lbDescription    ,3,2,1,1);
      layout.setConstraints(spDescription    ,4,2,1,1);
      layout.setConstraints(spResults        ,1,3,1,4);
      layout.setConstraints(btAddFirstChild  ,5,1,1,1);
      layout.setConstraints(btAddRightSibling,6,1,1,1);
      layout.setConstraints(btSet            ,5,2,1,1);
      layout.setConstraints(btRemove         ,6,2,1,1);
      layout.setConstraints(btListAll        ,5,3,1,1);
      layout.setConstraints(btListSkill      ,6,3,1,1);

      // Add controls to container
      contentPane.add(spList);
      contentPane.add(lbName);
      contentPane.add(tfName);
      contentPane.add(lbDescription);
      contentPane.add(spDescription);
      contentPane.add(spResults);
      contentPane.add(btAddFirstChild);
      contentPane.add(btAddRightSibling);
      contentPane.add(btSet);
      contentPane.add(btRemove);
      contentPane.add(btListAll);
      contentPane.add(btListSkill);

      // Specify listeners
      btAddFirstChild.addActionListener(new MyButtonListener());
      btAddRightSibling.addActionListener(new MyButtonListener());
      btSet.addActionListener(new MyButtonListener());
      btRemove.addActionListener(new MyButtonListener());
      btListAll.addActionListener(new MyButtonListener());
      btListSkill.addActionListener(new MyButtonListener());
      ltList.addMouseListener(new ListMouseListener());
      addWindowListener(new MyWindowAdapter());   
   }

   //////////////////////////////////////////////////////// Controller

   private void displayItem (Item item) {
      if (item == null){
         tfName.setText ("");
         taDescription.setText ("");
      }else{
         tfName.setText (item.name);
         taDescription.setText (item.description);
      } 
   } 

   private void addFirstChild(){
      int selection = treeList.getSelectedIndex();
      if (selection == -1){
         new MessageBox (this, "Select an item first", 350, 100);
         return;
      }
        
      int level = treeList.getLevel (selection);
      if (level == kSkillLevel){
         new MessageBox (this, "Skills cannot have children", 350, 100);
         return;
      }
        
      Item item = getUserInput();
      if (item == null) return;
        
      int treeIndex[] = treeList.getTreeIndex (selection);
      model.addFirstChild (treeIndex, item);
      treeList.addFirstChild (item.name, selection);
   }
    
   private void addRightSibling(){
      int selection = treeList.getSelectedIndex();
      if (selection == -1){
         new MessageBox (this, "You must first select an item in the list", 350, 100);
         return;
      }
        
      int level = treeList.getLevel (selection);
      if (level == kRootLevel){
         new MessageBox (this, "ROOT cannot have siblings", 350, 100);
         return;
      }
        
      Item item = getUserInput();
      if (item == null) return;
        
      int treeIndex[] = treeList.getTreeIndex (selection);
      model.addRightSibling (treeIndex, item);
      treeList.addRightSibling (item.name, selection);
   }
    
   private void modify(){
      int selection = treeList.getSelectedIndex();
      if (selection == -1){
         new MessageBox (this, "You must first select an item in the list", 350, 100);
         return;
      }
        
      int level = treeList.getLevel (selection);
      if (level == kRootLevel){
         new MessageBox (this, "Cannot modify ROOT", 350, 100);
         return;
      }
        
      Item item = getUserInput();
      if (item == null) return;
        
      int treeIndex[] = treeList.getTreeIndex (selection);
      model.set (treeIndex, item);
      treeList.set (item.name, selection);
      treeList.select (selection);
   }    
    
   private void remove(){
      int selection = treeList.getSelectedIndex();
      if (selection == -1){
         new MessageBox (this, "You must first select an item in the list", 350, 100);
         return;
      }
        
      int level = treeList.getLevel (selection);
      if (level == kRootLevel){
         new MessageBox (this, "Cannot remove ROOT", 350, 100);
         return;
      }
        
      int treeIndex[] = treeList.getTreeIndex (selection);
      model.remove (treeIndex);
      treeList.remove (selection);
      displayItem (null);
   }    

   private void listAll(){
      taResults.setText (model.getStringOfAllItems());
   }
    
   private void listSkill(){
      String skillName = tfName.getText().trim();
        
      if (skillName. equals ("")){
         new MessageBox (this, "Enter a skill in the name field", 350, 100);
         return;
      }    

      taResults.setText (model.getStringOfAllWithSkill (skillName));
   }

   private Item getUserInput(){
      String name = tfName.getText().trim();
      String description = taDescription.getText().trim();
      if (name.equals ("") || description.equals ("")){
         new MessageBox (this, "Name and description required", 350, 100);
         return null;
      }else
         return new Item (name, description);
   }  

   // Button action listener
   private class MyButtonListener implements ActionListener{
      public void actionPerformed (ActionEvent event){
         Object buttonObj = event.getSource();
         if      (buttonObj == btAddFirstChild)
            addFirstChild();
         else if (buttonObj == btAddRightSibling)
            addRightSibling();
         else if (buttonObj == btSet)
            modify();
         else if (buttonObj == btRemove)
            remove();
         else if (buttonObj == btListAll)
            listAll();
         else
            listSkill();    
      }
   }
      
   // List mouse listener
   private class ListMouseListener extends MouseAdapter{
      public void mouseClicked(MouseEvent e) {
         int selection = treeList.getSelectedIndex();
         int treeIndex[] = treeList.getTreeIndex(selection);

         if (e.getClickCount() == 2){ 
            if (treeList.getLevel (selection) == kSkillLevel)
               return;
            
            if (treeList.isExpanded (selection))
               treeList.contract (selection);
            else{
               java.util.List listOfNames = model.getNamesOfChildren (treeIndex);
               treeList.expand (selection, listOfNames);
            }
         }else if (e.getClickCount() == 1){
            Item item = model.get (treeIndex);
            displayItem (item);    
         }
      }
   }
  
/*  
    public void listDoubleClicked (List listObj, String itemClicked)
    {
        int selection = treeList.getSelectedIndex();
        int treeIndex[] = treeList.getTreeIndex(selection);

        if (treeList.getLevel (selection) == kSkillLevel)
            return;
            
        if (treeList.isExpanded (selection))
            treeList.contract (selection);
        else{
            java.util.List listOfNames = model.getNamesOfChildren (treeIndex);
            treeList.expand (selection, listOfNames);
        }
    }
*/
   
/*
    public void listItemSelected (List listObj)
    {
        int selection = treeList.getSelectedIndex();
        int treeIndex[] = treeList.getTreeIndex (selection);
        Item item = model.get (treeIndex);
        displayItem (item);    
    }
*/
   // Window listener
   private class MyWindowAdapter extends WindowAdapter{
      public void windowClosing (WindowEvent e){
         System.exit(0);
      }
   }      
}
