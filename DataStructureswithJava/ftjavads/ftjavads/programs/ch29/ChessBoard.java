import java.awt.*;
import javax.swing.*;

public class ChessBoard
{
	private boolean[][] board;		// simulates chess board

	private void clearBoard()
	{
		for(int i=0;i < 8;i++)
			for(int j=0;j < 8;j++)
				board[i][j] = false;
	}

	private void clearDisplay(JLabel[][] queenLabel)
	{
		for(int i=0;i < 8;i++)
			for(int j=0;j < 8;j++)
				queenLabel[i][j].setVisible(false);
	}


	public ChessBoard()
	{
		board = new boolean[8][8];
	}

	// set queens on board at cells (queenList[col], col)
	// 0 <= col < 8
	public void setQueens(int[] queenList)
	{
		clearBoard();

		for (int col = 0; col < 8; col++)
				board[queenList[col]][col] = true;
	}

	// draw the chess board
	public void drawBoard()
	{
		int i,j;

		System.out.println("   0 1 2 3 4 5 6 7");
		for (i = 0; i < 8; i++)
		{
			System.out.print(i + " ");
			// draw the squares in current row
			for (j = 0; j < 8; j++)
			{
				if (board[i][j] == true)
					System.out.print(" Q");
				else
					System.out.print(" -");
			}
			System.out.println();
		}
	}

	// draw the chess board
	public void displayBoard(JLabel[][] queenLabel)
	{
		int i,j;

		for (i = 0; i < 8; i++)
		{
			for (j = 0; j < 8; j++)
			{
				if (board[i][j] == true)
					queenLabel[i][j].setVisible(true);
				else
					queenLabel[i][j].setVisible(false);
			}
		}
	}
}
