import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

@SuppressWarnings("all")
public class NewWindow extends JFrame {

	public static void main(String[] args) {

		JFrame myMainFrame = new MainSwing();
		myMainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);		
		myMainFrame.setLayout(new FlowLayout(FlowLayout.CENTER));
		myMainFrame.setSize(400, 100);
		myMainFrame.setLocation(100, 100);
		myMainFrame.setVisible(true);
		
		final JTextField myTextField = new JTextField(30);
		myMainFrame.add(myTextField);
		
		JButton myButton = new JButton();
		myMainFrame.add(myButton);

		myButton.setText("Click Me");
		

		myButton.addActionListener(new ActionListener() {
		
				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					myTextField.setText("Hello GUI World!");
					myTextField.setForeground(new Color(0,0,255));
					
				}
				
				
		});
	}

}
