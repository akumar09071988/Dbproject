package utdallas.oilTransactionMgmt.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.faces.model.SelectItem;

import utdallas.oilTransactionMgmt.VO.LoginVO;
import utdallas.oilTransactionMgmt.VO.UserVO;
import utdallas.oilTransactionMgmt.VO.newUserVO;



public class LoginDao extends BaseDao 
{
     
	static String auth_query="select * from ots.dbo.users where USERS.username=? and USERS.password=?";
	static String create_user="{call ots.dbo.insertNewUser(?,?,?,?,?,?,?)}";
	static String test ="{call ots.dbo.test(?,?)}";
	static String get_user_type="select * from ots.dbo.USERTYPE";
	
	public UserVO authentication(LoginVO vo)
	{
		String authorized = "";
		UserVO user = null;
		try
		{
	
		   PreparedStatement statement = (PreparedStatement) getConnection().prepareStatement(this.auth_query);	
		   statement.setString(1, vo.getUserName().trim());
		   statement.setString(2, vo.getPwd().trim());
		   ResultSet rs = statement.executeQuery();
		   
		   while(rs.next())
		   {
			 user = new UserVO();
			 user.setUserID(rs.getInt("uid"));
		     user.setUserName(rs.getString("username"));
		     user.setUserType(rs.getInt("usertypeid"));
		   }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		closeConnection();
		return user;
	}
	
	public void createUser(newUserVO vo)
	{
		try
		{
			java.sql.CallableStatement stmt =	getConnection().prepareCall(this.create_user);
			stmt.setString(1, vo.getUserFirstName());
			stmt.setString(2, vo.getUserLastName());
			stmt.setString(3, vo.getPassword());
			stmt.setInt(4, vo.getUserType());
			stmt.setString(5, vo.getPhoneNum());
			stmt.setString(6, vo.getCellNum());
			stmt.setString(7, vo.getEmailID());
			int i=stmt.executeUpdate();
		    System.out.println(i);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void test()
	{
		try
		{
		  java.sql.CallableStatement stmt =	getConnection().prepareCall(test);
		  stmt.setInt(1, 2);
		  stmt.registerOutParameter(2, java.sql.Types.INTEGER);
		  stmt.execute();
		  System.out.println(stmt.getInt(2));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public ArrayList<SelectItem> getUserType()
	{
		ArrayList<SelectItem> userTypeList = new ArrayList<SelectItem>();
		try
		{
			PreparedStatement statement = (PreparedStatement) getConnection().prepareStatement(this.get_user_type);	
			ResultSet rs = statement.executeQuery();
            while(rs.next())
            {
            	SelectItem vo = new SelectItem();
            	vo.setValue(rs.getInt("usertypeid"));
            	vo.setLabel(rs.getString("name"));
            	userTypeList.add(vo);
            }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return userTypeList;
	}
}
