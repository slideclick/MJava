import java.util.Random;

public class Dice
{
	// static reference that identifies the single class instance
	private static Dice dice = null;
	private Random rnd;
	private int dice1, dice2;

	// private constructor is called by the method getDice()
	// to create a single instance of the class
	private Dice()
	{
		// create a random number generator
		rnd = new Random();
	}

	// if no object currently exists, the method calls the private
	// constructor to create an instance; if an object exists,
	// method returns the static reference variable
	public static Dice getDice()
	{
		if (dice == null)
		{
			dice = new Dice();
		}
		return dice;
	}

	// toss the dice and update values for dice1 and dice2
	public void toss()
	{
		dice1 = rnd.nextInt(6) + 1;
		dice2 = rnd.nextInt(6) + 1;
	}

	// return the value of dice1 from the previous toss
	public int getOne()
	{ return dice1; }

	// return the value of dice2 from the previous toss
	public int getTwo()
	{ return dice2; }

	// return the total of the dice from the previous toss
	public int getTotal()
	{ return dice1 + dice2; }
}

