package org.eclipse.swt;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class book {

	private JFrame frmRetrieveAllBorrowed;
	private JTextField borrowerID;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void book() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					book window = new book();
					window.frmRetrieveAllBorrowed.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public book() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRetrieveAllBorrowed = new JFrame();
		frmRetrieveAllBorrowed.setTitle("Retrieve all borrowed books");
		frmRetrieveAllBorrowed.setResizable(false);
		frmRetrieveAllBorrowed.setBounds(100, 100, 849, 358);
		frmRetrieveAllBorrowed.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 248, 255));
		frmRetrieveAllBorrowed.getContentPane().add(panel, BorderLayout.NORTH);

		JLabel lblEnterYourId = new JLabel("Enter author ID");
		panel.add(lblEnterYourId);

		borrowerID = new JTextField();
		panel.add(borrowerID);
		borrowerID.setColumns(10);

		JButton btnNewButton = new JButton("Retrieve");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					// Class.forName("com.mysql.jdbc.Driver");
					Connection myConn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root",
							"14886190");

					String borrowerId = "'" + borrowerID.getText() + "'";
					String join = "	select * from book, author where book.ISBN = author.BOOK_ISBN AND author.ID = "
							+ borrowerId;

					PreparedStatement ps = myConn1.prepareStatement(join);

					ResultSet rs = ps.executeQuery();

					System.out.println("BookISBN\tborrowerID");

					int counter = 0;
					while (rs.next()) {
						String BookISBN = rs.getString("book.ISBN");
						String borrowerId2 = rs.getString("author.ID");
						System.out.println(BookISBN + "\t" + borrowerId2);
						counter++;
					}

					book2 model = new book2(counter, borrowerId);
					table.setModel(model);

					myConn1.close();

				} catch (Exception E) {
					JOptionPane.showMessageDialog(btnNewButton, "Invalid" + E, null, JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel.add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		frmRetrieveAllBorrowed.getContentPane().add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		scrollPane.setViewportView(table);
	}

}