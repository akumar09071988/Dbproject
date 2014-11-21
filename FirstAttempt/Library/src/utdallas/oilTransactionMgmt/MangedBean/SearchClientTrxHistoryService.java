package utdallas.oilTransactionMgmt.MangedBean;

import java.util.List;

import utdallas.oilTransactionMgmt.Dao.SearchClientTrxHistoryDao;
import utdallas.oilTransactionMgmt.VO.ClientTrxListVO;

public class SearchClientTrxHistoryService {
	
public List<ClientTrxListVO> clientTrxSearch(int cid, String fName, String city) throws Exception
{
	List<ClientTrxListVO> clientTrxList= null;
	try
	{	
		clientTrxList = new SearchClientTrxHistoryDao().searchClientTrxHistory(cid,fName,city);
	}catch(Exception e)
	{
		throw e;
	}
	return clientTrxList;
}

}
