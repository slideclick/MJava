import lamborne.*;
import ioutil.Format;

public class Cashier extends Object{

   private int totalCustomerWaitTime, customersServed;
   
   private Customer currentCustomer;
   private Queue queue;

   public Cashier(){
      totalCustomerWaitTime = 0;
      customersServed = 0;
      currentCustomer = null;
      queue = new LinkedQueue();
   }
   
   public void addCustomer(Customer c){
      queue.enqueue(c);
   }
   
   public void serveCustomers(int currentTime){
      if (currentCustomer == null){
         // No customers yet
         if (queue.isEmpty())
            return;
         else{
            // Dequeue first waiting customer and tally results
            currentCustomer = (Customer) queue.dequeue();
            totalCustomerWaitTime = totalCustomerWaitTime + 
                                    currentTime - 
                                    currentCustomer.arrivalTime();
            customersServed++;
         }
      }

      // Give a unit of service
      currentCustomer.serve();

      // If current customer is finished, send it away   
      if (currentCustomer.amountOfServiceNeeded() == 0)
         currentCustomer = null;
   }
   
   public String toString(){
      String str = "TOTALS FOR THE CASHIER\n" +
                   "Number of customers served:        " +
                   customersServed + "\n";
      if (customersServed != 0){
         double aveWaitTime = (double)totalCustomerWaitTime /
                              customersServed;
         str += "Number of customers left in queue: " +
                queue.size() + "\n" +
                "Average time customers spend\n" + 
                "waiting to be served:              " +
                Format.justify('l', aveWaitTime, 5, 2);
      }
      return str;
   }
}
