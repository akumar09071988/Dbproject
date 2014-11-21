package utdallas.librarymanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import utdallas.librarymanagement.bean.BookList;


public class SearchBookDAO {

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
	
	public List<BookList> searchBook(String bookID, String authorName, String bookTitle) throws Exception
	{
		List<BookList> bookList = new ArrayList<BookList>();
		try
		{
			String constructor = " ";
			Connection connection = getSQLConnection();
            String query = "select b.book_id , b.title , c.branch_id, c.no_of_copies, a.author_name from book b "
            		+ "inner join book_authors a on b.book_id = a.book_id "
            		+ "inner join book_copies c on a.book_id = c.book_id where "; 
            if(!bookID.isEmpty()&&!authorName.isEmpty()&&!bookTitle.isEmpty())
            {
            	constructor = "abc"; 
            	query += "b.book_id = ? and a.author_name like ? and b.title like ?";
            }
            else if(!bookID.isEmpty()&&!authorName.isEmpty())
            {
            	constructor = "ab";
            	query += "b.book_id = ? and a.author_name like ?";
            }
            else if(!authorName.isEmpty()&&!bookTitle.isEmpty())
            {
            	constructor= "bc";
            	query += "a.author_name like ? and b.title like ?";
            }
            else if(!bookID.isEmpty()&&!bookTitle.isEmpty())
            {
            	constructor= "ac";
            	query += "b.book_id = ? and b.title like ?";
            }
            else if(!bookID.isEmpty())
            {
            	constructor= "a";
            	query += "b.book_id = ?";
            }
            else if(!authorName.isEmpty())
            {
            	constructor= "b";
            	query += "a.author_name like ?";
            }
            else if(!bookTitle.isEmpty())
            {
            	constructor= "c";
            	query += "b.title like ?";
            }
            //query += " group by c.branch_id, b.book_"; 
            PreparedStatement stmt = connection.prepareStatement(query);
            if(constructor.equals("abc"))
            {
            	stmt.setString(1, bookID);
            	stmt.setString(2, "%" + authorName + "%");
            	stmt.setString(3, "%" + bookTitle + "%");
            }
            else if(constructor.equals("ab"))
            {
            	stmt.setString(1, bookID);
            	stmt.setString(2, "%" + authorName + "%");
            }
            else if(constructor.equals("bc"))
            {
            	stmt.setString(1, "%" + authorName + "%");
            	stmt.setString(2, "%" + bookTitle + "%");
            }
            else if(constructor.equals("ac"))
            {
            	stmt.setString(1, bookID);
            	stmt.setString(2, "%" + bookTitle + "%");
            }
            else if(constructor.equals("a"))
            {
            	stmt.setString(1, bookID);
            }
            else if(constructor.equals("b"))
            {
            	stmt.setString(1, "%" + authorName + "%");
            }
            else if(constructor.equals("c"))
            {
            	stmt.setString(1, "%" + bookTitle + "%");
            }
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
            	String sqlBookID = rs.getString("book_id");
            	String sqlBookTitle = rs.getString("title");
            	String sqlBranchID = rs.getString("branch_id");
            	Integer sqlNoOfCopies = rs.getInt("no_of_copies");
            	Integer numberOfCopiesAvailable= sqlNoOfCopies;
            	String sqlAuthorName = rs.getString("author_name");
            	String availableCopiesQuery = "Select count(*) from book_loans where book_id = '"+ sqlBookID + "' and branch_id = "+ Integer.parseInt(sqlBranchID) + " and date_in is NULL";
            	Statement statement = connection.createStatement();
            	ResultSet result = statement.executeQuery(availableCopiesQuery);
            	while(result.next())
            	{
            		Integer noOfCopiesLoaned = result.getInt(1);
            		numberOfCopiesAvailable = sqlNoOfCopies-noOfCopiesLoaned;
            		
            	}
            	result.close();
            	BookList book = new BookList(sqlBookTitle, sqlBookID,sqlAuthorName, numberOfCopiesAvailable, sqlNoOfCopies, sqlBranchID);
            	bookList.add(book);
                System.out.println();
            } 
            if(bookList.isEmpty())
            {
            	throw new Exception("Sorry!!! no records found");
            }
            rs.close();
            connection.close();
            System.out.println("Success!!");
            }catch(Exception e)
		{
			throw e;
		}
		return bookList;
		}
}
