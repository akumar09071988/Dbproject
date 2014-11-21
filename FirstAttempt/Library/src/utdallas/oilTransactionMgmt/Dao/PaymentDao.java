package utdallas.oilTransactionMgmt.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import utdallas.oilTransactionMgmt.VO.TransactionVO;
import utdallas.oilTransactionMgmt.VO.paymentVO;



public class PaymentDao extends BaseDao {
	
	static String fetch_payment_for_transaction = "select * from ots.dbo.payment where trxid = ?";
	static String pay_for_transaction ="{call ots.dbo.spPaymentSettlement(?,?,?,?)}";
	static String fetch_transaction_details="select * from ots.dbo.OILTRANSACTION where trxid =?";
	static String cancel_payment ="{call ots.dbo.spPaymentCancellation(?)}";
	
	public ArrayList<paymentVO> fetchPayment(int transactionID)
	{
		ArrayList<paymentVO> paymentList = new ArrayList<paymentVO>();
		try
		{
			 
			 PreparedStatement statement = (PreparedStatement) getConnection().prepareStatement(this.fetch_payment_for_transaction);
			 statement.setInt(1, transactionID);
			 ResultSet rs = statement.executeQuery();
			 while(rs.next())
			 {
				paymentVO vo = new paymentVO();
				vo.setPid(rs.getInt("pid"));
				vo.setDate(rs.getDate("paymenttime"));
				vo.setAmount(rs.getFloat("paymentamt"));
				vo.setStatus(rs.getInt("isCancelled"));
				vo.setClientID(rs.getInt("cid"));
				vo.setTraderID(rs.getInt("traderid"));
				vo.setTrxID(rs.getInt("trxid"));
			    paymentList.add(vo);
			    System.out.println(rs.getDate("paymenttime"));
			 }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return paymentList;
	}
	
	public void paymentExecute(paymentVO vo)
	{
		 System.out.println("in execute transaction");
		   try
		   {
			   PreparedStatement statement = (PreparedStatement) getConnection().prepareStatement(this.pay_for_transaction);
			   statement.setInt(1,vo.getClientID());
			   System.out.println(vo.getClientID());
			   statement.setInt(2, vo.getTrxID());
			   System.out.println(vo.getTrxID());
			   statement.setFloat(3, vo.getAmount());
			   System.out.println(vo.getAmount());
			   statement.setInt(4, vo.getTraderID());
			   System.out.println(vo.getTraderID());
			   int i=statement.executeUpdate();
			   System.out.println(i);
		   }
		   catch(Exception e)
		   {
			   e.printStackTrace();
		   }
	   
	}
	
	public TransactionVO getTransactionDetails(int trxID)
	{
		 System.out.println("in execute getTransactionDetails"+trxID);
		 TransactionVO vo = new TransactionVO();
		   try
		   {
			   PreparedStatement statement = (PreparedStatement) getConnection().prepareStatement(this.fetch_transaction_details);
				 statement.setInt(1, trxID);
				 ResultSet rs = statement.executeQuery();
				 while(rs.next())
				 {
					 
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
		     }
		   }
		   catch(Exception e)
		   {
			   e.printStackTrace();
		   }
	   return vo;
	}
	
	public void cancelPayment(int pid)
	{
		try
		{
			PreparedStatement statement = (PreparedStatement) getConnection().prepareStatement(this.cancel_payment);
			   statement.setInt(1,pid);
			   int i=statement.executeUpdate();
			   System.out.println(i);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
