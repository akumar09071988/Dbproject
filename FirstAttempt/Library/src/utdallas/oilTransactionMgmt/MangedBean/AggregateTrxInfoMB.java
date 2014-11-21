package utdallas.oilTransactionMgmt.MangedBean;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.faces.event.ActionEvent;

import utdallas.oilTransactionMgmt.VO.AggregateTrxInfoListVO;

public class AggregateTrxInfoMB 
{
	private Date trxTime;
	private String trxTimeStr ="";
	List<AggregateTrxInfoListVO> aggregateTrxInfoList = null;
	
	
	
	public String getTrxTimeStr() {
		return trxTimeStr;
	}


	public void setTrxTimeStr(String trxTimeStr) {
		this.trxTimeStr = trxTimeStr;
	}


	public Date getTrxTime() {
		return trxTime;
	}


	public void setTrxTime(Date trxTime) {
		this.trxTime = trxTime;
	}

	
	public String message;
	
	public AggregateTrxInfoMB(){
		this.trxTime=null;
	}
	
	
	public List<AggregateTrxInfoListVO> getAggregateTrxInfoList() {
		return aggregateTrxInfoList;
	}

	public void setAggregateTrxInfoList(List<AggregateTrxInfoListVO> aggregateTrxInfoList) {
		this.aggregateTrxInfoList = aggregateTrxInfoList;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	//jsp page button click redirects here
	//monthly report - pass button id =1
	public String monthlyAggregateTrxInfo(ActionEvent ae)
	{
		try
		{
			System.out.println("monthlyAggregateTrxInfo ");
			this.aggregateTrxInfoList = null;
			AggregateTrxInfoService aggregateTrxInfo = new AggregateTrxInfoService();
			this.trxTime = convertStringToSqlDate(this.trxTimeStr);
			this.aggregateTrxInfoList = aggregateTrxInfo.aggregateTrxInfo(this.trxTime,1);
			this.setMessage(null);
			return "success";
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return "failure";
		}
		
	}
	
	//weekly report - pass button id =2
		public String weeklyAggregateTrxInfo(ActionEvent ae)
		{
			try
			{
				this.aggregateTrxInfoList = null;
				AggregateTrxInfoService aggregateTrxInfo = new AggregateTrxInfoService();
				this.aggregateTrxInfoList = aggregateTrxInfo.aggregateTrxInfo(this.trxTime,2);
				this.setMessage(null);
				return "success";
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				return "failure";
			}
			
		}
		
		//daily report - pass button id =3
		public String dailyAggregateTrxInfo(ActionEvent ae)
		{
			try
			{
				this.aggregateTrxInfoList = null;
				AggregateTrxInfoService aggregateTrxInfo = new AggregateTrxInfoService();
				this.aggregateTrxInfoList = aggregateTrxInfo.aggregateTrxInfo(this.trxTime,3);
				this.setMessage(null);
				return "success";
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				return "failure";
			}
			
		}
		
		public java.sql.Date convertStringToSqlDate(String trxDate)
		{
			java.sql.Date sqlStartDate = null;
			try
			{
				SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd-yyyy");
	             java.util.Date date = sdf1.parse(trxDate);
	              sqlStartDate = new Date(date.getTime());
			}
			catch(Exception e)
			{
			  e.printStackTrace();	
			}
			return sqlStartDate;
		}
			
}
