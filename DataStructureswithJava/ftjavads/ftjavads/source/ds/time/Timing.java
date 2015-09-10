/*
 * @(#)Timing.java
 */
package ds.time;

/**
 * An instance of the class acts like a stop watch for measuring the
 * time required to execute a process;  time is measured in seconds from start to stop
 * using millisecond intervals from the system clock.
 */
public class Timing
{
      // starting and ending time measured in milliseconds
      private long startTime, stopTime;

      /**
		 * Creates an instance with default values 0 for both the start time and
		 * and the stop time.
		 */
		public Timing()
      {
         startTime = stopTime = 0;
      }

      /**
		 * Establishes a starting time for the process by recording the
		 * current millisecond time on the system clock
		 */
      public void start()
      {
         startTime = System.currentTimeMillis();
      }

      /**
		 * Establishes a time in seconds by computing the interval from the start time
		 * to the current time on the system clock.
    	 * @return interval of time from start to stop measured in seconds.
		 */
      public double stop()
      {
         stopTime = System.currentTimeMillis();

         return (stopTime - startTime)/1000.0;
   }
}
