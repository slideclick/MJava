
public class MarketModel extends Object{

   private Cashier cashier;
   private double probabilityOfNewArrival;
   private int lengthOfSimulation, averageTimePerCus;

   public MarketModel(int lengthOfSimulation, 
                      int averageTimePerCus, 
                      double probabilityOfNewArrival){
      this.probabilityOfNewArrival = probabilityOfNewArrival;
      this.lengthOfSimulation = lengthOfSimulation;
      this.averageTimePerCus = averageTimePerCus;
      cashier = new Cashier();
   }
   
   public String runSimulation(){
      // Run the clock for n ticks
      for (int currentTime = 0; currentTime < lengthOfSimulation;
           currentTime++){

         // Attempt to generate a new customer
         Customer customer = 
                  Customer.generateCustomer(probabilityOfNewArrival, 
                                            currentTime, 
                                            averageTimePerCus);

         // Send customer to cashier if successfully generated
         if (customer != null)
            cashier.addCustomer(customer);

         // Tell cashier to provide another unit of service
         cashier.serveCustomers(currentTime);
      }
      return cashier.toString();
   }
}
