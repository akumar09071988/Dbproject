package utdallas.oilTransactionMgmt.MangedBean;


import java.sql.Date;
import java.util.List;

import utdallas.oilTransactionMgmt.Dao.AggregateTrxInfoDao;
import utdallas.oilTransactionMgmt.VO.AggregateTrxInfoListVO;

public class AggregateTrxInfoService {
	
public List<AggregateTrxInfoListVO> aggregateTrxInfo(Date trxTime, int btnID) throws Exception
{
	List<AggregateTrxInfoListVO> aggregateTrxInfoList= null;
	try
	{	
		aggregateTrxInfoList = new AggregateTrxInfoDao().computeAggregateTrxInfo(trxTime, btnID);
	}catch(Exception e)
	{
		throw e;
	}
	return aggregateTrxInfoList;
}

}
