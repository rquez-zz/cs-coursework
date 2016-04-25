import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.GridLayout;

import javax.swing.BoxLayout;

import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("all")
public class UsefulGuiDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nameField;
	private JTextField numberField;
	private JTextField emailField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UsefulGuiDialog dialog = new UsefulGuiDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public UsefulGuiDialog() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		{
			JLabel lblNewLabel_1 = new JLabel("Name    ");
			contentPanel.add(lblNewLabel_1);
		}
		{
			nameField = new JTextField();
			contentPanel.add(nameField);
			nameField.setColumns(30);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Number");
			contentPanel.add(lblNewLabel_2);
		}
		{
			numberField = new JTextField();
			contentPanel.add(numberField);
			numberField.setColumns(30);
		}
		{
			JLabel lblNewLabel = new JLabel("Email    \n");
			contentPanel.add(lblNewLabel);
		}
		{
			emailField = new JTextField();
			contentPanel.add(emailField);
			emailField.setColumns(30);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.out.println("Name: " + nameField.getText());
						System.out.println("Number: " + numberField.getText());
						System.out.println("Email: " + emailField.getText());
						
						for (Component C : contentPanel.getComponents()){

						    if (C instanceof JTextField){

						        ((JTextComponent) C).setText(""); //abstract superclass
						    }
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
