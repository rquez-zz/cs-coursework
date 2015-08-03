
public class Board 
{
	
	// The board data is contained in a 3x3 integer array.
	int[][] BoardData = new int[6][7];

	// The constructor simply calls Clear()
	public Board()
	{
		Clear();
	}

	// This method is necessary for the minimax algorithm. It would
	//   have been cleaner if java provided an = operator.
	public Board Clone()
	{
		// Create a new board object.
		Board Ret = new Board();
		// Set the board data in the newly-created object.
		Ret.SetBoardData( BoardData );
		// Return the newly-created class.
		return( Ret );
	}

	// See if the square at a position (row/col) is empty.
	public boolean IsSquareEmpty(int row, int col)
	{
		return (BoardData[row][col] == Connect4.EMPTY);
	}

	// See if the square at a positin (sq) is empty.
	public boolean IsSquareEmpty(int sq)
	{
		return (IsSquareEmpty(sq/6,sq%7));
	}

	// Get the board data for an external class to access.
	public int[][] GetBoardData()
	{
		return (BoardData);
	}

	// Set the board data from a 6x7 array.
	public void SetBoardData(int[][] data)
	{
		// Loop through six rows.
		for (int row = 0; row <= 5; row++)
		{
			// Loop through seven columns.
			for (int col = 0; col <= 6; col++)
			{
				BoardData[row][col] = data[row][col];
			}
		}
	}

	// Clear the board data to all empty.
	public void Clear()
	{
		// Loop through six rows.
		for( int row=0; row<=5; row++ )
		{
			// Loop through seven columns.
			for( int col=0; col<=6; col++ )
			{
				BoardData[row][col] = Connect4.EMPTY;
			}
		}
	}

	// Place a piece in the board data array at sq.
	public void PlacePiece(int sq, int piece)
	{
		PlacePiece(sq / 6, sq % 7, piece);
	}

	// Place a piece in the board data array at row/col.
	public void PlacePiece( int row, int col, int piece )
	{
		// Check that we are in bounds.
		if (row < 0 ||
			row > 5 ||
			col < 0 ||
			col > 6)
		{
			return;
		}

		// Place the data.
		BoardData[row][col] = piece;
	}

	// See if this is a Cats game (tied)
	public boolean IsCatsGame()
	{
		// Set the count to 0.
		int nCount = 0;

		// Loop through six rows.
		for (int row = 0; row <= 5; row++)
		{
			// Loop through seven columns.
			for (int col = 0; col <= 6; col++)
			{
				// If the data here is empty, increment the counter.
				if (BoardData[row][col] == Connect4.EMPTY)
				{
					nCount++;
				}
			}
		}

		// If we have no empty spots, then it is a Cats game.
		return (nCount == 0);
	}

	// This data is for looking at diagonals.
	//   The data is in sets of four. The first is the starting
	//   row. The second is the starting column. This third is the
	//   YDirection. THe fourth is the number of test for this iteration.
	public static int[] m_nDiagonalData =
	{
		3, 0, -1, 4,
		4, 0, -1, 5,
		5, 0, -1, 6,
		5, 1, -1, 6,
		5, 2, -1, 5,
		5, 3, -1, 4,
		
		0, 3, 1, 4,
		0, 2, 1, 5,
		0, 1, 1, 6,
		0, 0, 1, 6,
		1, 0, 1, 5,
		2, 0, 1, 4
	};
	
	// See if a given side has won.
	public boolean DidSideWin( int nSide )
	{
		
		// Check rows...
		for (int row = 0; row <= 5; row++)
		{
			for( int col=0; col<=7-4; col++ )
			{
				// See if the entire row == nSide.
				if (BoardData[row][col] == nSide &&
					BoardData[row][col+1] == nSide &&
					BoardData[row][col+2] == nSide &&
					BoardData[row][col+3] == nSide )
				{
					return (true);
				}
			}
		}

		// Check columns...
		for (int col = 0; col <= 6; col++)
		{
			for( int row=0; row<=6-4; row++ )
			{
				// See if the entire column == nSide.
				if (BoardData[row][col] == nSide &&
					BoardData[row+1][col] == nSide &&
					BoardData[row+2][col] == nSide &&
					BoardData[row+3][col] == nSide)
				{
					return (true);
				}
			}
		}

		// Loop through the diagonal data.
		for( int nDiagonalTest=0; nDiagonalTest<m_nDiagonalData.length/4; nDiagonalTest++)
		{
			int nCount = 0;
			// Starting row.
			int nRow = m_nDiagonalData[nDiagonalTest*4];
			// Starting column.
			int nCol = m_nDiagonalData[nDiagonalTest*4+1];
			// YDirection for the iterations.
			int nYDir = m_nDiagonalData[nDiagonalTest*4+2];
			// Number of iterations.
			int nIterations = m_nDiagonalData[nDiagonalTest*4+3];

			// Loop through the iterations.
			for( int i=0; i<nIterations; i++ )
			{
				// If this is nSide then increment the counter.
				if( BoardData[nRow][nCol] == nSide )
				{
					// Increment the counter.
					nCount++;
					
					// If we got to 4 then this is a winner.
					if( nCount >= 4 )
					{
						// Return true since this is a winner.
						return( true );
					}
				}
				
				// This square does not equal nSide.
				else
				{
					// Reset the counter.
					nCount = 0;
				}
				
				// Move the row position.
				nRow += nYDir;
				// Move the column position.
				nCol++;
			}
		}
		
		// No sequence of four in a row matching nSide, so return false.
		return (false);
	}
	
}
