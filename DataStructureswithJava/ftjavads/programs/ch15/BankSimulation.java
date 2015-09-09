import java.util.Random;     // include random number generator
import java.util.Scanner;
import java.text.DecimalFormat;

public class BankSimulation extends EventDrivenSimulation                                         
{
	// parameters used to describe the simulation
	int simulationLength;        	// simulation length
	int numTellers;
	int arrivalLow, arrivalHigh; 	// next arrival range
	int serviceLow, serviceHigh; 	// service range
	
	// variables used to monitor simulation
	int numCustomers = 0;
	int totalWaitTime = 0;
	int prevArrTime = 0;				// used for delay between arrivals
	boolean verboseRun = false;	// detail each event?
	Random rnd = new Random();		// use for random times
	Teller[] tList = null;			// list of tellers 
		
	public void startSimulation()
	{
		// read simulation parameters from keyboard
		inputParameters();
			   
	    // create instances for each teller; for convenience,
	    // tellers are referenced with indices beginning at 1
	    for (int i = 1; i <= numTellers; i++)
	    	tList[i] = new Teller();
	    	
		// for the length of the simulation, create successive 
		// arrival events at random arrival times; push events 
		// on the priority queue and update prevArrTime
		int t = 0;
		while (t < simulationLength)
		{
			// randomTime() returns random integer in the range
			// arrivalLow to arrivalHigh
			t += randomTime(arrivalLow, arrivalHigh);
			if (t >= simulationLength)
				break;
			// create an arrival event and add it to priority queue
			pushEvent(new ArrivalEvent(t, prevArrTime));
			// update prevArrTime for use by next arrival
			prevArrTime = t;
		}
		
		// with arrival events loaded in the priority queue, begin
		// execution of the simulation. Note: during execution, the
		// queue will dynamically grow since when an arrival event
		// exits the queue, its doEvent() method adds a service 
		// event to the queue; when a service event exits the queue,
		// its doEvent() method add a departure event. 
		run();
		
		// display a summary of results
		displayResults();
	}

	public void inputParameters()
	{
		Scanner sc = new Scanner(System.in);
		
		// inputs parameters for the simulation
    	System.out.print("Use verbose run ('Y' or 'N'): ");
    	String token = sc.next();
    	if (token.charAt(0) == 'Y' || token.charAt(0) == 'y')
    		verboseRun = true;
    		
    	System.out.print("Enter the simulation time in minutes: ");
    	simulationLength = sc.nextInt();
    	System.out.print("Enter number of available tellers: ");
    	numTellers = sc.nextInt();
    	tList = new Teller[numTellers+1];
    	
    	System.out.print("Enter range of potential arrival times: ");
    	arrivalLow = sc.nextInt();
    	arrivalHigh = sc.nextInt();
    	System.out.print("Enter range of potential service times: ");
    	serviceLow = sc.nextInt();
    	serviceHigh = sc.nextInt();
	}

	public void displayResults()
	{
		// output summary data for the simulation; for customers
		// include number and average wait time; for tellers include
		// pct of time worked and overtime
		DecimalFormat fmt = new DecimalFormat("0.0");
		int endService;
		double avgWait = (double)totalWaitTime/numCustomers,
		       pctService;
			
		System.out.println("Simulation Summary");
		System.out.println("  Number of customers is " +
			numCustomers);
		System.out.println("  Average waiting time is " +
					fmt.format(avgWait) + " minutes");
		System.out.println("  Service time for tellers");
		for (int i = 1; i <= numTellers; i++)
		{
			endService = simulationLength + tList[i].overtime;
			pctService = (double)tList[i].totalService/endService;
			System.out.println("    " + "Teller " + i + ":  Busy " + 
			      fmt.format(pctService * 100) + "%  Overtime " +
			      tList[i].overtime);
		}
	}
		
	public int randomTime(int low, int high)
	{
		return rnd.nextInt(high - low + 1) + low;
	}
	
	private static class Teller
	{
		public int backService = 0;
		public int totalService = 0;
		public int overtime = 0; 
	}
	
	private class ArrivalEvent extends Event
	{
		private int elapsedTime;

		public ArrivalEvent(int time, int prevArrTime)
		{
			super(time);
			elapsedTime = time - prevArrTime;
		}
		
		public void doEvent()
		{
			int i, minTeller, serviceTime;
			DecimalFormat numberFmt = new DecimalFormat("00");

			// increment number of customers
			numCustomers++;
			
			// use elapsedTime to update backService for each teller.
			// return teller with minimum backService; this is 
			// the next available teller
			minTeller = minTellerService(tList, elapsedTime );
			
			// backService for teller is wait time for customer
			totalWaitTime += tList[minTeller].backService;

			// generate service time for customer; add to backService 
			// for minTeller who is committed to serve customer
			serviceTime = randomTime(serviceLow, serviceHigh);
			tList[minTeller].backService += serviceTime;

			if (verboseRun)
				System.out.println("Customer #" + 
						numberFmt.format(numCustomers) +	"  Arrival " + 
						time + "  Wait " + tList[minTeller].backService);
						
			// create ServiceEvent object and add to priority queue
			pushEvent(new ServiceEvent(
						 time + tList[minTeller].backService,
						 numCustomers, 
						 minTeller,
						 serviceTime));
		}
		
		private int minTellerService(Teller[] tList, int elapsedTime)
		{
			int i, minTeller = 1;
			for (i = 1; i <= numTellers; i++) 
				tList[i].backService = 
				    (tList[i].backService - elapsedTime <= 0) ? 0 : 
				    tList[i].backService - elapsedTime;
				    
			for (i = 2; i <= numTellers; i++)
				if (tList[i].backService < tList[minTeller].backService)
					minTeller = i;
					
			return minTeller;
		}
	}	
		
	private class ServiceEvent extends Event
	{
		int customer;
		int teller;
		int serviceTime;
		public ServiceEvent(int time, int customer, int teller,
		           			  int serviceTime)
		{
			super(time);
			this.customer = customer;
			this.teller = teller;
			this.serviceTime = serviceTime;
		}
			
		public void doEvent()
		{
			DecimalFormat numberFmt = new DecimalFormat("00");
			
			tList[teller].totalService += serviceTime;
			if (verboseRun)
				System.out.println("Customer #" + numberFmt.format(customer) +
			   	"  Begin service at "  + time + " by teller " + teller +
			   	"  Service time " + serviceTime);
			   	
			pushEvent(new DepartureEvent(time + serviceTime, 
			                              customer, teller));
		}
	}	
		
	private class DepartureEvent extends Event
	{
		int customer;
		int teller;
		
		public DepartureEvent(int time, int customer, int teller)
		{
			super(time);
			this.customer = customer;
			this.teller = teller;
		}
			
		public void doEvent()
		{
			DecimalFormat numberFmt = new DecimalFormat("00");
			tList[teller].overtime = (time <= simulationLength) ? 0 :
			      time - simulationLength;
			if (verboseRun)
				System.out.println("Customer #" + numberFmt.format(customer) +
		      	"  Departs " + time + "  Served by " + teller);
		}	
	}		
}

/*

Run 1:

Use verbose run ('Y' or 'N'): Y
Enter the simulation time in minutes: 30
Enter number of available tellers: 2
Enter range of potential arrival times: 4 8
Enter range of potential service times: 10 18
Customer #01  Arrival 8  Wait 17
Customer #02  Arrival 15  Wait 16
Customer #03  Arrival 22  Wait 19
Customer #01  Begin service at 25 by teller 1  Service time 17
Customer #04  Arrival 26  Wait 17
Customer #02  Begin service at 31 by teller 2  Service time 16
Customer #03  Begin service at 41 by teller 1  Service time 16
Customer #01  Departs 42  Served by 1
Customer #04  Begin service at 43 by teller 2  Service time 12
Customer #02  Departs 47  Served by 2
Customer #04  Departs 55  Served by 2
Customer #03  Departs 57  Served by 1
Simulation Summary
  Number of customers is 4
  Average waiting time is 2.0 minutes
  Service time for tellers
    Teller 1:  Busy 57.9%  Overtime 27
    Teller 2:  Busy 50.9%  Overtime 25

Run 2:

Use verbose run ('Y' or 'N'): N
Enter the simulation time in minutes: 480
Enter number of available tellers: 4
Enter range of potential arrival times: 2 5
Enter range of potential service times: 6 20
Simulation Summary
  Number of customers is 144
  Average waiting time is 4.0 minutes
  Service time for tellers
    Teller 1:  Busy 94.7%  Overtime 27
    Teller 2:  Busy 93.2%  Overtime 20
    Teller 3:  Busy 88.2%  Overtime 29
    Teller 4:  Busy 86.6%  Overtime 21
*/