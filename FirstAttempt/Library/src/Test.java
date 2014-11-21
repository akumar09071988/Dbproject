import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;

import utdallas.oilTransactionMgmt.Dao.AggregateTrxInfoDao;
import utdallas.oilTransactionMgmt.Dao.ClientDao;
import utdallas.oilTransactionMgmt.Dao.LoginDao;
import utdallas.oilTransactionMgmt.Dao.SearchClientTrxHistoryDao;
import utdallas.oilTransactionMgmt.Dao.TransactionDao;
import utdallas.oilTransactionMgmt.VO.LoginVO;
import utdallas.oilTransactionMgmt.VO.TransactionVO;
import utdallas.oilTransactionMgmt.VO.UserVO;
import utdallas.oilTransactionMgmt.VO.newUserVO;


public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection connection =null;
		try
		{
			//LoginDao dao = new LoginDao();
		/*	LoginVO vo = new LoginVO();
			vo.setUserName("Manager1");
			vo.setPwd("123");
			System.out.println(dao.authentication(vo));*/
		/*	newUserVO vo = new newUserVO();
			vo.setUserFirstName("Client3FirstName");
			vo.setUserLastName("Client3LastName");
			vo.setPassword("1234");
			vo.setUserType(1);
			vo.setPhoneNum("1234546");
			vo.setCellNum("09876");
			vo.setEmailID("axk140230");
			
			dao.createUser(vo);*/
	/*		TransactionVO vo = new TransactionVO();
		    UserVO vo1 = new UserVO();
		    vo1.setUserID(3);
		    vo.setTransactionType(1);
		    vo.setQuantity(10);
		    vo.setCommissionType(2);
		    vo.setCurrentOilPrice(2);
		    vo.setUserID(3);
		    vo.setType("B");
		    TransactionDao dao = new TransactionDao();
		    dao.executeTransaction(vo, vo1);*/
			//System.out.println(dao.getUserType().get(0).getLabel());
            /*TransactionDao dao = new TransactionDao();
            dao.getTransaction(3);*/
			
			/*SearchClientTrxHistoryDao dao = new SearchClientTrxHistoryDao();
			dao.searchClientTrxHistory(4, "", "");*/
			
			
			try
          {
          String trxDate="11-18-2014";
             SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd-yyyy");
             java.util.Date date = sdf1.parse(trxDate);
             java.sql.Date sqlStartDate = new Date(date.getTime());
            
             AggregateTrxInfoDao dao2 = new AggregateTrxInfoDao();
                  dao2.computeAggregateTrxInfo(sqlStartDate,1 );
          }
          catch (Exception e)
          {
                  e.getMessage();
          }
		}
         catch(Exception e)
		{
        	 e.printStackTrace();
		}
	}

}
