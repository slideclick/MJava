
public class Customer extends Object{

   private int arrivalTime;
   private int amountOfServiceNeeded;

   // Returns a Customer object if the probability of arrival is
   // greater than or equal to a random number
   // Otherwise, returns null, indicating to new customer
   public static Customer generateCustomer(double probabilityOfNewArrival,
                                           int arrivalTime, 
                                           int averageTimePerCustomer){
                                          
      if (Math.random() <= probabilityOfNewArrival)
         return new Customer(arrivalTime, averageTimePerCustomer);
      else
         return null;
   }
      
   public Customer(int arrivalTime, int serviceNeeded){
      this.arrivalTime = arrivalTime;
      this.amountOfServiceNeeded = serviceNeeded;
   }

   public int arrivalTime(){
      return arrivalTime;
   }
   
   public int amountOfServiceNeeded(){
      return amountOfServiceNeeded;
   }
   
   // Accepts a unit of service from the cashier
   public void serve(){
      amountOfServiceNeeded--;
   }
}
