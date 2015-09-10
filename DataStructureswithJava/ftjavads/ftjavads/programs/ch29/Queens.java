public class Queens
{
	// try to find a solution to the 8-Queens problem starting
	// with a queen at (row,0)
	public static boolean safeLocation(int row, int col, int[] queenList)
	{
		int qRow, qCol;

		for (qCol = 0; qCol < col; qCol++)	// check previous columns only
		{
			qRow = queenList[qCol];
			if (qRow == row)				// same row
				return false;
			else if (qCol == col)		// same col
				return false;
			// can they attack on a diagonal?
			else if(qCol-qRow == col-row || qCol+qRow == col+row)
				return false;
		}
		return true;
	}

	public static boolean placeQueens(int[] queenList, int col)
	{
		int row;
		boolean foundLocation;

		if (col == 8)	// stopping condition
			foundLocation = true;
		else
		{
			foundLocation = false; // start with row 0
			row = 0;
			while (row < 8 && !foundLocation)
			{
				// check whether cell (row, col) is safe; if so,
				// assign row to queenList and call placeQueens()
				// for next column; otherwise, go to the next row
				if (safeLocation(row,col,queenList) == true)
				{
					// found good location
					queenList[col] = row;

					// recursive step. try to place queens in columns col+1
					// through 7
					foundLocation = placeQueens(queenList,col+1);
					if (!foundLocation)
						// use next row since current one does not lead
						// to a solution
						row++;
				}
				else
					// current row fails. go to the next row
					row++;

			}	// end while
		}

		// pass success or failure back to previous col
		return foundLocation;
	}

	public static boolean queens(int[] queenList, int row)
	{
		// place first queen at (row,0)
		queenList[0] = row;

		// locate remaining queens in columns 1 through 7
		if (placeQueens(queenList, 1))
			return true;
		else
			return false;
	}
}