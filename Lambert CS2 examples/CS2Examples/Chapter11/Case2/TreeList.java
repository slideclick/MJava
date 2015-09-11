// TreeList
// (c) 2000 Ken Lambert and Martin Osborne

import javax.swing.*;

public class TreeList extends Object{

   private DefaultListModel lmList;
   private JList ltList;
        // Item are stored in the list with multiple prefixes of "|  "
        // The root item has no prefix.
        // A child of the root has one prefix
        // A grandchild of the root has two prefixes
        // Etc...
   private int  maxLevel;
        // maxLevel = the maximum number of levels in the tree
        // For instance, root with children would have maxLevel = 2
    
   public TreeList (JList ltList, DefaultListModel lmList, int maxLevel){
      this.ltList = ltList;
      this.lmList = lmList;
      this.maxLevel = maxLevel;
   }
    
   public int getSelectedIndex(){
      return ltList.getSelectedIndex();
   }
    
   public void select (int pos){
      ltList.setSelectedIndex(pos);
   }
    
   public void add (String str, int pos, int level){
      lmList.add (pos, format (str, level));
   }
    
   public int[] getTreeIndex (int pos)
    // Gets the tree index of the item at the indicated position.
    // For instance, suppose the tree has a root and three levels below that,
    // then {0,0,0} indicates the root and {2,4,1} indicates the item reached
    // by navigating to the 2nd child of the root and then from there
    //               to this ones 4th child and then from there
    //               to this ones 1st child 
   {
      int i;
        
      // Declare and initialize treeIndex[]
      int treeIndex[] = new int [maxLevel - 1];
      for (i = 0; i < treeIndex.length - 1; i++)
         treeIndex[i] = 0;

      for (i = 1; i <= pos; i++){
         int level = getLevel (i) - 2;
         treeIndex[level]++;
         for (int j = level + 1; j < treeIndex.length; j++)
            treeIndex[j] = 0;
      }
      return treeIndex;   
        
   }
    
   public int getLevel (int pos)
    //The level associated with an item in the list equals
    //the number of '|' characters in the string  + 1
   {
      String str = (String)lmList.get (pos);
      int level = 1;
      int start = 0;
      int nextPos = str.indexOf ('|', start);
      while (nextPos != -1){
         level++;
         start = nextPos + 1;
         nextPos = str.indexOf ('|', start);
      }
      return level;  
   }

   public boolean isExpanded (int pos)
    // The following items are considered to be expanded
    //   a leaf
    //   an item followed by an item at a higher level in the tree
   {
      int level = getLevel (pos);
        
      if (level == maxLevel)                     // Leaf ==> expanded
         return true;
            
      if (pos + 1 == lmList.size())        // Not a leaf and last item in list
         return false;                          // ==> not expanded
            
      int levelOfNext = getLevel (pos + 1);
      if (levelOfNext > level)                   // Next item at higher level
         return true;                           // ==> expanded
      else
         return false;                          // not expanded
   }

   public void contract (int pos)
    //From the list remove all successors of the selected item until one
    //with the same or lesser level is encountered
   {
      int level = getLevel (pos);
      pos++;
      lmList.remove (pos);
      while (pos < lmList.size()){
         int nextLevel = getLevel (pos);
         if (nextLevel <= level)
            break;
         else
            lmList.remove (pos);
      }
   }
    
   public void expand (int pos, java.util.List listOfStrings){
      int level = getLevel (pos);
      pos++;
      for (int i = 0; i < listOfStrings.size(); i++){
         String str = (String)(listOfStrings.get(i));
         str = format (str, level + 1);
         lmList.add (pos, str);
         pos++;
      }
   }

   public void addFirstChild (String str, int pos){
      int level = getLevel (pos);
      lmList.add (pos + 1, format (str, level + 1));
   }

   public void addRightSibling (String str, int pos){
      int level = getLevel (pos);
      int insertionPoint = pos + 1;
      for (int i = insertionPoint; i < lmList.size(); i++){
         int nextLevel = getLevel (i);
          if (nextLevel > level)
             insertionPoint++;
          else
             break;
      }
      lmList.add (insertionPoint, format (str, level));
   }

   public void set (String str, int pos){
      int level = getLevel (pos);
      lmList.set (pos, format (str, level));
   }
    
   public void remove (int pos)
    //From the list remove the selected item and all successors until one
    //with the same or lesser level is encountered
   {
      int level = getLevel (pos);
      lmList.remove (pos);
      while (pos < lmList.size()){
         int nextLevel = getLevel (pos);
         if (nextLevel <= level)
            break;
         else
            lmList.remove (pos);
      }
   }

   private String format (String str, int level){
      for (int i = 1; i <= level - 1; i++)
         str = "|    " + str;
      return str;
   }  
}