package org.eclipse.swt;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.JRadioButton;
import java.awt.Color;

public class Reseption extends JFrame {

	static String server = "root";
	static String password = "14886190";
	static String path = "jdbc:mysql://localhost:3306/library";

	private JPanel contentPane;
	private JTextField Member_ID;
	private JTextField Number_Of_Copies;
	private JTextField Member_Phone;
	private JTextField ISBN;
	private JTextField BorrowDate;
	private JTextField BorrowReturn;
	private ButtonGroup bG;
	public Connection con;
	public Statement stmt;

	JButton btnInsert;

	JButton btnUpdate;

	JLabel lblMemberid;

	JLabel lblMemberphone;

	JLabel lblIsbn;

	JLabel lblCopy;

	JLabel lblBorrowdate;

	JLabel lblBorrowreturn;

	JRadioButton rdbtnAddMember;

	JRadioButton rdbtnNumerOfCopies;

	JRadioButton rdbtnBorrowdate;

	JRadioButton rdbtnBorrowReturn;
	GroupLayout gl_panel;
	private JButton btnAuthorsbooks;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		try {

			Connection con = DriverManager.getConnection(path, server, password);
			Statement stmt = con.createStatement();

			// con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reseption frame = new Reseption();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Reseption() {
		setTitle("Reseption");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 894, 721);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new TitledBorder(null, "Operations", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(39)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 775, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(46, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(94, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 524, GroupLayout.PREFERRED_SIZE)
					.addGap(29))
		);

		Member_ID = new JTextField();
		Member_ID.setColumns(10);

		Number_Of_Copies = new JTextField();
		Number_Of_Copies.setColumns(10);

		Member_Phone = new JTextField();
		Member_Phone.setColumns(10);

		ISBN = new JTextField();
		ISBN.setColumns(10);

		BorrowDate = new JTextField();
		BorrowDate.setColumns(10);

		BorrowReturn = new JTextField();
		BorrowReturn.setColumns(10);

		btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { // ~~~ Insert action

				if (rdbtnAddMember.isSelected()) {

					String ID = "\'" + Member_ID.getText() + "\'";
					String Phone = "\'" + Member_Phone.getText() + "\'";

					if (ID.equals("")) {
						JOptionPane.showMessageDialog(null, "Yuo must enter all information ", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else {

						try {
							con = DriverManager.getConnection(path, server, password);

							stmt = con.createStatement();
							String sql = "insert into members " + " (ID ,Phone )" + "values ( " + ID + " , " + Phone
									+ " )";
							// ** INERT member QUERY

							stmt.executeUpdate(sql);
							con.close();
						} catch (SQLException e1) {

							e1.printStackTrace();
						} // catch
					} // else check
				} // end if radio

				if (rdbtnBorrowdate.isSelected()) {

					String MemberID = "\"" + Member_ID.getText() + "\"";
					String BOOK_ISBN = "\"" + ISBN.getText() + "\"";
					String Borrow_Date = "\"" + BorrowDate.getText() + "\"";

					if (MemberID.equals("") || BOOK_ISBN.equals("") || Borrow_Date.equals("")) {
						JOptionPane.showMessageDialog(null, "Yuo must enter all information ", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else {

						try {
							con = DriverManager.getConnection(path, server, password);

							stmt = con.createStatement();
							String sql = "insert into Has " + " (Borrow_Date ,Borrow_Return ,MemberID ,BOOK_ISBN)"
									+ "values ( " + Borrow_Date + " , " + "null" + " , " + MemberID + " , " + BOOK_ISBN
									+ " )";
							// ** INERT has WITH Borrow_date QUERY

							stmt.executeUpdate(sql);
							con.close();
						} catch (SQLException e1) {

							e1.printStackTrace();
						} // catch
					} // else check
				} // end if radio

				if (rdbtnBorrowReturn.isSelected()) {

					String MemberID = "\"" + Member_ID.getText() + "\"";
					String BOOK_ISBN = "\"" + ISBN.getText() + "\"";
					String Borrow_Return = "\"" + BorrowReturn.getText() + "\"";

					if (MemberID.equals("") || BOOK_ISBN.equals("") || Borrow_Return.equals("")) {
						JOptionPane.showMessageDialog(null, "Yuo must enter all information ", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else {

						try {
							con = DriverManager.getConnection(path, server, password);

							stmt = con.createStatement();
							String sql = "update Has " + " set Borrow_Return =" + Borrow_Return + " where  MemberID= "
									+ MemberID;
							// ** INERT has WITH Borrow_date and Borrow_return QUERY

							stmt.executeUpdate(sql);
							con.close();
						} catch (SQLException e1) {

							e1.printStackTrace();
						} // catch
					} // else check
				} // end if radio

			}
		});

		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { // ~~~~ Update action

				if (rdbtnBorrowReturn.isSelected()) {

					String Borrow_Return = "\"" + BorrowReturn.getText() + "\"";
					String MemberID = "\"" + Member_ID.getText() + "\"";

					if (Borrow_Return.equals("")) {
						JOptionPane.showMessageDialog(null, "Yuo must enter all information ", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else {

						try {
							con = DriverManager.getConnection(path, server, password);

							stmt = con.createStatement();
							String sql = "update Has " + " set Borrow_Return =" + Borrow_Return + " where  MemberID= "
									+ MemberID;
							// ** UPDATE has WITH Borrow_return QUERY

							stmt.executeUpdate(sql);
							con.close();
						} catch (SQLException e1) {

							e1.printStackTrace();
						} // catch
					} // else check
				} // end if radio

				if (rdbtnNumerOfCopies.isSelected()) {

					String Copies = Number_Of_Copies.getText() + "";
					String isbn = "'" + ISBN.getText() + "'";
					if (Copies.equals("")) {
						JOptionPane.showMessageDialog(null, "Yuo must enter all information ", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else {

						try {
							con = DriverManager.getConnection(path, server, password);

							stmt = con.createStatement();
							String sql = "update Book " + " set Copies =" + Copies + " where ISBN = " + isbn;// **
																												// UPDATE
																												// book
																												// NUMBER
																												// OF
																												// COPIES
																												// QUERY

							stmt.executeUpdate(sql);
							con.close();
						} catch (SQLException e1) {

							e1.printStackTrace();
						} // catch
					} // else check
				} // end if radio

				if (rdbtnAddMember.isSelected()) {

					String Phone = Member_Phone.getText() + "";
					String ID = "\"" + Member_ID.getText() + "\"";
					if (Phone.equals("")) {
						JOptionPane.showMessageDialog(null, "Yuo must enter all information ", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else {

						try {
							con = DriverManager.getConnection(path, server, password);

							stmt = con.createStatement();
							String sql = "update members " + " set Phone =" + Phone + " where  ID = " + ID;// **
																											// UPDATE
																											// member
																											// PHONE
																											// NUMBER
																											// QUERY

							stmt.executeUpdate(sql);
							con.close();
						} catch (SQLException e1) {

							e1.printStackTrace();
						} // catch
					} // else check
				} // end if radio

			}
		});

		lblMemberid = new JLabel("MemberID");

		lblMemberphone = new JLabel("MemberPhone");

		lblIsbn = new JLabel("ISBN");

		lblCopy = new JLabel("Update copies");

		lblBorrowdate = new JLabel("BorrowDate");

		lblBorrowreturn = new JLabel("BorrowReturn");

		rdbtnAddMember = new JRadioButton("Member");

		rdbtnNumerOfCopies = new JRadioButton("Number Of Copies");

		rdbtnBorrowdate = new JRadioButton("Borrow Date");

		rdbtnBorrowReturn = new JRadioButton("Borrow Return");

		bG = new ButtonGroup(); // ~~~ Radio grouping
		bG.add(rdbtnAddMember);
		bG.add(rdbtnBorrowdate);
		bG.add(rdbtnBorrowReturn);
		bG.add(rdbtnNumerOfCopies);

		JButton btnRetrive = new JButton("Retrive");
		btnRetrive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ret r = new ret();
				r.ret();

			}
		});

		btnAuthorsbooks = new JButton("Authors_books");
		btnAuthorsbooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				book b = new book();
				b.book();
			}
		});

		gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(37)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMemberid)
								.addComponent(lblMemberphone)
								.addComponent(lblIsbn, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE))
							.addGap(20))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addContainerGap(48, Short.MAX_VALUE)
							.addComponent(btnAuthorsbooks)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(Member_ID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(Member_Phone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(ISBN, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(rdbtnAddMember)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
							.addComponent(btnInsert)
							.addComponent(rdbtnBorrowdate)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(rdbtnBorrowReturn)
								.addComponent(rdbtnNumerOfCopies)
								.addComponent(lblBorrowdate, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCopy, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblBorrowreturn, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
							.addGap(22)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(Number_Of_Copies, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(BorrowDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(BorrowReturn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnUpdate)
							.addGap(50)
							.addComponent(btnRetrive)))
					.addContainerGap(29, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(79)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(Member_ID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(Number_Of_Copies, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMemberid)
						.addComponent(lblCopy))
					.addGap(60)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(Member_Phone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMemberphone)
						.addComponent(lblBorrowdate)
						.addComponent(BorrowDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(58)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(ISBN, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblIsbn)
						.addComponent(lblBorrowreturn)
						.addComponent(BorrowReturn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(55)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnAddMember)
						.addComponent(rdbtnNumerOfCopies))
					.addGap(30)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnBorrowdate)
						.addComponent(rdbtnBorrowReturn))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnInsert)
						.addComponent(btnUpdate)
						.addComponent(btnRetrive)
						.addComponent(btnAuthorsbooks))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
}