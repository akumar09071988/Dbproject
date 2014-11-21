package utdallas.librarymanagement.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import utdallas.librarymanagement.bean.FineList;


public class FinesDAO {
	
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
	   
	    public void addFines(int loanId,double fine, Connection connection) throws Exception
	    {

	        try{
	            PreparedStatement insertPrepareStmt = connection.prepareStatement("INSERT INTO fines (" +
	                    " Loan_id, " +
	                    " Fine_amount, " +
	                    " Fine_amount_paid, " +
	                    " Paid " +
	                    ") VALUES (" +
	                    "?,?,?,?)");
	            insertPrepareStmt.setInt(1, loanId);
	            insertPrepareStmt.setDouble(2, fine);
	            insertPrepareStmt.setDouble(3, 0);
	            insertPrepareStmt.setBoolean(4, false);
	            insertPrepareStmt.executeUpdate();
	            insertPrepareStmt.close();
	           
	        }
	        catch(Exception e)
	        {
	            throw e;
	        }
	   
	    }
	   
	    public void updateFines(int loanId,double fine, Connection connection) throws Exception
	    {
	        String sqlStr = "";
	        try
	        {
	            sqlStr = "Update fines set Fine_amount="+fine+" where Loan_id="+loanId;
	               
	            PreparedStatement updatePrepareStmt = connection.prepareStatement(sqlStr);
	            updatePrepareStmt.executeUpdate();
	            updatePrepareStmt.close();
	           
	        }
	        catch (Exception e)
	        {
	        	throw e;
	        }
	   
	    }
	    public void payFine(String loanId,double amt) throws Exception
	    {
	        String sqlStr = "";
	        try
	        {
	        	Connection connection = getSQLConnection();
	        	Integer fineAmountPaid = null;
	        	sqlStr = "select fine_amount_paid from fines where loan_id ="+loanId;
	        	Statement stmt = connection.createStatement();
	        	ResultSet rs =stmt.executeQuery(sqlStr);
	        	while(rs.next())
	        	{
	        		fineAmountPaid = rs.getInt("fine_amount_paid");
	        	}
	        	if(fineAmountPaid==null)
	        	{
	        		throw new Exception("No Record Found");
	        	}
	            sqlStr = "Update fines set Fine_amount_paid="+ (fineAmountPaid+amt) +" where Loan_id="+loanId;
	               
	            PreparedStatement updatePrepareStmt = connection.prepareStatement(sqlStr);
	            updatePrepareStmt.executeUpdate();
	            updatePrepareStmt.close();
	           
	            sqlStr = "Update fines set Paid=true where Loan_id="+loanId+" and Fine_amount_paid=Fine_amount";
	           
	            updatePrepareStmt = connection.prepareStatement(sqlStr);
	            updatePrepareStmt.executeUpdate();
	            updatePrepareStmt.close();
	            connection.close();
	           
	        }
	        catch (Exception e)
	        {
	        	throw e;
	        }
	   
	    }
	   
	    public void refreshFines() throws Exception
	    {
	        String sqlStr = "";
	        try
	        {
	        	Connection connection = getSQLConnection();
	            sqlStr = "select * from book_loans where (Date_in is null or DATEDIFF(Date_in,Due_date)>=1) ";
	               
	            PreparedStatement selectPrepareStmt = connection.prepareStatement(sqlStr);
	            ResultSet dataResultSet = selectPrepareStmt.executeQuery();
	            List<List> fineList=new ArrayList<List>();
	            List dataList=new ArrayList();
	            while (dataResultSet.next())
	            {
	                dataList=new ArrayList();
	                Date date_in=dataResultSet.getDate("Date_in");
	                Date currdate=new Date();
	                Date due_date=dataResultSet.getDate("Due_date");
	               
	                if(date_in==null)
	                {
	                    long diff=currdate.getTime() - due_date.getTime();
	                    if(diff>0)
	                    {
	                        diff=diff/ (1000 * 60 * 60 * 24);
	                        dataList.add(dataResultSet.getInt("Loan_id"));
	                        dataList.add(diff *.25);
	                        fineList.add(dataList);
	                    }
	                   
	                }
	                else
	                {
	                    long diff=date_in.getTime() - due_date.getTime();
	                    if(diff>0)
	                    {
	                        diff=diff/ (1000 * 60 * 60 * 24);
	                        dataList.add(dataResultSet.getInt("Loan_id"));
	                        dataList.add(diff *.25);
	                        fineList.add(dataList);
	                    }
	                }
	               
	            }
	            dataResultSet.close();
	            selectPrepareStmt.close();
	           
	           
	            //Insert data into finestable
	            for(List dList:fineList)
	            {
	                int loanId=(Integer)dList.get(0);
	                double fine=(Double)dList.get(1);
	                if(!isLoanIdFinePaid(loanId, connection)) // if fine not paid completely , go in
	                {
	                    if(checkLoanIdPresence(loanId, connection))
	                        updateFines(loanId, fine, connection);
	                    else
	                        addFines(loanId, fine, connection);
	                }
	            }
	            connection.close();
    
	           
	        }
	        catch (Exception e)
	        {
	        	throw e;
	        }
	   
	    }
	   
	    public boolean checkLoanIdPresence(int loanId, Connection connection) throws Exception
	    {

	        String sqlStr = "";
	        int totalCount=0;
	        try
	        {
	            sqlStr = "select count(*) as count from fines where Loan_id="+loanId;
	           
	            PreparedStatement selectPrepareStmt = connection.prepareStatement(sqlStr);
	            ResultSet dataResultSet = selectPrepareStmt.executeQuery();
	            while (dataResultSet.next())
	            {
	                totalCount=dataResultSet.getInt("count");
	            }
	            dataResultSet.close();
	            selectPrepareStmt.close();
	            if(totalCount>0)
	                return true;
	            else
	                return false;
	        }
	        catch (Exception e)
	        {
	        	throw e;
	        }
	   
	   
	    }

	    public boolean isLoanIdFinePaid(List<Integer> loanIDs) throws Exception
	    {

	        String sqlStr = "";
	        int totalCount=0;
	        try
	        {
	        	
	        	for(int i=0; i<loanIDs.size();i++)
	        	{
	        		Connection connection = getSQLConnection();
	            sqlStr = "select count(*) as count from fines where Loan_id="+loanIDs.get(i)+" and Paid="+true;
	           
	            PreparedStatement selectPrepareStmt = connection.prepareStatement(sqlStr);
	            ResultSet dataResultSet = selectPrepareStmt.executeQuery();
	            while (dataResultSet.next())
	            {
	                totalCount=dataResultSet.getInt("count");
	            }
	            dataResultSet.close();
	            selectPrepareStmt.close();
	            connection.close();
	            if(totalCount>0)
	                return true;
	            else
	                return false;
	        	}
	        	
	        }
	        catch (Exception e)
	        {
	        	throw e;
	        }
	   return true;
	   
	    }
	    
	    public boolean isLoanIdFinePaid(Integer loanID, Connection connection) throws Exception
	    {

	        String sqlStr = "";
	        int totalCount=0;
	        try
	        {
	            sqlStr = "select count(*) as count from fines where Loan_id="+loanID+" and Paid="+true;
	           
	            PreparedStatement selectPrepareStmt = connection.prepareStatement(sqlStr);
	            ResultSet dataResultSet = selectPrepareStmt.executeQuery();
	            while (dataResultSet.next())
	            {
	                totalCount=dataResultSet.getInt("count");
	            }
	            dataResultSet.close();
	            selectPrepareStmt.close();
	            if(totalCount>0)
	                return true;
	            else
	                return false;
	        	}
	        catch (Exception e)
	        {
	        	throw e;
	        }
	    }
	   
	    public List<FineList> searchFines(String fname,String lname,String cardNo) throws Exception
	    {
	        String sqlStr = "";
	        List<FineList> fineList=new ArrayList<FineList>();
	        try
	        {
	        	Connection connection = getSQLConnection();
	            sqlStr = "select * from fines f,borrower b,book_loans bl where f.Loan_id=bl.Loan_id and b.Card_no=bl.Card_no and f.Paid=false ";
	           
	            if(fname!=null && fname.length()>0)
	                sqlStr+="  and b.Fname like '%"+fname+"%'";
	            if(lname!=null && lname.length()>0)
	                sqlStr+="  and b.Lname like '%"+lname+"%'";
	            if(cardNo!=null && cardNo.length()>0)
	                sqlStr+="  and b.Card_no = "+cardNo;
	           
	            PreparedStatement selectPrepareStmt = connection.prepareStatement(sqlStr);
	            ResultSet dataResultSet = selectPrepareStmt.executeQuery();
	            while (dataResultSet.next())
	            {
	                FineList fine = new FineList();
	                fine.setLoanId(dataResultSet.getString("Loan_id"));
	                fine.setCardNo(dataResultSet.getString("Card_no"));
	                fine.setFname(dataResultSet.getString("Fname"));
	                fine.setLname(dataResultSet.getString("Lname"));
	                fine.setBookId(dataResultSet.getString("Book_id"));
	                fine.setBranchId(dataResultSet.getString("Branch_id"));
	                double totalFine=dataResultSet.getDouble("Fine_amount");
	                double paid=dataResultSet.getDouble("Fine_amount_paid");
	                fine.setFineAmountToBePaid(totalFine-paid);
	                fineList.add(fine);
	            }
	            if(fineList.size()==0)
	            {
	            	throw new Exception("No records found");
	            }
	            dataResultSet.close();
	            selectPrepareStmt.close();
	           connection.close();
	        }
	        catch (Exception e)
	        {
	        	throw e;
	        }
	        return fineList;
	    }
	   
}
