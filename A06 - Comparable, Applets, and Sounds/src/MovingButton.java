import java.awt.*;
import java.applet.*;

public class MovingButton extends Applet implements Runnable{
	

	private static final long serialVersionUID = 1L;
	Thread t1;
	Button theButton;
	int xPos = 500;
	
	public void start() {
		if (t1 == null)	{
			setSize(500,200);
			theButton = new Button("This a Button");
			MovingButton.this.add(theButton);
			t1 = new Thread(this);
			t1.start();
		}
	}
	
	public void stop() {
		if (t1 != null) {
			t1 = null;
		}
	}
	
	public void run() {
		while (true) {
			repaint();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
				
	}
	
	public void paint(Graphics g) {
		xPos--;
		theButton.setLocation(xPos,100);
	}

}
