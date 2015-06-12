import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;


public class EightQueens extends Applet implements MouseListener, MouseMotionListener, ActionListener{

	private static final long serialVersionUID = 1L;
	
	private static final int NUMROWS = 8;
	private static final int NUMCOLS = 8;
	private static final int SQUAREWIDTH = 50;
	private static final int SQUAREHEIGHT = 50;
	private static final int BOARDLEFT = 50;
	private static final int BOARDTOP = 50;
	
	int m_nBoard[][] = new int[NUMROWS][NUMCOLS];
	boolean m_bClash;
	String m_strStatus = "Starting..";
	
	MediaTracker tracker = new MediaTracker(this);
	BufferedImage m_imgQueen;

	private static Button startButton = new Button("Start"); 
	private static Button resetButton = new Button("Reset"); 
	
	Thread solveThread;

	@Override
	public void init() 
	{
		
		addMouseListener(this);

		setSize(1020,700);
		
		try
		{
			m_imgQueen = ImageIO.read(EightQueens.class.getResourceAsStream("queen.png"));
		} 
		catch (IOException el)
		{
			System.out.println(el.getLocalizedMessage());
		}
		tracker.addImage(m_imgQueen, 100);
		try
		{
			tracker.waitForAll();
		}
		catch (InterruptedException e)
		{
			System.out.println(e.getLocalizedMessage());
		}		
		

		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) 
			{
				solveThread = new Thread(new Backtracking());
				solveThread.start();
			}
		});

		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) 
			{
				for (int i = 0; i < m_nBoard.length; i++) 
				{
					for (int j = 0; j < m_nBoard.length; j++)
					{
						m_nBoard[i][j] = 0;
					}
				}
				repaint();
			}
		});

		this.add(startButton);
		this.add(resetButton);

		m_strStatus = "Image loaded";
	}
	

	public void paint (Graphics canvas)
	{
		System.out.println("DEBUG: Painting...");
		m_bClash = false;
		DrawSquares(canvas);
		canvas.setColor(Color.RED);
		CheckColumns(canvas);
		CheckRows(canvas);
		CheckDiagonal1(canvas);
		CheckDiagonal2(canvas);
		canvas.setColor(Color.BLUE);
		canvas.drawString(m_strStatus, BOARDLEFT, BOARDTOP + SQUAREHEIGHT*8 + 20);
	}
	
	public void DrawSquares (Graphics canvas)
	{
		canvas.setColor(Color.black);
		
		for (int nRow = 0; nRow < NUMROWS; nRow++ )
		{
			for (int nCol = 0; nCol < NUMCOLS; nCol++)
			{
				canvas.drawRect(BOARDLEFT + nCol * SQUAREWIDTH, BOARDTOP + nRow * SQUAREHEIGHT, SQUAREWIDTH, SQUAREHEIGHT);
				
				if (m_nBoard[nRow][nCol] != 0)
				{
					canvas.drawImage(m_imgQueen, BOARDLEFT + nCol * SQUAREWIDTH + 8, BOARDTOP + nRow * SQUAREHEIGHT + 6, null);
				}
			}
		}
	}
	
	void CheckColumns (Graphics canvas) 
	{
		for (int nCol = 0; nCol < NUMCOLS; nCol++)
		{
			int nColCount = 0;
			for (int nRow = 0; nRow < NUMROWS; nRow++)
			{
				if (m_nBoard[nRow][nCol] != 0)
				{
					nColCount++;
				}
			}
			if (nColCount > 1)
			{
				canvas.drawLine(BOARDLEFT + nCol * SQUAREWIDTH + (SQUAREWIDTH / 2), 
						BOARDTOP + (SQUAREHEIGHT / 2), 
						BOARDLEFT + nCol * SQUAREWIDTH + (SQUAREWIDTH / 2),
						BOARDTOP + SQUAREHEIGHT * 7 + (SQUAREHEIGHT / 2));
				m_bClash = true;
			}
		}
	}
	
	void CheckRows( Graphics canvas )
	{
		for(  int nRow=0; nRow<NUMROWS; nRow++ )
		{
			int nRowCount = 0;
			for( int nCol = 0; nCol < NUMCOLS; nCol++ )
			{
				if( m_nBoard[nRow][nCol] != 0 )
				{
					nRowCount++;
				}
			}
			if( nRowCount > 1 )
			{
				canvas.drawLine( BOARDLEFT + ( SQUAREWIDTH / 2 ),
					BOARDTOP + nRow * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ),	
					BOARDLEFT + 7 * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
					BOARDTOP + nRow * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ) );
					
				m_bClash = true;
			}
		}
	}
		
	void CheckDiagonal1( Graphics canvas )
	{
		// Check diagonal 1
		for( int nRow=NUMROWS-1; nRow>=0; nRow-- )
		{
			int nCol = 0;
				
			int nThisRow = nRow;
			int nThisCol = nCol;

			int nColCount = 0;
				
			while( nThisCol < NUMCOLS &&
				nThisRow < NUMROWS )
			{
				if( m_nBoard[nThisRow][nThisCol] != 0 )
				{
					nColCount++;
				}
				nThisCol++;
				nThisRow++;
			}
				
			if( nColCount > 1 )
			{
				canvas.drawLine( BOARDLEFT + nCol * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
						BOARDTOP + nRow * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ),	
						BOARDLEFT + ( nThisCol - 1 ) * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
						BOARDTOP + ( nThisRow - 1 ) * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ) );
					
				m_bClash = true;
			}
		}

		for( int nCol=1; nCol<NUMCOLS; nCol++)
		{
			int nRow = 0;
			
			int nThisRow = nRow;
			int nThisCol = nCol;

			int nColCount = 0;
				
			while( nThisCol < NUMCOLS &&
				nThisRow < NUMROWS )
			{
				if( m_nBoard[nThisRow][nThisCol] != 0 )
				{
					nColCount++;
				}
				nThisCol++;
				nThisRow++;
			}
				
			if( nColCount > 1 )
			{
				canvas.drawLine( BOARDLEFT + nCol * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
						BOARDTOP + nRow * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ),	
						BOARDLEFT + ( nThisCol - 1 ) * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
						BOARDTOP + ( nThisRow - 1 ) * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ) );
					
				m_bClash = true;
			}
		}
	}
		
	void CheckDiagonal2( Graphics canvas )
	{
		// Check diagonal 2
		for( int nRow=NUMROWS-1; nRow>=0; nRow-- )
		{
			int nCol = NUMCOLS - 1;
				
			int nThisRow = nRow;
			int nThisCol = nCol;

			int nColCount = 0;
				
			while( nThisCol >= 0 &&
				nThisRow < NUMROWS )
			{
				if( m_nBoard[nThisRow][nThisCol] != 0 )
				{
					nColCount++;
				}
				nThisCol--;
				nThisRow++;
			}
				
			if( nColCount > 1 )
			{
				canvas.drawLine( BOARDLEFT + nCol * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
						BOARDTOP + nRow * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ),	
						BOARDLEFT + ( nThisCol + 1 ) * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
						BOARDTOP + ( nThisRow - 1 ) * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ) );
					
				m_bClash = true;
			}
		}

		for( int nCol=NUMCOLS-1; nCol>=0; nCol--)
		{
			int nRow = 0;
			
			int nThisRow = nRow;
			int nThisCol = nCol;

			int nColCount = 0;
				
			while( nThisCol >= 0 &&
				nThisRow < NUMROWS )
			{
				if( m_nBoard[nThisRow][nThisCol] != 0 )
				{
					nColCount++;
				}
				nThisCol--;
				nThisRow++;
			}
				
			if( nColCount > 1 )
			{
				canvas.drawLine( BOARDLEFT + nCol * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
						BOARDTOP + nRow * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ),	
						BOARDLEFT + ( nThisCol + 1 ) * SQUAREWIDTH + ( SQUAREWIDTH / 2 ),
						BOARDTOP + ( nThisRow - 1 ) * SQUAREHEIGHT + ( SQUAREHEIGHT / 2 ) );
					
				m_bClash = true;
			}
				
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {

		int x = e.getX();
		int y = e.getY();
		
		for (int nRow = 0; nRow < NUMROWS; nRow++ )
		{
			for (int nCol = 0; nCol < NUMCOLS; nCol++)
			{
				int nLeftXBoxEdge = BOARDLEFT + nCol * SQUAREWIDTH;
				int nRightXBoxEdge = nLeftXBoxEdge + SQUAREWIDTH;
				int nLeftYBoxEdge = BOARDLEFT + nRow * SQUAREHEIGHT; 
				int nRightYBoxEdge = nLeftYBoxEdge + SQUAREHEIGHT;
				
				if (x > nLeftXBoxEdge && x < nRightXBoxEdge && y > nLeftYBoxEdge && y < nRightYBoxEdge)
				{
					m_nBoard[nRow][nCol] = 1;
				}
			}
		}

		repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseMoved(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	class Backtracking implements Runnable {

		@Override
		public void run() {
			solve(0, 0);
		}
		
        public boolean solve (int nCol, int nRow)
        {
                if (nCol >= NUMCOLS && nRow >= NUMROWS)
                {
                        return true;
                }
                
                m_nBoard[nRow][nCol] = 1;
                repaint();
                System.out.println("Painting");
                try {
                        Thread.sleep(100);
                        System.out.println("Sleeping");
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }
                
                if (m_bClash)
                {
                        m_nBoard[nRow][nCol] = 0;
                        if (nRow == NUMROWS - 1)
                        {
                                return false; 
                        }
                        return solve(nCol, nRow + 1);
                }
                else
                {
                        if (nCol == NUMCOLS - 1)
                        {
                                return true;
                        }
                        return solve(nCol + 1, nRow);
                }
        }
	}
}
