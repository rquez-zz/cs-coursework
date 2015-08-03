
public class MiniMax 
{
	int m_nMaxPly = 7;
	
	//  This is the piece to search for. Should be either RED or YELLOW.
	int m_nSearchPiece = 0;
	
	// Set the piece for which a move will be found.
	public void SetSearchPiece( int nPiece )
	{
		m_nSearchPiece = nPiece;
	}

	// Compile a list of legal moves.
	int GetLegalMoves( int[][] nBoardData, int[] nMoveList )
	{
		int row, col;
		int nNumMoves = 0;

		// Loop through six rows.
		for( row=0; row<=5; row++ )
		{
			// Loop through seven columns.
			for( col=0; col<=6; col++ )
			{
				// If the board at this location is empty,
				//   then this square is a legal move.
				if( nBoardData[row][col] == Connect4.EMPTY &&
					( row == 5 || nBoardData[row+1][col] != Connect4.EMPTY ) )
				{
					// Put the row and column in the move list array.
					nMoveList[nNumMoves*2] = row;
					nMoveList[nNumMoves*2+1] = col;
					// Increment the index pointer.
					nNumMoves++;
				}
			}
		}

		// Return the number of legal moves that were found.
		return( nNumMoves );
	}

	int DoSearch( Position pos, int nPiece, int nDepth, Board pBoard, int nAlpha, int nBeta )
	{
		// Local arrays for the legal move list and
		//   the result list.
		int[] nMoveList = new int[7*2];
		int[] nResultList = new int[7];

		// Create value for node
		int nValue;

		// First, see if a side has won.
		if( pBoard.DidSideWin( nPiece ) )
		{
			if( nPiece == m_nSearchPiece )
			{
				return( 2000000 );
			}
			return( -2000000 );
		}
		
		// See if we have a Cats game.
		else if( pBoard.IsCatsGame() )
		{
			// Score for Cats game is 0.
			return( 0 );
		}
		
		// If we are at a leaf node, return the score.
		else if( nDepth >= m_nMaxPly )
		{
			return( ScoreIt( m_nSearchPiece, pBoard.GetBoardData() ) );
		}

		// Get the legal moves.
		int nMoves = GetLegalMoves( pBoard.GetBoardData(), nMoveList );

		if( nDepth == 0 )
		{
			pos.Row = nMoveList[0];
			pos.Col = nMoveList[1]; 
		}
		

		// If this is max of minimax, then return the max.
		if( nPiece == m_nSearchPiece )
		{

			nValue = -2000000;

			// Loop through the legal moves.
			for( int i=0; i<nMoves; i++ )
			{
				// We need a board clone so that we can place pieces
				//   without messing up previous board positions.
				Board SaveMe = pBoard.Clone();

				// Place the piece from the current move in the list.
				pBoard.PlacePiece( nMoveList[i*2], nMoveList[i*2+1], nPiece );

				// Save the value for later
				int nTempValue = nValue;

				// Call DoSearch() recursively.
				nValue = Math.max( nValue, DoSearch(pos, nPiece ^ 1, nDepth + 1, pBoard, nAlpha, nBeta) );

				// For nAlpha we get the max of nResultList[i] and nAlpha
				nAlpha = Math.max(nValue, nAlpha);

				// If the previous value was less than this value, then
				//   we have a better move. If we are at depth 0 then
				//   we save row and col to the Position data structure.
				if( nTempValue < nValue )
				{
					nResultList[i] = nValue;

					if( nDepth == 0 )
					{
						pos.Row = nMoveList[i*2];
						pos.Col = nMoveList[i*2+1];
					}
				}

				// Restore the board.
				pBoard = SaveMe.Clone();

				// When beta is less than or equal alpha, we don't need to keep going.
				if (nBeta <= nAlpha)
					break;
			}

			return( nValue );
		}
		
		// If this is min of minimax, then return the min.
		else
		{
			nValue = 2000000;

			// Loop through the legal moves.
			for( int i=0; i<nMoves; i++ )
			{
				// We need a board clone so that we can place pieces
				//   without messing up previous board positions.
				Board SaveMe = pBoard.Clone();

				// Place the piece from the current move in the list.
				pBoard.PlacePiece( nMoveList[i*2], nMoveList[i*2+1], nPiece );

				// Call DoSearch() recursively.
				nValue = Math.min( nValue, DoSearch(pos, nPiece ^ 1, nDepth + 1, pBoard, nAlpha, nBeta) );
				nBeta = Math.min( nResultList[i], nBeta );

				// Restore the board.
				pBoard = SaveMe.Clone();

				// When beta is less than or equal alpha, we don't need to keep going.
				if (nBeta <= nAlpha)
					break;
			}
			return( nValue );
		}
		
	}

	// Wrapper method that kicks off minimax to get a move.
	public void GetMove( Position pos, int[][] BoardData, int nPiece )
	{
		// Set the search piece.
		SetSearchPiece( nPiece );
		
		// Create a new board with this board data.
		Board brd = new Board();
		brd.SetBoardData( BoardData );

		// Call the recursive method.
		DoSearch( pos, nPiece, 0, brd, -2000000, 2000000);
	}
	
	int ScoreIt( int nPiece, int[][] BoardData )
	{
		int Twos = 0;
		int Threes = 0;
		int Fours =0 ;
		
		for( int nRow=0; nRow<6; nRow++ )
		{
			int nCount = 0;
			for( int nCol=0; nCol<7; nCol++ )
			{
				if( BoardData[nRow][nCol] == nPiece )
				{
					nCount++;
				}
				else
				{
					if( nCount == 2 )
					{
						Twos++;
					}
					else if( nCount == 3 )
					{
						Threes++;
					}
					else if( nCount == 4 )
					{
						Fours++;
					}
					nCount = 0;
				}
			}
			if( nCount == 2 )
			{
				Twos++;
			}
			else if( nCount == 3 )
			{
				Threes++;
			}
			else if( nCount == 4 )
			{
				Fours++;
			}
		}
		
		for( int nCol=0; nCol<7; nCol++ )
		{
			int nCount = 0;
			for( int nRow=0; nRow<6; nRow++ )
			{
				if( BoardData[nRow][nCol] == nPiece )
				{
					nCount++;
				}
				else
				{
					if( nCount == 2 )
					{
						Twos++;
					}
					else if( nCount == 3 )
					{
						Threes++;
					}
					else if( nCount == 4 )
					{
						Fours++;
					}
					nCount = 0;
				}
			}
			if( nCount == 2 )
			{
				Twos++;
			}
			else if( nCount == 3 )
			{
				Threes++;
			}
			else if( nCount == 4 )
			{
				Fours++;
			}
		}
		
		// Loop through the diagonal data.
		for( int nDiagonalTest=0; nDiagonalTest<Board.m_nDiagonalData.length/4; nDiagonalTest++)
		{
			int nCount = 0;
			// Starting row.
			int nRow = Board.m_nDiagonalData[nDiagonalTest*4];
			// Starting column.
			int nCol = Board.m_nDiagonalData[nDiagonalTest*4+1];
			// YDirection for the iterations.
			int nYDir = Board.m_nDiagonalData[nDiagonalTest*4+2];
			// Number of iterations.
			int nIterations = Board.m_nDiagonalData[nDiagonalTest*4+3];

			// Loop through the iterations.
			for( int i=0; i<nIterations; i++ )
			{
				// If this is nSide then increment the counter.
				if( BoardData[nRow][nCol] == nPiece )
				{
					// Increment the counter.
					nCount++;
				}
				
				// This square does not equal nSide.
				else
				{
					if( nCount == 2 )
					{
						Twos++;
					}
					else if( nCount == 3 )
					{
						Threes++;
					}
					else if( nCount == 4 )
					{
						Fours++;
					}
					
					// Reset the counter.
					nCount = 0;
				}
				
				// Move the row position.
				nRow += nYDir;
				// Move the column position.
				nCol++;
			}
			
			if( nCount == 2 )
			{
				Twos++;
			}
			else if( nCount == 3 )
			{
				Threes++;
			}
			else if( nCount == 4 )
			{
				Fours++;
			}
			
		}

		int nPositionalAdvantage = 0;
		for( int nCol=0; nCol<7; nCol++ )
		{
			int nCount = 0;
			for( int nRow=0; nRow<6; nRow++ )
			{
				if( BoardData[nRow][nCol] == nPiece )
				{
					nCount++;
				}
			}
			
			if( nCol == 2 || nCol == 3 )
			{
				nPositionalAdvantage += nCount * 2;
			}
			else if( nCol == 1 || nCol == 4 )
			{
				nPositionalAdvantage += nCount;
			}
			
		}
		for( int nCol=0; nCol<7; nCol++ )
		{
			int nCount = 0;
			for( int nRow=0; nRow<6; nRow++ )
			{
				if( BoardData[nRow][nCol] != nPiece )
				{
					nCount++;
				}
			}
			
			if( nCol == 2 || nCol == 3 )
			{
				//nPositionalAdvantage -= nCount * 2;
			}
			else if( nCol == 1 || nCol == 4 )
			{
				//nPositionalAdvantage -= nCount;
			}
			
		}
		
		return( nPositionalAdvantage + Twos + Threes * 2 + Fours * 4 );
	}

	static String ArrayToString( int[] IntArray )
	{
		String strRet = "{ ";
		for( int i=0; i<IntArray.length; i++ )
		{
			strRet += (""+IntArray[i] );
			if( i < IntArray.length - 1 )
			{
				strRet += ", ";
			}
		}
		return( strRet + " }");
	}
	
}

