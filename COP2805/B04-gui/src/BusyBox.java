import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JRadioButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;

import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;

import java.awt.Component;

import javax.swing.border.BevelBorder;

import java.awt.ComponentOrientation;

import javax.swing.JLabel;

import java.awt.Rectangle;

import javax.swing.border.MatteBorder;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.ImageIcon;
@SuppressWarnings("all")
public class BusyBox {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BusyBox window = new BusyBox();
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
	public BusyBox() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel();
		tabbedPane.addTab("Buttons", null, buttonPanel, null);

		JButton btnNewButton_2 = new JButton("Button 1");
		buttonPanel.add(btnNewButton_2);

		JButton btnNewButton = new JButton("Button 2");
		buttonPanel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Button 3");
		buttonPanel.add(btnNewButton_1);

		JPanel radioPanel = new JPanel();
		tabbedPane.addTab("Radio Buttons", null, radioPanel, null);

		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Radio Button 1");

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Radio Button 2");

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Radio Button 3");
		GroupLayout gl_radioPanel = new GroupLayout(radioPanel);
		gl_radioPanel.setHorizontalGroup(gl_radioPanel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_radioPanel.createSequentialGroup().addGap(71)
								.addComponent(rdbtnNewRadioButton_2).addGap(5)
								.addComponent(rdbtnNewRadioButton))
				.addGroup(
						gl_radioPanel.createSequentialGroup().addGap(148)
								.addComponent(rdbtnNewRadioButton_1)));
		gl_radioPanel.setVerticalGroup(gl_radioPanel.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_radioPanel
						.createSequentialGroup()
						.addGap(5)
						.addGroup(
								gl_radioPanel
										.createParallelGroup(Alignment.LEADING)
										.addComponent(rdbtnNewRadioButton_2)
										.addComponent(rdbtnNewRadioButton))
						.addGap(5).addComponent(rdbtnNewRadioButton_1)));
		radioPanel.setLayout(gl_radioPanel);

		JPanel textPanel = new JPanel();
		textPanel.setAutoscrolls(true);
		textPanel.setBounds(new Rectangle(100, 100, 100, 100));
		textPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0,
				0)));
		tabbedPane.addTab("Text, List, Combo", null, textPanel, null);
		textPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		textField_1 = new JTextField();
		textPanel.add(textField_1);
		textField_1.setColumns(10);

		textField = new JTextField();
		textPanel.add(textField);
		textField.setColumns(10);

		textField_2 = new JTextField();
		textPanel.add(textField_2);
		textField_2.setColumns(10);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Option 1",
				"Option 2", "Option 3" }));
		textPanel.add(comboBox);

		JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] { "1", "2", "3", "4" };

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		textPanel.add(list);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		tabbedPane.addTab("Table", null, scrollPane, null);

		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setFillsViewportHeight(true);
		table.setModel(new DefaultTableModel(new Object[][] {
				{ null, null, null, null, null, null, null, null, null, null,
						null, null },
				{ null, null, null, null, null, null, null, null, null, null,
						null, null },
				{ null, null, null, null, null, null, null, null, null, null,
						null, null },
				{ null, null, null, null, null, null, null, null, null, null,
						null, null },
				{ null, null, null, null, null, null, null, null, null, null,
						null, null },
				{ null, null, null, null, null, null, null, null, null, null,
						null, null },
				{ null, null, null, null, null, null, null, null, null, null,
						null, null },
				{ null, null, null, null, null, null, null, null, null, null,
						null, null },
				{ null, null, null, null, null, null, null, null, null, null,
						null, null },
				{ null, null, null, null, null, null, null, null, null, null,
						null, null },
				{ null, null, null, null, null, null, null, null, null, null,
						null, null },
				{ null, null, null, null, null, null, null, null, null, null,
						null, null },
				{ null, null, null, null, null, null, null, null, null, null,
						null, null },
				{ null, null, null, null, null, null, null, null, null, null,
						null, null },
				{ null, null, null, null, null, null, null, null, null, null,
						null, null },
				{ null, null, null, null, null, null, null, null, null, null,
						null, null },
				{ null, null, null, null, null, null, null, null, null, null,
						null, null }, }, new String[] { "-4", "-3", "-2", "-1",
				"0", "1", "2", "3", "4", "5", "6", "7" }) {
			boolean[] columnEditables = new boolean[] { false, false, true,
					false, false, false, false, false, true, true, true, true };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(6).setResizable(false);
		table.getColumnModel().getColumn(7).setResizable(false);
		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel
				.setIcon(new ImageIcon(
						BusyBox.class
								.getResource("/javax/swing/plaf/metal/icons/ocean/computer.gif")));
		panel.add(lblNewLabel);
	}
}
