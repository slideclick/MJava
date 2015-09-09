import java.util.Scanner;

public class Program29_2
{
	public static void main (String[] args)
	{
		int row;
		// the array needed by the 8-Queens algorithm
		int[] queenList = new int[8];
		// board will display the solution
		ChessBoard board = new ChessBoard();
		Scanner keyIn = new Scanner(System.in);

		// enter a starting row for queen in column 0
		System.out.print("Enter row for queen in column 0: ");
		row = keyIn.nextInt();
		System.out.println();

		// see if there is a solution
		if (Queens.queens(queenList, row))
		{
			// insert the solution into the chessboard
			board.setQueens(queenList);
			// display the solution
			board.drawBoard();
		}
		else
			System.out.println("No solution");
	}
}
