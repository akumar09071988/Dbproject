package utdallas.oilTransactionMgmt.Dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.faces.model.SelectItem;

import com.mysql.jdbc.ResultSet;

import utdallas.oilTransactionMgmt.VO.TransactionVO;
import utdallas.oilTransactionMgmt.VO.UserVO;

public class TransactionDao extends BaseDao
{
   private static String execute_transaction="{call ots.dbo.spOilTransaction(?,?,?,?,?,?)}";
   private static String get_all_transaction ="select * from ots.dbo.OILTRANSACTION";
   private static String get_client_transaction ="select * from ots.dbo.OILTRANSACTION where cid=?";
   private static String get_commission_type ="select * from ots.dbo.COMMISSIONFEETYPE";
   private static String cancel_transaction="{call ots.dbo.spTransactionCancellation(?)}";
   
   
   public void executeTransaction(TransactionVO transVo ,UserVO user)
   {
	   System.out.println("in execute transaction");
	   try
	   {
		   PreparedStatement statement = (PreparedStatement) getConnection().prepareStatement(this.execute_transaction);
		   statement.setInt(1, user.getUserID());
		   System.out.println(user.getUserID());
		   statement.setInt(2, transVo.getTransactionType());
		   System.out.println(transVo.getTransactionType());
		   statement.setFloat(3, transVo.getQuantity());
		   System.out.println(transVo.getQuantity());
		   statement.setInt(4, transVo.getCommissionType());
		   System.out.println(transVo.getCommissionType());
		   statement.setFloat(5, transVo.getCurrentOilPrice());
		   System.out.println(transVo.getCurrentOilPrice());
		   statement.setInt(6, transVo.getUserID());
		   System.out.println(transVo.getUserID());
		   int i=statement.executeUpdate();
		   System.out.println(i);
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
	   }
   }
   
   public ArrayList<TransactionVO> getTransaction(int clientID)
   {
	   ArrayList<TransactionVO> list = new ArrayList<TransactionVO>();
	   try
	   {
		   
		   PreparedStatement statement = null;
		   if(clientID==-1)
		   {
			   statement = (PreparedStatement) getConnection().prepareStatement(this.get_all_transaction);
		   }
		   else
		   {
			   statement = (PreparedStatement) getConnection().prepareStatement(this.get_client_transaction);
			   statement.setInt(1, clientID);
		   }
		   System.out.println("in transactionDao"+clientID);
		   java.sql.ResultSet rs = statement.executeQuery();
		   if(rs!=null)
		   {
			   while(rs.next())
			   {
				   TransactionVO vo = new TransactionVO();
				   vo.setTransactionID(rs.getInt("trxid"));
				   System.out.println(vo.getTransactionID());
				   vo.setClientID(rs.getInt("cid"));
				   System.out.println(vo.getClientID());
				   vo.setTrxDate(rs.getDate("trxtime"));
				   System.out.println(vo.getTrxDate());
				   vo.setType(rs.getString("trxtype"));
				   System.out.println(vo.getType());
				   vo.setTrxAmount(rs.getFloat("trxamount"));
				   System.out.println(vo.getTrxAmount());
				   vo.setFeeAmount(rs.getFloat("feeamount"));
				   System.out.println(vo.getFeeAmount());
				   vo.setFeeInOil(rs.getFloat("feeinoil"));
				   System.out.println(vo.getFeeInOil());
				   vo.setCommissionType(rs.getInt("commtype"));
				   System.out.println(vo.getCommissionType());
				   vo.setCancelledStatus(rs.getInt("isCancelled"));
				   System.out.println(vo.getCancelled());
				   vo.setUserID(rs.getInt("uid"));
				   System.out.println(vo.getUserID());
				   vo.setTotalTrxAmount(rs.getFloat("totaltrxamount"));
				   System.out.println(vo.getTotalTrxAmount());
				   vo.setTrxBalance(rs.getFloat("trxbalance"));
				   System.out.println(vo.getTrxBalance());
				   vo.setQuantity(rs.getInt("quantity"));
				   list.add(vo);
			   }
		   }
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
	   }
	   return list;
   }
   
   public ArrayList<SelectItem> getCommissionFeeType()
   {
	   ArrayList<SelectItem> list = new ArrayList<SelectItem>();
	   try
	   {
		   
		   PreparedStatement statement= (PreparedStatement) getConnection().prepareStatement(this.get_all_transaction);
		   
			   statement = (PreparedStatement) getConnection().prepareStatement(this.get_commission_type);
			   java.sql.ResultSet rs = statement.executeQuery();
			   if(rs!=null)
			   {
				   while(rs.next())
				   {
					   SelectItem vo = new SelectItem();
					   vo.setValue(rs.getInt("commtypeid"));
					   vo.setLabel(rs.getString("commname"));
					   list.add(vo);
				   }
			   }
				   
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
	   }
	   return list;
   }
   
   public void cancelTransaction(int trxID)
   {
	   System.out.println("in execute transaction");
	   try
	   {
		   PreparedStatement statement = (PreparedStatement) getConnection().prepareStatement(this.cancel_transaction);
		   statement.setInt(1, trxID);
		   int i=statement.executeUpdate();
		   System.out.println(i);
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
	   }
   }
   
}
