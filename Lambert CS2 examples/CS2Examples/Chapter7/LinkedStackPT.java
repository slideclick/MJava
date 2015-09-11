// LinkedStackPT
// (c) 1999 Ken Lambert and Martin Osborne

import java.io.Serializable;

public class LinkedStackPT implements StackPT, Serializable {

   private int count;           // Number of items in the stack
   private Node top;            // Top node in the stack
    
   public LinkedStackPT(){
      top = null;
      count = 0;
   }
    
   public boolean isEmpty(){
      return count == 0;
   }
    
   public boolean isFull(){  
      return false; 
   }
    
   public Object peek(){
      if (isEmpty())
         throw new IllegalStateException ("Stack is empty");
      return top.value;
   }

   public Object pop(){
      if (isEmpty())
         throw new IllegalStateException ("Stack is empty");
      count--;
      Object item = top.value;
      top = top.next;
      return item;
   }

   public void push(Object item){
      if (item == null)
         throw new IllegalArgumentException ("Item is null");
      count++;
      Node n = new Node (item, top);
      top = n;
   }    

   public int size(){
      return count;
   }

   private class Node implements Serializable {

      private Object value;    // Value stored in this node
      private Node   next;     // Reference to next node
    
      private Node(){
         value = null;
         next = null;
      }

      private Node(Object value, Node next){
         this.value = value;
         this.next =next;
      }
   }
}