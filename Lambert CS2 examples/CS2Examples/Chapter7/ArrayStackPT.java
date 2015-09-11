// ArrayStackPT
// (c) 1999 Ken Lambert and Martin Osborne

// This implementation is based on a static array.  Thus, the stack can
// become full, and then no more items can be pushed.
// The user can specify the stack's capacity when it is created.

import java.io.Serializable;

public class ArrayStackPT implements StackPT, Serializable {

   public final static int DEFAULT_CAPACITY = 100;
   private Object stack[];     // The array that holds the stack
   private int top;            // Index of top item on the stack

   // Creates a stack with the default capacity
   public ArrayStackPT (){
      this(DEFAULT_CAPACITY);
   }

   // Creates a stack with a user-specified capacity
   public ArrayStackPT (int capacity){
      if (capacity < 1)
         throw new IllegalArgumentException ("Capacity must be > 0");
      stack = new Object[capacity];
      top = -1;
   }
    
   public boolean isEmpty(){
      return top == -1;
   }
    
   public boolean isFull(){
      return size() == stack.length;
   }
    
   public Object peek(){
      if (isEmpty())
         throw new IllegalStateException ("Stack is empty");
      return stack[top];
   }

   public Object pop(){
      if (isEmpty())
         throw new IllegalStateException ("Stack is empty");
      Object topItem = stack[top];
      stack[top] = null;
      top--;
      return topItem;
   }

   public void push(Object item){
      if (item == null)
         throw new IllegalArgumentException ("Item is null");
      if (isFull())
         throw new IllegalStateException ("Stack is full");
      top++; 
      stack[top] = item;
   }    
    
   public int size(){
      return top + 1;
   }
}