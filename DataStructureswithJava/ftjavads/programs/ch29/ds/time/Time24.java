/*
 * @(#)Time24.java
 */
package ds.time;

import java.util.StringTokenizer;
import java.text.DecimalFormat;
import java.io.Serializable;
import java.io.*;

/**
 * An instance of the class  stores time in hour and minute units
 * where hour is in the range 0 to 23 and minute is in the
 * range 0 to 59.
 */
public class Time24 implements Comparable<Time24>, Cloneable,
										java.io.Serializable
{
   private static final long serialVersionUID = 10001L;

	// instance variables
   private int hour, minute;

   // utility method sets the hour value in the range 0 to 23
   // and the minute value in the range 0 to 50
   private void normalizeTime()
   {
      int extraHours = minute / 60;

      // set minute in range 0 to 59
      minute %= 60;

      // update hour. set in range 0 to 23
      hour = (hour + extraHours) % 24;
   }

	/**
	 * Creates an instance with initial values for the hour and minute.
	 * The values hour and minute are adjusted to their proper ranges.
	 * @param hour    number of hours in the initial time.
	 * @param minute  number of minutes in the initial time.
	 * @throws <tt>InvalidArgumentException</tt> if hour and minute are not positive values.
	 */
   public Time24(int hour, int minute)
   {
      setTime(hour,minute);
   }

	/**
	 * Default constructor creates an instance with hour and minute set to 0  (time is midnight).
	 */
    public Time24()
    {
        this(0,0);
    }

   /**
	 * Updates the current time by the value m specifying minutes.  If necessary,
	 * hour and minute are adjusted to their proper ranges.
	 * @param m  advance time by m minutes.
	 * @throws <tt>InvalidArgumentException</tt> if <tt>m</tt> is not a positive value.
	 */
   public void addTime(int m)
   {
      if (m < 0)
         throw new IllegalArgumentException("Time24.setTime: argument"
                + " must be a positive integer");
      minute += m;
      normalizeTime();
   }

   /**
	 * Compares this <tt>Time24</tt> object with another <tt>Time24</tt> object.
	 * Returns a negative
    * integer, zero, or a positive integer depending on whether this time.
    * is less than, equal to, or greater than the time of the other object.
 	 * @param t  a <tt>Time24</tt> object.
    * @return an integer value that is negative, zero, or positive.
	 */
   public int compareTo(Time24 t)
   {
      int time, ttime;

      time = hour * 60 + minute;

      ttime = t.hour * 60 + t.minute;

      if (time < ttime)
         return -1;
      else if (time == ttime)
         return 0;
      else
         return 1;
   }

   /**
	 * Returns the length of time from this object to some other <tt>Time24</tt> object t.
 	 * @param t  a <tt>Time24</tt> object to compare with.
    * @return a <tt>Time24</tt> object designating the interval of time.
	 */
   public Time24 interval(Time24 t)
   {
      // convert current time and time t to minutes
      int currTime = hour * 60 + minute;
      int tTime = t.hour * 60 + t.minute;

      // if t is earlier than the current time, add 24 hours to
      // indicate that t is time in the next day
      if (tTime < currTime)
         tTime += 24 * 60;

		// return a reference to a new Time24 object
		return new Time24(0, tTime-currTime);
   }

   /**
	 * Compares this <tt>Time24</tt> object with another Object. Return true if they
    * specify the same time and false otherwise.
 	 * @param obj  the object to compare with.
    * @return true if the objects are the same and false otherwise.
	 */
   public boolean equals(Object obj)
   {
      Time24 t = (Time24)obj;

      if (obj instanceof Time24)
      {
			int time, ttime;
      	time = hour * 60 + minute;
      	ttime = t.hour * 60 + t.minute;

      	return time == ttime;
		}
		return false;
   }

	/**
	 * Returns the hour value for this object.
	 * @return the hour.
	 */
   public int getHour()
   { return hour; }

	/**
	 * Returns the minute value for this object.
	 * @return the minute.
	 */
   public int getMinute()
   { return minute; }

  	/**
	 * Updates the hour and minute for this object to the specified
	 * values. If necessary, it adjusts hour and minute to their proper range.
	 * @param hour    number of hours for the updated time.
	 * @param minute  number of minutes for the updated time.
	 * @throws InvalidArgumentException if hour and minute are not positive values.
	 */
   public void setTime(int hour, int minute)
   {
      // check that the parameters hour and minute are positive
      if (hour < 0 || minute < 0)
         throw  new IllegalArgumentException("Time24 setTime: "
               + "parameters must be positive integers");

		// assign new values and call normalizeTime()
      this.hour = hour;
      this.minute = minute;

      // adjust hour and minute as necessary
      normalizeTime();
   }

  	/**
	 * Returns a string describing this time in the format hh:mm.
	 * @return string describing this time.
	 */
   public String toString()
   {
      // create a text format object with two character positions
      // and fill character 0
      DecimalFormat fmt = new DecimalFormat("00");

      return new String(hour + ":" + fmt.format(minute));
   }

	/**
	 * Parses the string argument as time in the format hh:mm. The resulting
	 * time is returned as a Time24 object.
	 *
	 * @param s	   a <code>String</code> containing a time representation
	 *             to be parsed.  The format of the string is hh:mm
	 * @return     the <tt>Time24</tt> object represented by the argument.
	 */
	public static Time24 parseTime(String s)
   {
		StringTokenizer stok = new StringTokenizer(s, " :");
		String timePeriod = null;
		int hour, minute;

		hour = Integer.parseInt(stok.nextToken());
		minute = Integer.parseInt(stok.nextToken());
		return new Time24(hour, minute);
	}

	/**
	 * Returns a shallow copy of this <tt>Time24</tt> instance.
	 *
	 * @return  a clone of this <tt>Time24</tt> instance.
	 */
	public Object clone()
	{
		// the clone that is returned
		Object copy = null;

		try
		{
			// call the Object method clone(). copy is a reference
			// to a Time24 object
			copy = (Time24)super.clone();
		}
		catch (CloneNotSupportedException cnse)
		{
			// exception indicates a fatal error in the virtual machine
			throw new InternalError();
		}

		return copy;
	}

	/**
	 * Returns a hash code for this <tt>Time24</tt> object.
	 *
	 * @return  a hash code value for this object, equal to the
	 *          time measured in minutes.
	 */
	public int hashCode()
	{
		return hour*60 + minute;
	}

	/**
	 * Save the state of the <tt>Time24</tt> instance to a stream.
	 * @param out  serialize this instance to the specified ObjectOutputStream.
	 */
	private void writeObject(ObjectOutputStream out)
		throws java.io.IOException
	{
		// write out the hour and minute values and and internal serialization magic
		out.defaultWriteObject();
	}

	/**
	 * Reconstitute the <tt>Time24</tt> instance from a stream.
	 * @param in  reconstitute (deserialize) this instance from the ObjectInputStream.
	 */
	private void readObject(ObjectInputStream in)
		throws java.io.IOException, ClassNotFoundException
	{
		// read in hour and minute and and internal serialization magic
		in.defaultReadObject();
	}
}
