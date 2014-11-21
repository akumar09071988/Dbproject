package utdallas.librarymanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class AddBorrowerDAO {
	
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

	public Integer addBorrower(String fname, String lname,String address, String telephoneNumber) throws Exception
	{
		try
		{
			Connection connection = getSQLConnection();
			Integer prevBorrower = null;
			Integer cardNo = null;
			String query = "select count(*) from borrower where fname = ? and lname = ? and address = ?";
			PreparedStatement statement= connection.prepareStatement(query);
			statement.setString(1, fname);
			statement.setString(2, lname);
			statement.setString(3, address);
			ResultSet rs = statement.executeQuery();
			while(rs.next())
			{
				prevBorrower = rs.getInt(1);
			}
			rs.close();
			if(!prevBorrower.equals(0))
			{
				throw new Exception("Borrower present in the system already!!!");
			}
			query = "select max(card_no) from borrower";
			Statement stmt= connection.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next())
			{
				cardNo = rs.getInt(1);
			}
			cardNo++;
			rs.close();
			query = "insert into borrower values(?,?,?,?,?)";
			statement = connection.prepareStatement(query);
			statement.setInt(1, cardNo);
			statement.setString(2, fname);
			statement.setString(3, lname);
			statement.setString(4, address);
			statement.setString(5, telephoneNumber);
			statement.executeUpdate();
			rs.close();
			connection.close();
			return cardNo;
					
		}catch(Exception e)
		{
			e.printStackTrace();
			throw e;
		}
	}
}
