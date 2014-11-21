package utdallas.oilTransactionMgmt.MangedBean;

import java.util.ArrayList;
import java.util.List;

import utdallas.oilTransactionMgmt.VO.ClientTrxListVO;

public class ClientTrxHistoryMB 
{
	private int cid;
	private String fName="";
	private String city="";
	private List<ClientTrxListVO> clientTrxList = null;
	public String message;
	
	public ClientTrxHistoryMB(){
		this.cid=0;
	}
	
	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getFName() {
		return fName;
	}

	public void setFName(String cid) {
		this.fName = fName;
	}
	public String getCity() {
		return city;
	}

	public void setCity(String cid) {
		this.city = city;
	}
	

	
	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public List<ClientTrxListVO> getClientTrxList() {
		return clientTrxList;
	}

	public void setClientTrxList(List<ClientTrxListVO> clientTrxList) {
		this.clientTrxList = clientTrxList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String clientTrxHistorySearch()
	{
		try
		{
		this.clientTrxList = null;
		SearchClientTrxHistoryService clientTrxHistorySearch = new SearchClientTrxHistoryService();
		if(this.cid==0&&this.fName.isEmpty()&&this.city.isEmpty())
		{
			throw new Exception("Please enter atleast one search field");
		}
		System.out.println(" in clientTrxHistorySearch");
		this.clientTrxList = clientTrxHistorySearch.clientTrxSearch(this.cid,this.fName, this.city);
		if(this.clientTrxList!=null)
		{
			System.out.println("kk   "+this.clientTrxList.size());
		}
		else
		{
			System.out.println("ami null hoon");
		}
		this.setMessage(null);
		return "success";
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
			return "failure";
		}
		
	}

}
