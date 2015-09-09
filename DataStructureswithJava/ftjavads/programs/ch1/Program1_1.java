import ds.time.Time24;

public class Program1_1
{
	public static void main(String[] args)
	{
		// Time24 reference variables
		Time24 startGame = new Time24(13,15), 		// game 1:15 PM
		timeOfGame = new Time24(3,23),				// length 3:23
		startNews = Time24.parseTime("17:00");		// news 5:00 PM
		Time24 endGame, timeForInterviews;        // uninitialized

		// create object for endGame, with same time as startGame;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   ate object for endGame, with same time as startGame;
		// update it by adding the time for the game in minutes.
		endGame =
			new Time24(startGame.getHour(), startGame.getMinute());

		// declare an integer that stores length of game in minutes
		int minutesOfGame = timeOfGame.getHour()*60 +
										timeOfGame.getMinute();

		// advance time of endGame using addTime()
		endGame.addTime(minutesOfGame);

		// assign interval() to variable timeForInterviews
		timeForInterviews = endGame.interval(startNews);

		// output
		System.out.println("The game begins at " + startGame);
		System.out.println("The game ends at " + endGame);
		System.out.println("Post game interviews last " +
								 timeForInterviews);
	}
}

/*
Run:

The game begins at 13:15
The game ends at 16:38
Post game interviews last 0:22
*/
