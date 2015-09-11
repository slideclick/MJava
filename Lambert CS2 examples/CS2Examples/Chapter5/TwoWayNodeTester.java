public class TwoWayNodeTester{
    
   // Run the test during the construction of the TwoWayNodeTester object
   public TwoWayNodeTester(){

      // Create a doubly linked structure with one node
      TwoWayNode head = new TwoWayNode("1", null, null);
      TwoWayNode tail = head;

      // Add four nodes to the end of the doubly linked structure
      for (int i = 2; i <= 5; i++){
         String str = i + "";
         tail.next = new TwoWayNode(str, tail, null);
         tail = tail.next;
      }

      // Print the contents of the linked structure in reverse order
      TwoWayNode probe = tail;
      while (probe != null){
         System.out.println(probe.value);
         probe = probe.previous;
      }

   }


   // Instantiate the TwoWayNodeTester class so the test runs
   public static void main(String[] args){
      new TwoWayNodeTester();
   }

   private class TwoWayNode extends Object{

      private Object     value;    //Value stored in this node
      private TwoWayNode next;     //Reference to next node
      private TwoWayNode previous; //Reference to previous node
    
      private TwoWayNode(){
         value = null;
         previous = null;
         next = null;
      }

      private TwoWayNode(Object value){
         this.value = value;
         previous = null;
         next = null;
      }

      private TwoWayNode(Object value, 
                         TwoWayNode previous, 
                         TwoWayNode next){
         this.value = value;
         this.previous = previous;
         this.next = next;
      }
}
