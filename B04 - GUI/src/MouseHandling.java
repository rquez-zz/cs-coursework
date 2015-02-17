import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.event.KeyListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
@SuppressWarnings("all")
public class MouseHandling {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MouseHandling window = new MouseHandling();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MouseHandling() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setFocusable(true);
		frame.requestFocusInWindow();
		frame.setFocusTraversalKeysEnabled(false);
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println(e.getKeyChar() + " Key has been pressed.");
			}

			@Override
			public void keyReleased(KeyEvent e) {
				System.out.println(e.getKeyChar() + "Key has been released.");
			}

			@Override
			public void keyTyped(KeyEvent e) {

			}
		});
		frame.getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Mouse clicked.");
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println("Mouse released.");
			}

			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("Mouse pressed.");
			}
		});
		frame.getContentPane().addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				System.out.println("X: " + e.getX() + " Y: " + e.getY());
			}
		});
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
