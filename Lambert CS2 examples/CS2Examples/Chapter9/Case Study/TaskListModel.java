// TaskListModel
// (c) 2000 Ken Lambert and Martin Osborne

import java.util.*;

public class TaskListModel extends Object {

   // Instance variables ---------------------------------

   private List list;
   private int currentPosition;

   // Constructor-----------------------------------------------

   public TaskListModel(){
   // Choose one of the following list implementations
         list = new ArrayList();
         // list = new LinkedList();

      currentPosition = -1;
   }

   // Public methods-------------------------------------------

   public boolean hasCurrentPosition(){
      return currentPosition != -1;
   }

   public Task get(){
      if (! list.isEmpty() && hasCurrentPosition())
         return (Task) list.get(currentPosition);
      else
         return null;
   }

   public int size(){
      return list.size();                    
   }                                

   public String insertBefore (Task task){
      if (list.isEmpty() || hasCurrentPosition()){
         if (task.getName().equals(""))
            return "Enter a name before inserting";
         else{
            if (currentPosition == -1)
               currentPosition++;
            list.add (currentPosition, task);
            return null;
         }
      }else
         return "Establish a current position before inserting";
   }

   public String insertAfter (Task task){
      return "insertAfter";        // incomplete  
   } 

   public String replace(Task task){
      return "replace";            // incomplete

   } 
   public String remove() {
      return "remove";             // incomplete
   } 

   public String getFirst(){
      if (! list.isEmpty())
         currentPosition = 0;
      return null;
   }

   public String getPrevious(){
      if (currentPosition > 0 && hasCurrentPosition())
         currentPosition--;
      return null;
   }

   public String getNext() {
      if (currentPosition < list.size() - 1 && hasCurrentPosition())
         currentPosition++;
      return null;
   }

   public String getLast() {
      return "getLast";            // incomplete
   }  

   public String findFirst(Task task){
      currentPosition = -1;
      return findNext (task);
   }

   public String findNext(Task task) {
      return "findNext";           // incomplete
   }

}

