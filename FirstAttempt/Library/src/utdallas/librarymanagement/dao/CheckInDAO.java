package utdallas.librarymanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import utdallas.librarymanagement.bean.LoanList;

public class CheckInDAO {

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
	
	public List<LoanList> checkInSearch(String bookID, String borrowerName, Integer cardNo) throws Exception 
	{
		List<LoanList> loanList = new ArrayList<LoanList>();
		try
		{
			boolean andFlag = false;
			String query = null;
			Connection connection = getSQLConnection();
			query = "select loan_id, book_id, branch_id, card_no from book_loans where ";
			if(!bookID.isEmpty())
			{
				andFlag=true;
				query += "book_id = '" + bookID  + "' ";
			}
			if(!((cardNo==null)||(cardNo==0)))
			{
				if(andFlag)
				{
					query += " and ";
				}
				andFlag = true;
				query += "card_no = " + cardNo  + " ";
			}
			if(!borrowerName.isEmpty())
			{
				if(andFlag)
				{
					query += " and ";
				}
				query += "card_no in (select card_no from borrower where fname like '%"+borrowerName+"%' or lname like '%"+borrowerName+"%')" ;
			}
			query += " and date_in is NULL";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while(rs.next())
			{
				LoanList loan = new LoanList();
				Integer loanID = rs.getInt("loan_id");
				Integer branchID = rs.getInt("branch_id");
				Integer sqlCardNo =  rs.getInt("card_no");
				String sqlBookID = rs.getString("book_id");
				loan.setLoanId(loanID);
				loan.setBranchId(branchID);
				loan.setCardNo(sqlCardNo);
				loan.setBookId(sqlBookID);
				loanList.add(loan);
			}
			if(loanList.size()==0)
			{
				throw new Exception("Sorry!!! No results found");
			}
			rs.close();
			connection.close();
			return loanList;							
		}catch(Exception e)
		{
			e.printStackTrace();
			throw e;
		}
	}
public void checkInCompletion(List<Integer> loanIds) throws Exception
{
	try
	{
		Connection connection = getSQLConnection();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM");
		Date date = new Date();
		String currentDate = dateFormat.format(date);
		for(int i=0;i<loanIds.size();i++)
		{
		Statement statement = connection.createStatement();
		String query = "update book_loans set date_in = '"+ currentDate + "' where loan_id = "+loanIds.get(i);
		statement.executeUpdate(query);
		}
		connection.close();
	}catch(Exception e)
	{
		e.printStackTrace();
		throw e;
	}
}
}
