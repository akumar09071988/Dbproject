package utdallas.librarymanagement.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;

public class BaseDao implements Serializable 
{
     private Connection connection =null;

	
     
     
     
     
     public BaseDao() {
		super();
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbProject", "root", "Ultr@mild1");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}






	public Connection getConnection() {
		return connection;
	}

	
     
     
     
     
     
     
}
