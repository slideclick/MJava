public class NodeTester{
    
   // Run the test during the construction of the NodeTester object
   public NodeTester(){
      Node head = null;

      // Add five nodes to the beginning of the linked structure
      for (int i = 1; i <= 5; i++){
         String str = String.valueOf(i);
         head = new Node(str, head);
      }

      // Print the contents of the structure
      while (head != null){
         System.out.println(head.value);
         head = head.next;
      }
   }

   // Instantiate the NodeTester class so the test runs
   public static void main(String[] args){
      new NodeTester();
   }

   private class Node extends Object{

      private Object value;    //Value stored in this node
      private Node   next;     //Reference to next node
    
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
