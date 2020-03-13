package org.eclipse.swt;

import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class manager {

	static String server = "root";
	static String password = "14886190";
	static String path = "jdbc:mysql://localhost:3306/library";
	// static String write_Update = " insert into write_ select author.ID, ISBN from
	// author, book where author.ID not in(select write_.AuthorID from write_)";

	private JFrame frmManager;
	private JTextField ISBN;
	private JTextField number_copies;
	private JTextField Title;
	private JTextField pdate;
	private JTextField publishername;
	private JTextField authorID;
	private JTextField firstn;
	private JTextField middlen;
	private JTextField lastn;
	private JTextField azcode;
	private JTextField astreet;
	private JTextField acity;
	private JTextField pphone;
	private JTextField aphone;
	private JTextField pcity;
	private JTextField pzcode;
	private JTextField pstreet;
	private JRadioButton rdbtnBook;
	private JRadioButton rdbtnAuthor;
	private JRadioButton rdbtnPublisher;
	private JRadioButton radioButWriter;
	private ButtonGroup bG;
	private String insert;
	private String update;
	private String delete;
	public Connection con;
	public Statement stmt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//
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
					manager window = new manager();
					window.frmManager.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public manager() {
		initialize();
	}

	private void initialize() {
		frmManager = new JFrame();
		frmManager.setTitle("Manager");
		frmManager.setBounds(100, 100, 1822, 525);
		frmManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Insert", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		GroupLayout groupLayout = new GroupLayout(frmManager.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 1747, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(34, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(40)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 408, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(404, Short.MAX_VALUE)));

		rdbtnBook = new JRadioButton("Book");
		rdbtnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		rdbtnAuthor = new JRadioButton("Author");
		rdbtnPublisher = new JRadioButton("Publisher");
		bG = new ButtonGroup();

		bG.add(rdbtnBook);
		bG.add(rdbtnPublisher);
		bG.add(rdbtnAuthor);
		bG.add(radioButWriter);

		JLabel lblIsbn = new JLabel("ISBN");

		ISBN = new JTextField();
		ISBN.setColumns(10);

		JLabel lblNewLabel = new JLabel("Number_of_copies");

		JLabel lblNewLabel_1 = new JLabel("Titles");

		JLabel lblPublishdate = new JLabel("Publish_Date");

		JLabel lblPublishername = new JLabel("PublisherName");

		number_copies = new JTextField();
		number_copies.setColumns(10);

		Title = new JTextField();
		Title.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Author_ID");

		pdate = new JTextField();
		pdate.setColumns(10);

		publishername = new JTextField();
		publishername.setColumns(10);

		authorID = new JTextField();
		authorID.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("FIRST_NAME");

		JLabel lblNewLabel_4 = new JLabel("MIDDLE_NAME");

		JLabel lblLastname = new JLabel("LAST_NAME");

		firstn = new JTextField();
		firstn.setColumns(10);

		middlen = new JTextField();
		middlen.setColumns(10);

		lastn = new JTextField();
		lastn.setColumns(10);

		JLabel lblAuthorcity = new JLabel("Author_City");

		JLabel lblAuthorstreet = new JLabel("Author_Street");

		JLabel lblAuthorzipcode = new JLabel("Author_ZIP_CODE");

		azcode = new JTextField();
		azcode.setColumns(10);

		astreet = new JTextField();
		astreet.setColumns(10);

		acity = new JTextField();
		acity.setColumns(10);

		JLabel lblAuthorphone = new JLabel("Author_PHONE");

		JLabel lblPublisherphone = new JLabel("PUBLISHER_PHONE");

		JLabel lblPublishercity = new JLabel("Publisher_City");

		pphone = new JTextField();
		pphone.setColumns(10);

		aphone = new JTextField();
		aphone.setColumns(10);

		pcity = new JTextField();
		pcity.setColumns(10);

		JLabel lblPublishercity_1 = new JLabel("Publisher_Street");

		JLabel lblPublisherzipcode = new JLabel("Publisher_ZIP_CODE");

		pzcode = new JTextField();
		pzcode.setColumns(10);

		pstreet = new JTextField();
		pstreet.setColumns(10);

		JButton btnInsert = new JButton("insert"); // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Insert action
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (rdbtnBook.isSelected()) {

					String isbn = "'" + ISBN.getText() + "'";
					String copies = number_copies.getText();
					String title = "'" + Title.getText() + "'";
					String date = "\"" + pdate.getText() + "\"";
					String pname = "'" + publishername.getText() + "'";

					if (isbn.equals("") || title.equals("") || pname.equals(""))
						JOptionPane.showMessageDialog(null, "Yuo must enter all information ", "Error",
								JOptionPane.ERROR_MESSAGE);
					else {

						try {
							con = DriverManager.getConnection(path, server, password);

							stmt = con.createStatement();
							String sql = "insert into Book " + " (ISBN, Copies,Titles ,Publish_Date, PublisherName )"
									+ "values ( " + isbn + " , " + copies + " , " + title + " , " + date + " , " + pname
									+ " )";
							stmt.executeUpdate(sql);
							con.close();
						} catch (SQLException e1) {

							e1.printStackTrace();
						}

					}

				}

				if (rdbtnAuthor.isSelected()) {
					String id = "'" + authorID.getText() + "'";
					String fname = "'" + firstn.getText() + "'";
					String mname = "'" + middlen.getText() + "'";
					String lname = "'" + lastn.getText() + "'";
					String city = "'" + acity.getText() + "'";
					String street = "'" + astreet.getText() + "'";
					String zip = "'" + azcode.getText() + "'";
					String phone = "'" + aphone.getText() + "'";
					String isbn = "'" + ISBN.getText() + "'";
					if (isbn.equals("") || fname.equals("") || lname.equals("") || id.equals(""))
						JOptionPane.showMessageDialog(null, "Yuo must enter all information ", "Error",
								JOptionPane.ERROR_MESSAGE);
					else {

						try {
							con = DriverManager.getConnection(path, server, password);
							stmt = con.createStatement();
							String sql = "insert into Author "
									+ " (ID ,Fname ,Mname ,Lname ,City ,Street ,ZIP ,Phone ,BOOK_ISBN  )" + "values ( "
									+ id + " , " + fname + " , " + mname + " , " + lname + " , " + city + " , " + street
									+ " , " + zip + " , " + phone + " , " + isbn + " )";
							stmt.executeUpdate(sql);
							con.close();
						} catch (SQLException e1) {

							e1.printStackTrace();
						}

					}

				}

				if (rdbtnPublisher.isSelected()) {

					String Pname = "'" + publishername.getText() + "'";
					String city = "'" + pcity.getText() + "'";
					String street = "'" + pstreet.getText() + "'";
					String zip = "'" + pzcode.getText() + "'";
					String phone = "'" + pphone.getText() + "'";

					if (Pname.equals(""))
						JOptionPane.showMessageDialog(null, "Yuo must enter all information ", "Error",
								JOptionPane.ERROR_MESSAGE);
					else {

						try {
							con = DriverManager.getConnection(path, server, password);
							stmt = con.createStatement();
							String sql = "insert into PUBLISHER  " + " ( Pname  , Phone  , City , Street , ZIP  ) "
									+ " values ( " + Pname + " , " + phone + " , " + city + " , " + street + " , " + zip
									+ " ) ";
							stmt.executeUpdate(sql);
							con.close();
						} catch (SQLException e1) {

							e1.printStackTrace();
						}

					} // else
				} // if

				if (radioButWriter.isSelected()) {

					String BOOK_ISBN = "'" + ISBN.getText() + "'";
					String AuthorID = "'" + authorID.getText() + "'";

					if (BOOK_ISBN.equals("") || AuthorID.equals(""))
						JOptionPane.showMessageDialog(null, "Yuo must enter all information ", "Error",
								JOptionPane.ERROR_MESSAGE);
					else {

						try {
							con = DriverManager.getConnection(path, server, password);

							stmt = con.createStatement();
							String sql = "insert into write_ values (" + BOOK_ISBN + " , " + AuthorID + ")";
							stmt.executeUpdate(sql);
							con.close();
						} catch (SQLException e1) {

							e1.printStackTrace();
						}

					}

				}

			}

		});

		JButton btnUpdate = new JButton("update"); // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Update action
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (rdbtnBook.isSelected()) {

					String isbn = "'" + ISBN.getText() + "'";
					String copies = number_copies.getText();

					if (isbn.equals("") || copies.equals(""))
						JOptionPane.showMessageDialog(null, "Yuo must enter all information ", "Error",
								JOptionPane.ERROR_MESSAGE);
					else {

						try {
							con = DriverManager.getConnection(path, server, password);
							stmt = con.createStatement();
							String sql = "update Book " + " set Copies =" + copies + " where ISBN = " + isbn;
							stmt.executeUpdate(sql);
							con.close();
						} catch (SQLException e1) {

							e1.printStackTrace();
						}

					} // else

				} // if

				if (rdbtnAuthor.isSelected()) {
					String id = "'" + authorID.getText() + "'";
					String city = "'" + acity.getText() + "'";
					String street = "'" + astreet.getText() + "'";
					String zip = "'" + azcode.getText() + "'";
					String phone = "'" + aphone.getText() + "'";

					if (id.equals(""))
						JOptionPane.showMessageDialog(null, "Yuo must enter all information ", "Error",
								JOptionPane.ERROR_MESSAGE);
					else {

						try {
							con = DriverManager.getConnection(path, server, password);
							stmt = con.createStatement();

							String sql = "update Author " + " set Phone =" + phone + " where ID = " + id;

							stmt.executeUpdate(sql);
							con.close();
						} catch (SQLException e1) {

							e1.printStackTrace();
						}

					}

				}

				if (rdbtnPublisher.isSelected()) {

					String Pname = "'" + publishername.getText() + "'";
					String city = "'" + pcity.getText() + "'";
					String street = "'" + pstreet.getText() + "'";
					String zip = "'" + pzcode.getText() + "'";
					String phone = "'" + pphone.getText() + "'";

					if (Pname.equals(""))
						JOptionPane.showMessageDialog(null, "Yuo must enter all information ", "Error",
								JOptionPane.ERROR_MESSAGE);
					else {

						try {
							con = DriverManager.getConnection(path, server, password);
							stmt = con.createStatement();

							String sql = "update PUBLISHER  " + " set phone = " + phone + " where Pname =" + Pname;

							stmt.executeUpdate(sql);
							con.close();
						} catch (SQLException e1) {

							e1.printStackTrace();
						}

					} // else
				} // if

			}
		});

		JButton btnDelete = new JButton("delete"); // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Delete action
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (rdbtnBook.isSelected()) {

					String isbn = "'" + ISBN.getText() + "'";

					if (isbn.equals(""))
						JOptionPane.showMessageDialog(null, "Yuo must enter all information ", "Error",
								JOptionPane.ERROR_MESSAGE);
					else {

						try {
							con = DriverManager.getConnection(path, server, password);
							stmt = con.createStatement();

							String sql = "delete from Book " + " where ISBN = " + isbn;

							stmt.executeUpdate(sql);
							con.close();
						} catch (SQLException e1) {

							e1.printStackTrace();
						}

					} // else

				} // if

				if (rdbtnAuthor.isSelected()) {

					String id = "'" + authorID.getText() + "'";

					if (id.equals(""))
						JOptionPane.showMessageDialog(null, "Yuo must enter all information ", "Error",
								JOptionPane.ERROR_MESSAGE);
					else {

						try {
							con = DriverManager.getConnection(path, server, password);
							stmt = con.createStatement();

							String sql = "delete from Author " + " where ID = " + id;

							stmt.executeUpdate(sql);
							con.close();
						} catch (SQLException e1) {

							e1.printStackTrace();
						}

					}

				}

				if (rdbtnPublisher.isSelected()) {

					String Pname = "'" + publishername.getText() + "'";

					if (Pname.equals(""))
						JOptionPane.showMessageDialog(null, "Yuo must enter all information ", "Error",
								JOptionPane.ERROR_MESSAGE);
					else {

						try {
							con = DriverManager.getConnection(path, server, password);
							stmt = con.createStatement();
							String sql = "delete from PUBLISHER " + "where Pname =" + Pname;
							stmt.executeUpdate(sql);
							con.close();
						} catch (SQLException e1) {

							e1.printStackTrace();
						}

					} // else
				} // if

			}
		});

		radioButWriter = new JRadioButton("Add writer");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup().addContainerGap()
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_panel.createSequentialGroup().addComponent(rdbtnBook).addGap(113)
												.addComponent(rdbtnAuthor))
										.addGroup(gl_panel.createSequentialGroup().addComponent(lblIsbn)
												.addPreferredGap(ComponentPlacement.RELATED, 131, Short.MAX_VALUE)
												.addComponent(ISBN, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel.createSequentialGroup().addComponent(lblNewLabel_1)
												.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(Title, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel.createSequentialGroup().addComponent(lblNewLabel)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(number_copies, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addComponent(rdbtnPublisher)
										.addGroup(gl_panel.createSequentialGroup().addGroup(gl_panel
												.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_panel.createSequentialGroup().addGap(18)
														.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
																.addComponent(lblPublishdate)
																.addComponent(lblPublishername)))
												.addGroup(gl_panel.createSequentialGroup().addGap(17)
														.addComponent(lblNewLabel_2)))
												.addGap(18)
												.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
														.addComponent(authorID, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(publishername, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(
																pdate, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
										.createSequentialGroup().addGap(18)
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_panel.createSequentialGroup().addComponent(lblNewLabel_4)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(middlen, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addGap(18).addComponent(lblAuthorstreet))
												.addGroup(gl_panel
														.createSequentialGroup().addComponent(lblNewLabel_3).addGap(12)
														.addComponent(firstn, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addGap(18).addComponent(lblAuthorcity))
												.addGroup(gl_panel.createSequentialGroup()
														.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
																.addComponent(lblLastname).addComponent(btnInsert))
														.addGap(18)
														.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
																.addGroup(gl_panel.createSequentialGroup()
																		.addComponent(btnUpdate).addGap(51)
																		.addComponent(btnDelete))
																.addGroup(gl_panel.createSequentialGroup()
																		.addComponent(lastn, GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(18)
																		.addGroup(gl_panel
																				.createParallelGroup(Alignment.LEADING)
																				.addComponent(lblPublishercity_1)
																				.addComponent(lblAuthorzipcode))))))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_panel
												.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_panel.createSequentialGroup()
														.addComponent(astreet, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addGap(18).addComponent(lblPublisherphone))
												.addGroup(gl_panel.createSequentialGroup()
														.addComponent(acity, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addGap(18).addComponent(lblAuthorphone))
												.addGroup(gl_panel.createSequentialGroup()
														.addGroup(
																gl_panel.createParallelGroup(Alignment.TRAILING, false)
																		.addComponent(pstreet, Alignment.LEADING)
																		.addComponent(azcode, Alignment.LEADING))
														.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
																.addGroup(gl_panel.createSequentialGroup().addGap(18)
																		.addComponent(lblPublishercity))
																.addGroup(gl_panel.createSequentialGroup()
																		.addPreferredGap(ComponentPlacement.UNRELATED)
																		.addComponent(lblPublisherzipcode)))))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addComponent(
												pphone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE).addComponent(
														aphone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addGroup(gl_panel
																		.createParallelGroup(Alignment.TRAILING, false)
																		.addComponent(pzcode, Alignment.LEADING)
																		.addComponent(pcity, Alignment.LEADING))
																.addContainerGap())))
										.addGroup(gl_panel
												.createSequentialGroup().addGap(115).addComponent(radioButWriter,
														GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
												.addContainerGap()))));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(rdbtnAuthor)
						.addComponent(rdbtnPublisher).addComponent(rdbtnBook).addComponent(radioButWriter))
				.addGap(18)
				.addGroup(
						gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblIsbn)
								.addComponent(ISBN, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPublishdate)
								.addComponent(pdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_3)
								.addComponent(firstn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAuthorcity)
								.addComponent(acity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAuthorphone).addComponent(aphone, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(
						ComponentPlacement.UNRELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel)
						.addComponent(number_copies, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPublishername)
						.addComponent(publishername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_4)
						.addComponent(middlen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAuthorstreet)
						.addComponent(astreet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPublisherphone).addComponent(pphone, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_1)
						.addComponent(Title, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2)
						.addComponent(authorID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLastname)
						.addComponent(lastn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAuthorzipcode)
						.addComponent(azcode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPublishercity).addComponent(pcity, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(37)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addComponent(lblPublishercity_1)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(pstreet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPublisherzipcode).addComponent(pzcode, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
				.addGap(54).addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(btnInsert)
						.addComponent(btnUpdate).addComponent(btnDelete))
				.addContainerGap(136, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);
		frmManager.getContentPane().setLayout(groupLayout);
	}
}