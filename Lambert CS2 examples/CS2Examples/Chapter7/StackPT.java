// StackPT
// (c) 1999 Ken Lambert and Martin Osborne

public interface StackPT {

   public boolean isEmpty();
    
   public boolean isFull();  
    
   public Object peek();

   public Object pop();

   public void push(Object item);

   public int size();

}