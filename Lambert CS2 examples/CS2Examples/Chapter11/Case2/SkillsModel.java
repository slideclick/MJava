// SkillsModel
// (c) 2000 Ken Lambert and Martin Osborne

import lamborne.*;
import java.util.*;

public class SkillsModel extends Object{

   private Tree tree;

   public SkillsModel(){
      tree = new LinkedTree();
      tree.addRoot(new Item("ROOT", ""));
   }

   public Item get (int treeIndex[]){
      return (Item)(tree.get(treeIndex));
   }     
    
   public void addFirstChild (int treeIndex[], Item item){
      tree.addFirstChild (treeIndex, item);
   }
    
   public void addRightSibling (int treeIndex[], Item item){
      tree.addRightSibling (treeIndex, item);
   }
    
   public void set (int treeIndex[], Item item){
      tree.set (treeIndex, item);
   }
    
   public void remove (int treeIndex[]){
      tree.remove(treeIndex);
   }
    
   public List getNamesOfChildren (int treeIndex[]){
      TreeIterator treeIter = tree.treeIterator();
      treeIter.moveTo (treeIndex);
      List list = new LinkedList();
      Object obj = treeIter.getFirstChild();
      while (obj != null) {
         String name = ((Item)(obj)).name;
         list.add (name);
         obj = treeIter.getRightSibling();
      }
      return list;
   }
    
   public String getStringOfAllItems(){
      return tree.hierarchicalList();
   }
    
   public String getStringOfAllWithSkill (String targetSkill){
      Item department, employee, skill;
      TreeIterator treeIter = tree.treeIterator();
        
      String result = "People with skill " + targetSkill;
       
      treeIter.getRoot(); 
      department = (Item)(treeIter.getFirstChild());
      while (department != null){                   
         employee = (Item)(treeIter.getFirstChild());
         while (employee != null){
            skill = (Item)(treeIter.getFirstChild());
            while (skill != null){
               if (skill.name.equals (targetSkill)){
                  result += "\n  " + employee.name + " in " + department.name;
                  break;
               }else{
                  skill = (Item)(treeIter.getRightSibling());
               }
            }
            treeIter.getParent();
            employee = (Item)(treeIter.getRightSibling());
         }
         treeIter.getParent();
         department = (Item)(treeIter.getRightSibling());
      }          

      return result;
   }  
}