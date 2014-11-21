package utdallas.librarymanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import utdallas.librarymanagement.bean.FineList;

public class CheckOutDAO {

	private Connection getSQLConnection()
	{
		Connection connection =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "tiger");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	public Integer checkOutBook(String bookID, Integer branchID, Integer cardNo) throws Exception 
	{
		Integer loanID = null;
		try
		{
			Connection connection = getSQLConnection();
			int prevBorrowedCount = 0;
			Integer noOfCopies = null;
			Integer numberOfCopiesAvailable = null;
			Integer cardIDCount = null; 
			String query = "select count(*) from book_loans where card_no = ? and date_in is NULL";
			PreparedStatement statement= connection.prepareStatement(query);
			statement.setInt(1, cardNo);
			ResultSet rs = statement.executeQuery();
			while(rs.next())
			{
				prevBorrowedCount = rs.getInt(1);
			}
			rs.close();
			if(!(prevBorrowedCount<3))
			{
				throw new Exception("Sorry. Only three book loans allowed per borrower.");
			}
			if(cardNo!=0)
			{
			query = "select count(*) from borrower where card_no = ?";
			statement= connection.prepareStatement(query);
			statement.setInt(1, cardNo);
			rs = statement.executeQuery();
			while(rs.next())
			{
				cardIDCount = rs.getInt(1);
			} 
			if(cardIDCount.equals(0))
			{
				throw new Exception("Invalid Card Number");
			}
			rs.close();
			}
			List<FineList> fineList = new FinesDAO().searchFines("", "", cardNo.toString()); 
			if(fineList.size()>0)
			{
				throw new Exception("You have outstanding bill amount.Not able to checkout");
			}
			query = "select max(loan_id) from book_loans";
			Statement stmt= connection.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next())
			{
				loanID = rs.getInt(1);
			}
			loanID++;
			rs.close();
			
			
			query = "select no_of_copies from book_copies where book_ID = ? and branch_ID = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, bookID);
			statement.setInt(2, branchID);
			rs = statement.executeQuery();
			if (!rs.isBeforeFirst() ) {    
				 throw new Exception("No records found");
				} 
			while(rs.next())
			{
				noOfCopies = rs.getInt(1);
				String availableCopiesQuery = "Select count(*) from book_loans where book_id = '"+ bookID + "' and branch_id = "+ branchID + " and date_in is NULL";
            	Statement statementBookLoans = connection.createStatement();
            	ResultSet result = statementBookLoans.executeQuery(availableCopiesQuery);
            	while(result.next())
            	{
            		Integer noOfCopiesLoaned = result.getInt(1);
            		numberOfCopiesAvailable = noOfCopies-noOfCopiesLoaned;
            		
            	}
            	result.close();
			}
			rs.close();
			if(noOfCopies!=null)
			{
				if(numberOfCopiesAvailable.equals(0))
				{
					throw new Exception("Sorry the book is not available in the branch. Please enter an alternate branch name");
				}
			}
			else
			{
				if(noOfCopies.equals(0))
				{
					throw new Exception("Sorry the book is not available in the branch. Please enter an alternate branch name");
				}
			}
			
			
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM");
			Date date = new Date();
			String currentDate = dateFormat.format(date);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			c.setTime(sdf.parse(currentDate));
			c.add(Calendar.DATE, 14);  
			String dueDate  = sdf.format(c.getTime());  
			query = "insert into book_loans (loan_id, book_id, branch_id, card_no, date_out, due_date) values(?,?,?,?,?,?)";
			statement = connection.prepareStatement(query);
			statement.setInt(1, loanID);
			statement.setString(2, bookID);
			statement.setInt(3, branchID);
			statement.setInt(4, cardNo);
			statement.setString(5, currentDate);
			statement.setString(6, dueDate);
			statement.executeUpdate();
			connection.close();
			return loanID;
					
		}catch(Exception e)
		{
			e.printStackTrace();
			throw e;
		}
	}
	
}
