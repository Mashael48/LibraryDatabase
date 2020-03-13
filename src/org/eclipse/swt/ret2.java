package org.eclipse.swt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class ret2 extends AbstractTableModel {
	private String borrowerID;
	private int RowCount;

	private static final int BOOKISBN_COL = 0;
	private static final int BORROWERID_COL = 1;

	private static final String[] columnNames = { "Borrow_Date", "Borrow_Return", "MemberID", "BOOK_ISBN" };

	public ret2(int counter, String borrowerID) {
		this.borrowerID = borrowerID;
		RowCount = counter;
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return RowCount;
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		try {
			// Class.forName("com.mysql.jdbc.Driver");
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "14886190");

			String join = "select * from has where MemberID = " + borrowerID;
			PreparedStatement ps = myConn.prepareStatement(join);
			ResultSet rs = ps.executeQuery();
			for (int i = 0; i <= row && rs.next(); i++) {
				if (i == row) {
					switch (col) {
					case 0:
						return rs.getString("Borrow_Date");
					case 1:
						return rs.getString("Borrow_Return");
					case 2:
						return rs.getString("MemberID");
					case 3:
						return rs.getString("BOOK_ISBN");

					}
				}

			}
		} catch (Exception E) {
			System.out.println("ERROR" + E);
		}
		return new String();
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}